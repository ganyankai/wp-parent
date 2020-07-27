package cn.dante.p02queue;

public class MyQueue {
    //底层使用数组
    private long[] arr;
    //有效数据的大小
    private int elements;
    //队头
    private int front;
    //队尾
    private int end;

    /**
     * 默认的构造方法
     */
    public MyQueue(){
        arr = new long[10];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 带参数的构造方法,参数为数组的大小
     */
    public MyQueue(int maxsize){
        arr = new long[maxsize];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 添加数据,从队尾插入
     */
    public void insert(long value){
        arr[++end] = value;
        elements ++;
    }

    /**
     * 删除数据,从对头删除
     */
    public long remove(){
        elements --;
        return arr[front++];
    }

    /**
     * 查看数据,对头
     */
    public long peek(){
        return arr[front];
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return elements == 0;
    }

    /**
     * 判断是否满了
     */
    public boolean isFull(){
        return elements == arr.length;
    }
}
