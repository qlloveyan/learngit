/**
 * Project Name:my_pro
 * File Name:TestRedis.java
 * Package Name:com.surfilter.self.redis
 * Date:2016年5月4日下午8:17:25
 *
*/

package com.surfilter.self.redis;

import com.surfilter.self.common.ConfigUtils;

import redis.clients.jedis.Jedis;
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
		JedisPool jpool = JedisPoolFactory.getInstance();
		Jedis jedis = jpool.getResource();
		jedis.select(ConfigUtils.getParam("redis.db", 1));
		
	}
}

