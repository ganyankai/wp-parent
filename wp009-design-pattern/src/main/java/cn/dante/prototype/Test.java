package cn.dante.prototype;

public class Test {
    public static void main(String[] args) throws Exception{
//        ConcretePrototype1 p1 = new ConcretePrototype1("I");
//        ConcretePrototype1 c1 = (ConcretePrototype1)p1.Clone();
//        System.out.println(p1.getId());
//        System.out.println(c1.getId());

        char[] chars = new char[]{'a','b','c'};
        String str = "abc";
        System.out.println(str.equals(chars));  //false
//        System.out.println(str==chars);  //编译错误

        String str1 = "abc";
        String str2 = "a" + new String("bc");

        System.out.println(str1.equals(str2));  // true
        System.out.println(str1==str2);  // false

        str2 = "abc";
        System.out.println(str1==str2);  // true
    }
}
