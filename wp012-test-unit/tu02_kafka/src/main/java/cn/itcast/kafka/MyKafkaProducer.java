package cn.itcast.kafka;

import java.util.Properties;
import java.util.UUID;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class MyKafkaProducer {
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.put("metadata.broker.list", "192.168.1.181:9092");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("partitioner.class", "cn.itcast.kafka.MyPartitioner");
		Producer producer = new Producer(new ProducerConfig(properties));
		while(true){
			
//			producer.send(new KeyedMessage("xm129", "zhang","我爱我的祖国abc"+UUID.randomUUID()));
			producer.send(new KeyedMessage("dante818", "zhang","abcdefg"+UUID.randomUUID()));
			Thread.sleep(2000);
		}
		
	}
}
