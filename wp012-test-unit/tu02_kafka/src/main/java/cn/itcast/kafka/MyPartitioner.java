package cn.itcast.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class MyPartitioner implements Partitioner{
	
	public MyPartitioner(VerifiableProperties properties) {
		
	}
	
	public int partition(Object arg0, int arg1) {
		
		return 2;
	}
	
}
