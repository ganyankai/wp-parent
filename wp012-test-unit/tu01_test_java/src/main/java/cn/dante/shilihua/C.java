package cn.dante.shilihua;

public class C {
    static {
        System.out.println("C的static代码块...");
    }

    public C(){
        System.out.println("c 的构造函数 ");
    }
    public static String prtString(String str) {
        System.out.println(" in static String prtString");
        System.out.println(str);
        return null;
    }

    public static void main(String[] args) {
        C c = new C();
    }
}
