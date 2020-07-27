package cn.dante.p02queue;

public class TestMyQueue {

    public static void main(String[] args) {
        MyQueue mq = new MyQueue();
//        MyCycleQueue mq = new MyCycleQueue(4);
        mq.insert(1);
        mq.insert(3);
        mq.insert(5);
        mq.insert(7);
        System.out.println(mq.isEmpty());
        System.out.println(mq.isFull());

        System.out.println(mq.peek());
        while (!mq.isEmpty()){
            System.out.print(mq.remove() +" ");
        }
        mq.insert(4);
    }

}
