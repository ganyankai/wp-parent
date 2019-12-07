package cn.dante.p01singleton;

public class LazySingleton {
    private LazySingleton(){
        System.out.println("singleton is create ");
    }
    private static LazySingleton instance = null;
    private synchronized static LazySingleton getInstance(){
        if (instance == null){
            return new LazySingleton();
        }
       return instance;
    };

    public static void createString(){
        System.out.println("str is create");
    }

    public static void main(String[] args) {
        //直接调用类方法此时不会创建类对象
        LazySingleton.createString();
    }
}
