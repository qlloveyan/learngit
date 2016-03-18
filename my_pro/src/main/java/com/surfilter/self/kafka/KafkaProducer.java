/**
 * Project Name:my_pro
 * File Name:KafkaProducer.java
 * Package Name:com.surfilter.self.kafka
 * Date:2016年3月17日下午1:46:39
 *
*/

package com.surfilter.self.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * ClassName:KafkaProducer <br/>
 * Function: kafka信息生产者. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月17日 下午1:46:39 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class KafkaProducer {

	private String topic;
	
	public KafkaProducer(String topic) {
		super();
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * 
	 * sendMessage:以topic为主题发送消息. <br/>
	 *
	 * @author quanli
	 * @param msg	消息
	 * @since JDK 1.6
	 */
	public void sendMessage(String msg){
		//创建配置对象实例
		ConfigFactory configFac = ConfigFactory.newInstance();
		//获取配置
		ProducerConfig config = configFac.getProducerConfig();
		Producer<String,String> producer = new Producer<String, String>(config);
		//封装消息实体
		KeyedMessage<String, String> keyMes = new KeyedMessage<String, String>(topic, msg);
		//发送消息
		producer.send(keyMes);
	}	
	
	public static void main(String[] args) {
		ConfigFactory configFac = ConfigFactory.newInstance();
		String topic = configFac.getParams("topic", "test");
		KafkaProducer producer = new KafkaProducer(topic);
		producer.sendMessage("Now ,I'm test kafka!");
	}
}

