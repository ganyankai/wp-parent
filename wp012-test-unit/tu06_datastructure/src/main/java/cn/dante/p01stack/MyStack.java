package cn.dante.p01stack;

public class MyStack {
    public static long[] arr;
    public static  int top;

//    private long[] arr;
//    private int top;
    /*
     * 默认的构造方法
     */
    public MyStack(){
       arr = new long[10];
       top = -1;
    }

    /**
     * 带参数的构造方法
     */
    public MyStack(int maxsize){
        arr = new long[maxsize];
        top = -1;
    }

    /**
     * 添加数据
     */
    public void push(int value){
        arr[++top] = value;
    }

    /*
     *  弹出数据
     */
    public long pop(){
        return arr[top--];
    }

    /**
     * 查看数据
     */
    public long peek(){
        return arr[top];
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 判断是否满了
     */
    public boolean isFull(){
        return top == arr.length - 1;
    }

}
