package com.dante;

/**
 * @program: activemq
 * @description: 测试消费者
 * @author: daiwenlong
 * @create: 2018-08-26 15:46
 **/
public class RunConsumer {
 
    public static void main(String[] args){
        Consumer consumer = new Consumer();
        consumer.init();
        RunConsumer testConsumer = new RunConsumer();
        //启动多个线程同时消费
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
        new Thread(testConsumer.new ConsumerMq(consumer)).start();
    }
 
   class ConsumerMq implements Runnable{
       Consumer consumer;
        public ConsumerMq( Consumer consumer){
            this.consumer = consumer;
        }
 
        public void run() {
            while(true){
                try {
                    consumer.getMessage("activemq");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
