/**
 * Project Name:my_pro
 * File Name:JedisPool.java
 * Package Name:com.surfilter.self.redis
 * Date:2016年5月4日下午8:17:55
 *
*/

package com.surfilter.self.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.Host;
import org.apache.commons.lang.StringUtils;

import com.surfilter.self.common.ConfigUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * ClassName:JedisPool <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年5月4日 下午8:17:55 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class JedisPoolFactory {

	private static JedisPool pool = null;
	
	private static JedisCluster cluster = null;

	private static JedisPoolConfig config = null;
	
	public static JedisPool getInstance(){
		if (pool == null) {
			Integer maxActive=ConfigUtils.getParam("redis.pool.maxActive", 1024);
			Integer maxIdle=ConfigUtils.getParam("redis.pool.maxIdle", 200);
			Long maxWait=ConfigUtils.getParam("redis.pool.maxWait", 1000L);
			boolean testOnBorrow=ConfigUtils.getParam("redis.pool.testOnBorrow", true);
			boolean testOnReturn=ConfigUtils.getParam("redis.pool.testOnReturn", true);
			String ip=ConfigUtils.getParam("redis.ip", "172.31.25.140");
			Integer port=ConfigUtils.getParam("redis.port", 6379);
			Integer timeout=ConfigUtils.getParam("redis.timeout", 30000);
			String password=ConfigUtils.getParam("redis.password", "");
			
			config = new JedisPoolConfig();
			config.setMaxTotal(maxActive);
			config.setMaxIdle(maxIdle);
			config.setMaxWaitMillis(maxWait);
			config.setTestOnBorrow(testOnBorrow);
			config.setTestOnReturn(testOnReturn);
			if(StringUtils.isNotBlank(password)){
				pool = new JedisPool(config, ip, port,timeout,password);
			}else{
				pool =new JedisPool(config, ip, port, timeout);
			}
		}
		return pool;
	}
	
	public static JedisCluster getCluster(){
		if (cluster == null) {
			Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
			
			String ip=ConfigUtils.getParam("redis.cluster.ip", "127.0.0.1");
			String port=ConfigUtils.getParam("redis.cluster.port", "6379");
			String password=ConfigUtils.getParam("redis.password", "");
			
			String[] ips = ip.split(",");
			String[] ports = port.split(",");
//			String[] passwords = password.split(",");
			for(int i = 0 ; i < ips.length ; i++){
				jedisClusterNodes.add(new HostAndPort(ips[i], Integer.parseInt(ports[i])));
			}
			cluster = new JedisCluster(jedisClusterNodes);
		}
		return cluster;
	}
	
	public static JedisPool getPool() {
		return pool;
	}
 
	public static void setPool(JedisPool pool) {
		JedisPoolFactory.pool = pool;
	}
}

