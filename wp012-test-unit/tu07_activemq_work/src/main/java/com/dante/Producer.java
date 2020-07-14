package com.dante;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;
 
/**
 * @program: activemq
 * @description: 生产者
 * @author: daiwenlong
 * @create: 2018-08-26 15:01
 **/
public class Producer {

    //ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //的链接地址
//    private static final String BROKEN_URL = "tcp://192.168.159.2:61616";
    private static final String BROKEN_URL = "tcp://127.0.0.1:61616";
    //原子计数器
    AtomicInteger count = new AtomicInteger(0);
    //链接工厂
    ConnectionFactory connectionFactory;
    //链接对象
    Connection connection;
    //事务管理
    Session session;
    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal();
 
    public void init(){
        try {
            //创建一个链接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            //从工厂中创建一个链接
            connection  = connectionFactory.createConnection();
            //开启链接
            connection.start();
            //session级别
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String disname){
        try {
            //创建一个消息队列
            Queue queue = session.createQueue(disname);
            //消息生产者,
            MessageProducer messageProducer = null;
            //每个线程使用自己的消息生产者
            if(threadLocal.get()!=null){
                messageProducer = threadLocal.get();
            }else{
                messageProducer = session.createProducer(queue);
                threadLocal.set(messageProducer);
            }
            while(true){
                Thread.sleep(1000);
                int num = count.getAndIncrement();
                //创建一条消息
                TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+
                        "正在发送消息！,count:"+num);
                System.out.println(Thread.currentThread().getName()+
                        "正在发送消息！,count:"+num);
                //发送消息
                messageProducer.send(msg);
                session.commit();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
