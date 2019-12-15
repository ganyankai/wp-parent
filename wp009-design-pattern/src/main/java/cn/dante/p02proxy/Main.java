package cn.dante.p02proxy;

public class Main {
    public static void main(String[] args) {
        IDBQuery q = new DBQueryProxy();    //使用代理
        q.request();                        //真正使用时才创建对象


    }
}
