package cn.dante.p01singleton;

public class SingleTon {
    private SingleTon(){
        System.out.println("singleton is create ");
    }
    private static SingleTon instance = new SingleTon();
    private static SingleTon getInstance(){
        return instance;
    }

    public static void createString(){
        System.out.println("str is create");
    }

    public static void main(String[] args) {
        //直接调用类方法竟然还是会调用构造方法
        SingleTon.createString();
    }
}
