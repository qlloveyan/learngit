/**
 * Project Name:my_pro
 * File Name:ConfigFactory.java
 * Package Name:com.surfilter.self.kafka
 * Date:2016年3月17日上午11:35:22
 *
*/

package com.surfilter.self.kafka;

import java.io.InputStream;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.producer.ProducerConfig;

/**
 * ClassName:ConfigFactory <br/>
 * Function: 配置文件工厂类,用于获取配置文件信息. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月17日 上午11:35:22 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ConfigFactory {

	private final static String CONFIG_FILE = "config.properties";
	
	private static ConfigFactory instance = null;
	
	private static Properties properties = new Properties();
	
	private static InputStream input;
	
	public static ConfigFactory newInstance(){
		if( instance == null ){
			try {
				instance = new ConfigFactory();
				
				input = ConfigFactory.class.getResourceAsStream(CONFIG_FILE);
				properties.load(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * getProConfig:获取kafka生产者配置. <br/>
	 *
	 * @author quanli
	 * @return
	 * @since JDK 1.6
	 */
	public ProducerConfig getProducerConfig(){
		ProducerConfig producerConfig = null;
		try {
			Properties pro = new Properties();
			pro.put("metadata.broker.list", properties.get("metadata.broker.list"));
			pro.put("producer.type", properties.get("producer.type"));
			pro.put("compression.codec", properties.get("compression.codec"));
			pro.put("serializer.class", properties.get("serializer.class"));
			
			producerConfig = new ProducerConfig(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return producerConfig;
	}
	
	/**
	 * 
	 * getProConfig:获取kafka消费者配置. <br/>
	 *
	 * @author quanli
	 * @return
	 * @since JDK 1.6
	 */
	public ConsumerConfig getConsumerConfig(){
		ConsumerConfig consumerConfig = null;
		try {
			Properties pro = new Properties();
			pro.put("zookeeper.connect", properties.get("zookeeper.connect"));
			pro.put("group.id", properties.get("group.id"));
			pro.put("zookeeper.session.timeout.ms", properties.get("zookeeper.session.timeout.ms"));
			pro.put("zookeeper.sync.time.ms", properties.get("zookeeper.sync.time.ms"));
			pro.put("auto.commit.interval.ms", properties.get("auto.commit.interval.ms"));
			pro.put("serializer.class", properties.get("serializer.class"));
			
			consumerConfig = new ConsumerConfig(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consumerConfig;
	}
	
	/**
	 * 
	 * getParams:获取配置文件中的属性值. <br/>
	 *
	 * @author quanli
	 * @param key			
	 * @param defaultVal	默认值
	 * @return
	 * @since JDK 1.6
	 */
	public String getParams(String key,String defaultVal){
		String value = null;
		try {
			InputStream input = getClass().getResourceAsStream(CONFIG_FILE);
			properties.load(input);
			
			value = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value==null?defaultVal:value;
	}
	
	public static void main(String[] args) {
		ConfigFactory cFactory = ConfigFactory.newInstance();
		System.out.println(cFactory.getParams("metadata.broker.list", "127.0.0.1:9092"));
	}
}

