package cn.dante.shilihua;

class A {
    static {
        System.out.println("A的static代码块...");
    }
    public String s1 = prtString("A的成员变量...");
    public static String s2 = prtString("A的static变量...");
    protected A() {
        System.out.println("A的构造函数...");
    }
    {
        System.out.println("A的代码块");
    }
    public static String prtString(String str) {
//        System.out.println(" in static String prtString");
        System.out.println(str);
        return null;
    }
}
 
class B extends A {
    public String ss1 = prtString("B的成员变量...");

    public B() {
        System.out.println("B的构造函数...");
    }
    static {
        System.out.println("B的static代码块...");
    }
    public static String ss2 = prtString("B的static变量...");
    {
        System.out.println("B的代码块...");
    }
}
 
public class Test {
    public static void main(String[] args) {
        System.out.println("主函数运行");
        B b=new B();
    }
}