package com.dante.topic;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
public class NoPersistenceRecever {
    
public static void main(String[] args) throws JMSException {
        
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//        Destination topic=session.createTopic("uas_topic");
        Destination topic=session.createTopic("uas_topic_springboot_1");

        MessageConsumer  consumer = session.createConsumer(topic);
        
        Message message=consumer.receive();
        while (message !=null){
            TextMessage textMessage=(TextMessage) message;
            //System.out.println(message.getStringProperty("queue"));
            System.out.println(textMessage.getText());
            session.commit();
            message = consumer.receive(1000L);
        }        
                
        session.close();
        connection.close();
        
    }

}