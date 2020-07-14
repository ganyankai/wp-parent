package com.dante;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;
 
/**
 * @program: activemq
 * @description: 消费者
 * @author: daiwenlong
 * @create: 2018-08-26 15:10
 **/
public class Consumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
 
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
 
//    private static final String BROKEN_URL = "tcp://192.168.159.2:61616";

    private static final String BROKEN_URL = "tcp://127.0.0.1:61616";
 
    ConnectionFactory connectionFactory;
 
    Connection connection;
 
    Session session;
 
    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal();
    AtomicInteger count = new AtomicInteger();
 
    public void init(){
        try {
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            connection  = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
 
    public void getMessage(String disname){
        try {
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer = null;
 
            if(threadLocal.get()!=null){
                consumer = threadLocal.get();
            }else{
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }
            while(true){
                Thread.sleep(1000);
                TextMessage msg = (TextMessage) consumer.receive();
                if(msg!=null) {
                    System.out.println(Thread.currentThread().getName()+": 正在接收信息----------"+msg.getText()+"--->"+count.getAndIncrement());
                }else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
