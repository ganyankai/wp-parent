package com.dante.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
public class NoPersistenceSender {
    
    public static void main(String[] args) throws JMSException {
//        private static final String BROKEN_URL = "tcp://127.0.0.1:61616";
//        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.174.104:61616");
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        
        connection.start();
        
        Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination topic=session.createTopic("uas_topic");

        MessageProducer producer=session.createProducer(topic);
        
        for(int i=0 ; i<3 ; i++){
             TextMessage message=session.createTextMessage("message"+i);
             //message.setStringProperty("queue", "queue"+i);
             //message.setJMSType("1");
             producer.send(message);
        }
        session.commit();
        session.close();
        
        connection.close();
        
    }

}