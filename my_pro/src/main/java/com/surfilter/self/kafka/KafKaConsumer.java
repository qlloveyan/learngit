/**
 * Project Name:my_pro
 * File Name:KafKaConsumer.java
 * Package Name:com.surfilter.self.kafka
 * Date:2016年3月17日下午2:25:48
 *
*/

package com.surfilter.self.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

/**
 * ClassName:KafKaConsumer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月17日 下午2:25:48 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class KafKaConsumer {

	private String topic;
	
	public KafKaConsumer(String topic) {
		super();
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public List<String> getMsg(){
		
		List<String> msgs = new ArrayList<String>();
		
		ConfigFactory configFac = ConfigFactory.newInstance();
		ConsumerConfig config = configFac.getConsumerConfig();
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[],byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[],byte[]> iter = stream.iterator();
        while(iter.hasNext(	)){
        	msgs.add( new String(iter.next().message()) );
        }
        
        
        return msgs;
	}
	
	public static void main(String[] args) {
		ConfigFactory configFac = ConfigFactory.newInstance();
		String topic = configFac.getParams("topic", "test");
		KafKaConsumer consumer = new KafKaConsumer(topic);
		List<String> msgs = consumer.getMsg();
		System.out.println("=================================================================================");
		for(String str:msgs){
			System.out.println(str);
		}
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("20s 暂停期已过!");
		msgs = consumer.getMsg();
		for(String str:msgs){
			System.out.println(str);
		}
		
	}
}

