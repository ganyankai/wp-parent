package cn.itcast.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaConsumerSimple implements Runnable {
    public String title;
    public KafkaStream<byte[], byte[]> stream;
    public KafkaConsumerSimple(String title, KafkaStream<byte[], byte[]> stream) {
        //获取自己的消费编号，以及要消费的kafkaStream
        this.title = title;
        this.stream = stream;
    }
    public void run() {
        System.out.println("开始运行 " + title);
        //6、从KafkaStream获取一个迭代器
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        /**
         * 7、不停地从stream读取新到来的消息，在等待新的消息时，hasNext()会阻塞
         * */
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> data = it.next();
            String topic = data.topic();
            int partition = data.partition();
            long offset = data.offset();
            String msg = new String(data.message());
            System.out.println(String.format(
                    "Consumer: [%s],  Topic: [%s],  PartitionId: [%d], Offset: [%d], msg: [%s]",
                    title, topic, partition, offset, msg));
        }
        System.err.println(String.format("Consumer: [%s] exiting ...", title));
    }

    public static void main(String[] args) throws Exception{
        // 1、准备一些配置参数
        Properties props = new Properties();
        props.put("group.id", "testGroup");
        props.put("zookeeper.connect", "192.168.1.181:2181,192.168.1.182:2181,192.168.1.183:2181");
        props.put("auto.offset.reset", "largest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("partition.assignment.strategy", "roundrobin");
        ConsumerConfig config = new ConsumerConfig(props);
        //2、准备要消费的topic
//        String topic = "order820";
        String topic = "dante129";

        //3、创建一个consumer的连接器
        // 只要ConsumerConnector还在的话，consumer会一直等待新消息，不会自己退出
        ConsumerConnector consumerConn = Consumer.createJavaConsumerConnector(config);
        //创建topicCountMap
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic,1);

        //4、获取每个topic对应的kafkaStream
        Map<String, List<KafkaStream<byte[], byte[]>>> topicStreamsMap = consumerConn.createMessageStreams(topicCountMap);

        //5、消费KafkaStream中的数据
        List<KafkaStream<byte[], byte[]>> streams = topicStreamsMap.get(topic);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < streams.size(); i++)
            executor.execute(new KafkaConsumerSimple("消费者" + (i + 1), streams.get(i)));
    }
}