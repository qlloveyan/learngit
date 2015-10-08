package com.ql.basepro.system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis使用的工具类,采用与Spring整合的方式
 * 在这里只列举redis中常使用的hash操作请参考http://www.cnblogs.com/edisonfeng/p/3571870.html
 * 存取值方式采用 json字符串
 * @author ql
 */
@Service
public class RedisSpringClient {
	
	 //redis数据DB概念,作为数据存储不同的区域
	/**
	 * 开发数据库
	 */
	public static int DEVELOP = 0;

	/**
	 * 注入Spring-redis.xml配置文件中的redis使用类
	 */
	@Autowired
	private RedisTemplate<String,String> redisClient;
	
	/**
	 * 在此处提供两种方式的json字符串存储以及读取操作
	 * 1、采用String,纯 key - value方式
	 * 2、采用hash,需要指定 三个参数  key - field - value (可以在该关键字下再进行区分数据类别)
	 */
	
	/****************************************1、String方式****************************************/
	/**
	 * String类型的数据存储
	 * @param dbType 所选数据库
	 * @param key    键
	 * @param info  值
	 */
	public void strSave(final int dbType , final String key ,final String info){
		redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				byte[] infoTemp = redisClient.getStringSerializer().serialize(info);
				
				connection.set(keyTemp, infoTemp);
				return 1;
			}
		});
	}
	
	/**
	 * 根据key删除指定的数据
	 * @param dbType  需要操作的数据库
	 * @param key     键
	 */
	public String strDel(final int dbType , final String key ){
		return (String) redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				
				return connection.del(keyTemp);
			}
		});
	}
	
	/**
	 * 根据key读取指定的数据
	 * @param dbType  需要操作的数据库
	 * @param key     键
	 */
	public String strRead(final int dbType , final String key ){
		return (String) redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				//读取数据
				byte[] returnStr = connection.get(keyTemp);
				return redisClient.getStringSerializer().deserialize(returnStr);
			}
		});
	}
	
	/*************************************2、采用hash的方式*************************************/
	/**
	 * String类型的数据存储
	 * @param dbType 所选数据库
	 * @param key    键
	 * @param info  值
	 */
	public void hashSave(final int dbType , final String key ,final String field , final String info){
		redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				byte[] fieldTemp = redisClient.getStringSerializer().serialize(field);
				byte[] infoTemp = redisClient.getStringSerializer().serialize(info);
				
				connection.hSet(keyTemp, fieldTemp, infoTemp);
				return 1;
			}
		});
	}
	
	/**
	 * 根据key删除指定的数据
	 * @param dbType  需要操作的数据库
	 * @param key     键
	 */
	public String hashDel(final int dbType , final String key , final String field){
		return (String) redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				byte[] fieldTemp = redisClient.getStringSerializer().serialize(field);
				
				return connection.hDel(keyTemp, fieldTemp);
			}
		});
	}
	
	/**
	 * 根据key读取指定的数据
	 * @param dbType  需要操作的数据库
	 * @param key     键
	 */
	public String hashRead(final int dbType , final String key , final String field){
		return (String) redisClient.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//选择需要操作的数据库
				connection.select(dbType);
				//序列化键和值
				byte[] keyTemp = redisClient.getStringSerializer().serialize(key);
				byte[] fieldTemp = redisClient.getStringSerializer().serialize(field);
				//读取数据
				byte[] returnStr = connection.hGet(keyTemp, fieldTemp);
				return redisClient.getStringSerializer().deserialize(returnStr);
			}
		});
	}
}
