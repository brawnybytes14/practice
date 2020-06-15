public class SingletonExample{
    private static SingletonExample instance = null;

    private SingletonExample() {}

    static synchronized SingletonExample getInstance(){
        if(instance == null)
            instance = new SingletonExample();
        return instance;
    }
}


class driver{
    public static void main(String[] args) {
        SingletonExample ins = SingletonExample.getInstance();
        SingletonExample ins2 = SingletonExample.getInstance();
        System.out.println(ins + "\n" + ins2);
        for(int i = 0; i < 4; i++){
            System.out.println(SingletonExample.getInstance().hashCode());
        }
    }
}