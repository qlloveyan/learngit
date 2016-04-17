/**
 * Project Name:my_pro
 * File Name:ElasticsearchUtils.java
 * Package Name:com.surfilter.self.es
 * Date:2016年4月8日下午2:21:51
 *
*/

package com.surfilter.self.es;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.Transport;

/**
 * ClassName:ElasticsearchUtils <br/>
 * Function: elasticsearch使用学习工具类. <br/>
 * Date:     2016年4月8日 下午2:21:51 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ElasticsearchUtils<T> {
	
	private final String PREFIX = "personal";
	
	//ES配置
	private final String ES_CLUSTER_NAME = "elasticsearch-personal";
	private final String ES_CLUSTER_NODE = "node-personal";
	private final String ES_CLUSTER_IP = "127.0.0.1";
	private final Integer ES_CLUSTER_PORT = 9200;
	
	
	public void createClient(){
		Settings setting = Settings.settingsBuilder()
				.put("cluster.name",ES_CLUSTER_NAME).build();
		
		Client client = TransportClient.builder().settings(setting).build()
							.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(ES_CLUSTER_IP,ES_CLUSTER_PORT)));
		
		
	}
}

