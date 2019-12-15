package cn.dante.p02proxy;

public class Main2 {
    public static void main(String[] args) {
        IDBQuery jdkProxy =   JdkDbQueryHanlder.createJdkProxy();
        String str = jdkProxy.request();
        System.out.println(str);
    }
}
