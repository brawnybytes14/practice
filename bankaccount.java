class MyBankAccount implements MyTransactor {
    private float balance = (float) 0.0;
    private float workingBalance = (float) 0.0; // shadow copy
    private Object currentKey = null; // current transacion

    public float getBalance() {
        return this.balance;
    }

    MyBankAccount(float balance) {
        this.balance = balance;
    }

    public synchronized float balance(Object key) throws KeyException {
        if (key != currentKey)
            throw new KeyException("KeyException: " + currentKey + " " + key);

        return workingBalance;
    }

    public synchronized void deposit(Object key, float amount) throws BadAmount, KeyException {
        if (key != currentKey)
            throw new KeyException("KeyException: " + currentKey + " " + key);

        if (workingBalance < -amount)
            throw new BadAmount("Bad amount: " + key);

        workingBalance += amount;
    }

    public synchronized void withdraw(Object key, float amount) throws BadAmount, KeyException {
        deposit(key, -amount);
    }

    // Transaction methods
    public void join(Object key) throws KeyException {
        if (currentKey != null)
            throw new KeyException("Server busy. Please try again. " + currentKey + " " + key);
        // refuse to join if you're already doing a trans

        currentKey = key; // starts a new transaction with this key
        workingBalance = balance; // make shadow, working copy
    }

    public boolean canCommit(Object key) throws KeyException {
        return (key == currentKey);
    }

    public void commit(Object key) throws KeyException {
        if (key != currentKey)
            throw new KeyException("Something went wrong. Please try again. " + currentKey + " " + key);

        balance = workingBalance; // state change to shadow copy
        currentKey = null; // trans is done, reset for next
    }

    public void abort(Object key) throws KeyException {
        currentKey = null; // throws out pending trans state change, resets for next
    }
}

class AccountUser {
    public boolean transfer(float amount, MyBankAccount src, MyBankAccount dst, Object key)
            throws KeyException, BadAmount {

        // Object key = new Object();

        // System.out.println(key);
        try {
            src.join(key);
            dst.join(key);

            src.withdraw(key, amount);
            dst.deposit(key, amount);

            if (src.canCommit(key) && dst.canCommit(key)) {
                try{
                    src.commit(key);
                    dst.commit(key);
                    System.out.println(key + " successful");
                    return true;
                } catch (KeyException e) {
                    src.abort(key);
                    dst.abort(key);
                    System.out.println("exception 2 " + e.getMessage());
                    return false;
                }
              
            } else {
                src.abort(key);
                dst.abort(key);
                System.out.println(key + " failed");
                return false;
            }
        } catch (KeyException | BadAmount e) {
            src.abort(key);
            dst.abort(key);
            System.out.println("exception 1 " + e.getMessage());
            return false;
        }
    }
}

class Driver {
    // Driver class
    public static void main(String[] args) throws KeyException, BadAmount, InterruptedException {
        AccountUser u = new AccountUser();

        MyBankAccount rohit = new MyBankAccount(100);
        MyBankAccount john = new MyBankAccount(100);

        System.out.println("Main thread...");
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("thread 1 is running...");

                    u.transfer(1, rohit, john, "Transaction 01");
                    u.transfer(50, rohit, john, "Transaction 02");
                    u.transfer(1, john, rohit, "Transaction 08");
                    u.transfer(1, john, rohit, "Transaction 10");
                    u.transfer(1, john, rohit, "Transaction 11");
                    u.transfer(1, john, rohit, "Transaction 12");
                    u.transfer(1, john, rohit, "Transaction 13");
                    u.transfer(1, john, rohit, "Transaction 03");
                    u.transfer(1, rohit, john, "Transaction 04");
                    u.transfer(1, rohit, john, "Transaction 05");
                    u.transfer(1, john, rohit, "Transaction 09");

                } catch (KeyException | BadAmount e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("thread 2 is running...");

                    u.transfer(1, john, rohit,  "Transaction 06");
                    u.transfer(1, john, rohit,  "Transaction 07");
                    u.transfer(1, john, rohit,  "Transaction 14");
                    u.transfer(1, john, rohit,  "Transaction 15");
                    u.transfer(1, john, rohit,  "Transaction 16");
                    u.transfer(20, john, rohit, "Transaction 17");
                    u.transfer(1, john, rohit,  "Transaction 18");
                    u.transfer(1, john, rohit,  "Transaction 19");
                    u.transfer(1, john, rohit,  "Transaction 20");
                    u.transfer(1, john, rohit,  "Transaction 21");
                    u.transfer(1, john, rohit,  "Transaction 22");
                    u.transfer(20, john, rohit,  "Transaction 23");

                } catch (KeyException | BadAmount e) {
                    System.out.println(e.getMessage());

                }

            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        System.out.println("rohit " + rohit.getBalance());
        System.out.println("john " + john.getBalance());
    }
}

// Other classes and interfaces
class BadAmount extends Exception {
    BadAmount(String s) {
        super(s);
    }
}

class KeyException extends Exception {
    KeyException(String s) {
        super(s);
    }
}

interface MyTransactor {
    // methods specific to the kinds of operations
    // done for my transaction class
    public void deposit(Object key, float amount) throws BadAmount, KeyException;

    public void withdraw(Object key, float amount) throws BadAmount, KeyException;

    public float balance(Object key) throws KeyException;
}

interface Transactor {
    // enter into a new transaction
    public void join(Object key) throws KeyException;

    // return true if commit is possible
    public boolean canCommit(Object key) throws KeyException;

    // make state change permanent
    public void commit(Object key) throws KeyException;

    // roll back state change
    public void abort(Object key) throws KeyException;
}