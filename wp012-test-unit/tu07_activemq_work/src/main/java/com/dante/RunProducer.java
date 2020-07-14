package com.dante;

/**
 * @program: activemq
 * @description: 测试生产者
 * @author: daiwenlong
 * @create: 2018-08-26 15:13
 **/
public class RunProducer {
 
    public static void main(String[] args){
        Producer producer = new Producer();
        producer.init();
        RunProducer runTest = new RunProducer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用多个线程同时生产
        //Thread 1
        new Thread(runTest.new ProducerMq(producer)).start();
        //Thread 2
        new Thread(runTest.new ProducerMq(producer)).start();
        //Thread 3
        new Thread(runTest.new ProducerMq(producer)).start();
        //Thread 4
        new Thread(runTest.new ProducerMq(producer)).start();
        //Thread 5
        new Thread(runTest.new ProducerMq(producer)).start();
    }

    class ProducerMq implements Runnable{
        Producer producer;
        public ProducerMq(Producer producer){
            this.producer = producer;
        }
        public void run() {
            while(true){
                try {
                    //发送消息
                    producer.sendMessage("activemq");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
