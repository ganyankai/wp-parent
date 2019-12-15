package cn.dante.p01singleton;

public class StaticSingleton {
    private StaticSingleton(){
        System.out.println("StaticSingleton is create ");
    }

    public static class SingletonHolder{
        private static StaticSingleton instance =  new StaticSingleton();
    }

    private static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

    public static void createString(){
        System.out.println("str is create");
    }

    public static void main(String[] args) {
        StaticSingleton.createString();
    }
}
