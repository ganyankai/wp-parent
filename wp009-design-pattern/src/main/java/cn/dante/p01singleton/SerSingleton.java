package cn.dante.p01singleton;

import org.junit.Assert;

import java.io.*;

public class SerSingleton implements Serializable {
    String name;
    private SerSingleton() {
        System.out.println("SerSingleton is create ");
    }
    private static SerSingleton instance = new SerSingleton();
    private static SerSingleton getInstance(){
        return instance;
    }
    public static void createString(){
        System.out.println("str is create");
    }
    private Object readResolve(){   //阻止生成新的实例,总是返回当前对象
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SerSingleton s1 = null;
        SerSingleton s =SerSingleton.getInstance();
        //先将实例串行化到文件
        FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        oos.flush();
        oos.close();

        //从文件中读取原有的单例类
        FileInputStream fis = new FileInputStream("SerSingleton.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (SerSingleton)ois.readObject();

        Assert.assertEquals(s,s1);

//        无readResolve会有这个异常
//        Exception in thread "main" java.lang.AssertionError: expected:<cn.dante.p01singleton.SerSingleton@2d6a9952> but was:<cn.dante.p01singleton.SerSingleton@17550481>
//                at org.junit.Assert.fail(Assert.java:88)
//        at org.junit.Assert.failNotEquals(Assert.java:743)
//        at org.junit.Assert.assertEquals(Assert.java:118)
//        at org.junit.Assert.assertEquals(Assert.java:144)
//        at cn.dante.p01singleton.SerSingleton.main(SerSingleton.java:38)

    }
}
