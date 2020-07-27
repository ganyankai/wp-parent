package cn.dante.p01singleton.example5;
//懒汉式
public class Singleton {
    //4：定义一个变量来存储创建好的类实例
    //5：因为这个变量要在静态方法中使用，所以需要加上static修饰(因果推理法)
    private static Singleton instance = null;

    // 1.私有化构造方法,好在内部控制创建实例的数目
    private Singleton(){

    }

    //2：定义一个方法来为客户端提供类实例
    //3：这个方法需要定义成类方法，也就是要加static
    private static Singleton getInstance(){
        //6：判断存储实例的变量是否有值
        if(instance == null){
            //6.1：如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            instance = new Singleton();
        }
        //不能一个变量
        return instance;
//        return new Singleton();
    }


    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            //看它的hashcode是不是同一个
//            cn.dante.p01singleton.example5.Singleton@14ae5a5
            System.out.println(Singleton.getInstance());
        }
    }

}

//从时间空间来看：以时间换空间
//从线程安全来看: 不安全