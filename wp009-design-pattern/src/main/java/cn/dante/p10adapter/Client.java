package cn.dante.p10adapter;

public class Client {
    public static void main(String[] args) {
//        Adapter adapter = new Adapter(new Adaptee());
        Target target = new Adapter(new Adaptee());
        target.request();
    }

}
