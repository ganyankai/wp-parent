package cn.dante.p01singleton.example12;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i <3 ; i++) {
//            System.out.println(Singleton.uniqueInstance.singletonOperation());
//            System.out.println(Singleton.uniqueInstance);
            Singleton.uniqueInstance.singletonOperation();
        }
    }
}
