/**
 * Project Name:my_pro
 * File Name:TestRedis.java
 * Package Name:com.surfilter.self.redis
 * Date:2016年5月4日下午8:17:25
 *
*/

package com.surfilter.self.redis;

import java.io.IOException;

import com.surfilter.self.common.ConfigUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * ClassName:TestRedis <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年5月4日 下午8:17:25 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestRedis {

	public static void main(String[] args) {
		//普通的单链接
		/****/
		JedisPool jpool = JedisPoolFactory.getInstance();
		Jedis jedis = jpool.getResource();
		jedis.select(ConfigUtils.getParam("redis.db", 1));
		//存取数据
		//hash
		jedis.hset("hashTest", "quanli", "全立");
		System.out.println(jedis.hget("hashTest", "quanli"));
		System.out.println(jedis.hget("hashTest", "quanli"));
		//list
		jedis.rpush("listTest", "1","2","3","4");
		System.out.println(jedis.lpop("listTest"));//list操作的方式会删除已查找到的数据
		System.out.println(jedis.lpop("listTest"));
		jedis.del("listTest");
		
		//释放资源
		jedis.close();
		
		
		//redis集群
		JedisCluster cluster = JedisPoolFactory.getCluster();
		cluster.hset("clusterTest", "myCluster", "redis集群测试");
		System.out.println(cluster.hget("clusterTest", "myCluster"));
		try {
			cluster.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

