class one {
    public static void main(String[] args) {
        two t1 = new two();
        Thread th = new Thread(t1);
        th.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("First: " + i);
        }
    }
}

class two implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Second: " + i);
        }
    }
}