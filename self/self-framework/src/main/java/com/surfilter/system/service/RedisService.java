/**
 * Project Name:smcs<br>
 * File Name:AreaCodeService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年12月10日  下午07:36:02<br>
 *
 */
package com.surfilter.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName:AreaCodeService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason: TODO ADD REASON.<br>
 * Date: 2013年12月10日 下午07:36:02<br>
 * 
 * @author Alex
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
public class RedisService {

	/**
	 * SET:存放临时数据的数据库.
	 * 
	 * @since JDK 1.6
	 */
	public static int TEMP = 0;
	/**
	 * SET:存放系统配置参数的数据库.
	 * 
	 * @since JDK 1.6
	 */
	public static int SET = 1;
	/**
	 * ALLCOUNT:存放统计信息.
	 * 
	 * @since JDK 1.6
	 */
	public static int COUNT = 2;

	/**
	 * RESULT:存放结果集.
	 * 
	 * @since JDK 1.6
	 */
	public static int RESULT = 3;

	/**
	 * redisTemplate:redisTemplate.
	 * @since JDK 1.6
	 */
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * save:根据键值保存(String). <br/>
	 * 
	 * @author Alex
	 * @param database
	 *            选择的数据库
	 * @param key
	 *            key
	 * @param info
	 *            需要存储的信息
	 * @since JDK 1.6
	 */
	public void save(final int database, final String key, final String info) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				connection.setEx(
						redisTemplate.getStringSerializer().serialize(key), 0,
						redisTemplate.getStringSerializer().serialize(info));
				return 1;
			}
		});
	}

	/**
	 * read:读取缓存中的某个值（String）. <br/>
	 * 
	 * @author Alex
	 * @param database
	 *            选择的数据库
	 * @param key
	 *            需要读取的key
	 * @return
	 * @since JDK 1.6
	 */
	public String read(final int database, final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				byte[] value = connection.get(redisTemplate
						.getStringSerializer().serialize(key));
				return redisTemplate.getStringSerializer().deserialize(value);
			}
		});
	}

	/**
	 * read:删除缓存中的某个值（string）. <br/>
	 * @author Alex
	 * @param database 选择的数据库
	 * @param key 需要删除的key
	 * @return
	 * @since JDK 1.6
	 */
	public String delete(final int database, final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				return connection.del(redisTemplate.getStringSerializer().serialize(key));
			}
		});
	}
	
	/**
	 * save:根据键值保存(hash). <br/>
	 * 
	 * @author Alex
	 * @param database
	 *            选择的数据库
	 * @param key
	 *            key
	 * @param info
	 *            需要存储的信息
	 * @since JDK 1.6
	 */
	public void save(final int database, final String key,final String field, final String info) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				return connection.hSet(
						redisTemplate.getStringSerializer().serialize(key), 
						redisTemplate.getStringSerializer().serialize(field),
						redisTemplate.getStringSerializer().serialize(info));
			}
		});
	}

	/**
	 * read:读取缓存中的某个值（hash）. <br/>
	 * 
	 * @author Alex
	 * @param database
	 *            选择的数据库
	 * @param key
	 *            需要读取的key
	 * @return
	 * @since JDK 1.6
	 */
	public String read(final int database, final String key, final String field) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				byte[] value = connection.hGet(redisTemplate
						.getStringSerializer().serialize(key),redisTemplate
						.getStringSerializer().serialize(field));
				return redisTemplate.getStringSerializer().deserialize(value);
			}
		});
	}

	/**
	 * read:删除缓存中的某个值（hash）. <br/>
	 * @author Alex
	 * @param database 选择的数据库
	 * @param key 需要删除的key
	 * @return
	 * @since JDK 1.6
	 */
	public String delete(final int database, final String field, final String key) {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.select(database);
				return connection.hDel(redisTemplate
						.getStringSerializer().serialize(key),redisTemplate
						.getStringSerializer().serialize(field));
			}
		});
	}

}
