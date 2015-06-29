package com.surfilter.fr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

public class RedisClient {

	private Jedis jedis;//非切片额客户端连接
	private JedisPool jedisPool;//非切片连接池
	private ShardedJedis shardedJedis;//切片额客户端连接
	private ShardedJedisPool shardedJedisPool;//切片连接池
	
	public RedisClient() {
		initialPool();
		initialShardedPool();
		
		jedis =jedisPool.getResource();
		shardedJedis = shardedJedisPool.getResource();
	}
	
	//初始化非切片池
	public void initialPool(){
		//池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(false);
		
		jedisPool = new JedisPool(config, "127.0.0.1",6379);
	}
	
	//初始化切片池,用在多个服务器
	public void initialShardedPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(false);
		//slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add( new JedisShardInfo("127.0.0.1",6379,"test") );
		
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	//1、key功能
	public void keyOperate(){
		System.out.println("========================演示key操作功能=========================");
		//判断key值
		System.out.println("判断key为 'ql'键是否存在："+shardedJedis.exists("ql"));
		System.out.println("========================清空数据===============================");
		System.out.println("清空数:" + jedis.flushDB());
		System.out.println("增加数据 zqh --> 老婆 ："+shardedJedis.set("zqh", "老婆"));
		System.out.println("判断key为 'zqh'键是否存在："+shardedJedis.exists("zqh"));
		//输出所有的键
		shardedJedis.set("wxj", "大娟");
		printRedis();
		//删除某个key，若key不存在，则忽略
		System.out.println("系统中删除ql用户："+shardedJedis.del("ql"));
		printRedis();
		System.out.println("判断key为 'ql'键是否存在："+shardedJedis.exists("ql"));
		System.out.println("=========================设置键为zqh的过期时间====================");
		shardedJedis.expire("zqh", 10);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
		System.out.println("key --->  zqh 的剩余有效时间为："+shardedJedis.ttl("zqh"));
		//移除zqh键的生存时间
		shardedJedis.persist("zqh");
		//查看键的存储值类型
		System.out.println( shardedJedis.type("zqh") );
		 /*
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         *          2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
	}
	
	//2、String操作
	public void stringOperate(){
		System.out.println("====================String 操作=====================");
		System.out.println("清空数据"+jedis.flushDB());
		System.out.println("===================增加数据======================");
		jedis.set("key1", "value1");
		jedis.set("key2", "value2");
		jedis.set("key3", "value3");
		printRedis();
		//1、直接覆盖
		jedis.set("key1", "value1-update");
		//2、增加字符
		jedis.append("key2", "appendString");
		//批量增加
		jedis.mset("test1", "test-value1","test2", "test-value2","test3", "test-value3","test4", "test-value4","test5", "test-value5");
		//批量获取
		jedis.mget("test1","test2","test3","test4","test5");
		//批量删除
		jedis.del(new String[]{"test4","test5"});
		printRedis();
		
		System.out.println("================新增值是放置覆盖========================");
		System.out.println("原先不存在key201,新增："+shardedJedis.setnx("key201", "value201"));
		System.out.println("原先不存在key202,新增："+shardedJedis.setnx("key202", "value202"));
		System.out.println("当key202存在,尝试增加："+shardedJedis.setnx("key202", "new-value202"));
		//获取key201  202的值
		System.out.println("key201 = "+shardedJedis.get("key201"));
		System.out.println("key202 = "+shardedJedis.get("key202"));
		
		//新增key203  并在一定时间之后销毁
		System.out.println("设置有效时间："+shardedJedis.setex("key203", 10, "value203"));
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("4s之后值为："+shardedJedis.get("key203"));
		try {
			Thread.sleep(7000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("11s之后值为："+shardedJedis.get("key203"));
		
		System.out.println("获取原值，更新为新值："+shardedJedis.getSet("key201", "value201-new"));
		System.out.println("key201新值："+shardedJedis.get("key201"));
		System.out.println("================获取子串====================");
		System.out.println("key201对应值的子串为："+shardedJedis.getrange("key201", 0, 5));
	}
	
	//3、list操作
	public void listOperate(){
		System.out.println("====================list 操作=====================");
		System.out.println("清空数据"+jedis.flushDB());
		
		//集合中增加数据
		shardedJedis.lpush("stringList", "vector");
		shardedJedis.lpush("stringList", "vector");
		shardedJedis.lpush("stringList", "ArrayList");
		shardedJedis.lpush("stringList", "LinkedList");
		shardedJedis.lpush("stringList", "MapList");
		shardedJedis.lpush("stringList", "HashList");
		shardedJedis.lpush("numberList", "1");
		shardedJedis.lpush("numberList", "2");
		shardedJedis.lpush("numberList", "3");
		shardedJedis.lpush("numberList", "5");
		
		System.out.println("=================stringList 全部数据===============");
		printList("stringList");
		System.out.println("=================numberList 全部数据===============");
		printList("numberList");
		
		System.out.println("============================删==============================");
		System.out.println("删除指定元素个数："+shardedJedis.lrem("stringList", 1, "vector"));
		printList("stringList");
		System.out.println("删除下标0-2范围的数据："+shardedJedis.ltrim("stringList", 0, 2));
		printList("stringList");
		
		System.out.println("列表元素出栈："+shardedJedis.lpop("stringList"));
		printList("stringList");
		
		System.out.println("修改指定位置的元素："+shardedJedis.lset("stringList", 0, "vew-value"));
		printList("stringList");
		
		System.out.println("=====================查========================");
		System.out.println("numberList 长度："+shardedJedis.llen("numberList"));
		System.out.println("numberList第二个元素：" + shardedJedis.lindex("numberList", 1));
		
		//排序
		/*
         * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
         * 会出现"ERR One or more scores can't be converted into double"
         */
		SortingParams sp = new SortingParams();
		sp.alpha();
		sp.limit(0, 2);
		shardedJedis.sort("stringList", sp);
		System.out.println("=================排序后========================");
		printList("stringList");
	}
	
	//4、set操作
	public void setOperate(){
		System.out.println("=============================set操作===============================");
		jedis.flushDB();
		
		System.out.println("========================增============================");
		jedis.sadd("setExp", "element1");
		jedis.sadd("setExp", "element2");
		jedis.sadd("setExp", "element3");
		jedis.sadd("setExp", "element4");
		jedis.sadd("setExp", "element5");
		System.out.println("查看setExp中所有数据："+jedis.smembers("setExp"));
		
		System.out.println("==========================删========================");
		System.out.println("删除元素element3:"+jedis.srem("setExp", "element3"));
		System.out.println("查看setExp中所有数据："+jedis.smembers("setExp"));
		
		System.out.println("=========================查=========================");
		System.out.println("元素element3是否存在："+jedis.sismember("setExp", "element3"));
		System.out.println("元素element1是否存在："+jedis.sismember("setExp", "element1"));
		//打印
		Set<String> setExp = jedis.smembers("setExp");
		Iterator<String> it = setExp.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+"\t");
		}
		System.out.println("");
		System.out.println("============================集合运算==========================");
		jedis.sadd("setExp1", "element3");
		jedis.sadd("setExp1", "element5");
		jedis.sadd("setExp1", "element7");
		jedis.sadd("setExp1", "element9");
		
		System.out.println("setExp 和 setExp1 交集："+jedis.sinter("setExp", "setExp1"));
		System.out.println("setExp 和 setExp1并集："+jedis.sunion("setExp", "setExp1"));
		System.out.println("setExp 和 setExp1 差集："+jedis.sdiff("setExp", "setExp1"));
	}
	
	//5、sortedSet操作
	public void sortedOperate(){
		System.out.println("=============================zset================================");
		//清空数据
		System.out.println(jedis.flushDB());
		System.out.println("============================增==================================");
		System.out.println("zset增加元素element1:"+shardedJedis.zadd("zset", 7.5, "element1"));
		System.out.println("zset增加元素element2:"+shardedJedis.zadd("zset", 5.0, "element2"));
		System.out.println("zset增加元素element3:"+shardedJedis.zadd("zset", 8.0, "element3"));
		System.out.println("zset增加元素element4:"+shardedJedis.zadd("zset", 1.0, "element4"));
		System.out.println("zset增加元素element5:"+shardedJedis.zadd("zset", 2.0, "element5"));
		
		System.out.println("zset中所有元素："+shardedJedis.zrange("zset", 0, -1));
		System.out.println("==========================删================================");
		System.out.println("zset中删除元素element3:"+shardedJedis.zrem("zset", "element3"));
		System.out.println("zset中所有元素："+shardedJedis.zrange("zset", 0, -1));
		System.out.println("===========================================================");
		System.out.println("==========================查=================================");
		System.out.println("zset中有元素："+shardedJedis.zcard("zset"));
		System.out.println("统计权重在1.0 - 5.0 之间的元素个数为："+shardedJedis.zcount("zset", 1.0, 5.0));
		System.out.println("查看元素element4的权重为："+shardedJedis.zscore("zset", "element4"));
		System.out.println("查看下标1到2范围内的元素值："+shardedJedis.zrange("zset", 1, 2));
	}
	
	//6、hash操作
	public void hashOperate(){
		System.out.println("============================hash==========================");
		System.out.println("============================增=============================");
		System.out.println("为hashs增加："+shardedJedis.hset("hashs", "key1", "value1"));
		System.out.println("为hashs增加："+shardedJedis.hset("hashs", "key2", "value2"));
		System.out.println("为hashs增加："+shardedJedis.hset("hashs", "key3", "value3"));
		System.out.println("新增key4和4的整型键值对："+shardedJedis.hincrBy("hashs", "key4", 4));
		System.out.println("==========================================================");
		System.out.println("============================删=============================");
		shardedJedis.hdel("hashs", "key3");
		System.out.println("hashs中所有值："+shardedJedis.hvals("hashs"));
		System.out.println("==========================================================");
		System.out.println("============================改=============================");
		shardedJedis.hincrBy("hashs", "key4", 401);//为key4值增加401
		System.out.println("hashs中所有值："+shardedJedis.hvals("hashs"));
		System.out.println("==========================================================");
		System.out.println("============================查=============================");
		System.out.println("判断key2键是否存在："+shardedJedis.hexists("hashs", "key2"));
		System.out.println("获取ke4键对应的值："+shardedJedis.hget("hashs", "key4"));
		System.out.println("批量获取key1 和key2的值："+shardedJedis.hmget("hashs", "key1","key2"));
		System.out.println("获取hashs所有键："+shardedJedis.hkeys("hashs"));
		System.out.println("获取hashs所有值："+shardedJedis.hvals("hashs"));
		System.out.println("==========================================================");
	}
	
	//遍历输出所有key 
	public void printRedis(){
		//输出所有的键
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		System.out.println("============================键列表============================");
		while( it.hasNext() ){
			System.out.println( it.next() + "\t" + jedis.get( it.next() ));
		}
		System.out.println("============================================================");
	}
	
	//显示集合所有数据
	public void printList(String key){
		//-1代表倒数一个元素，-2代表倒数第二个元素
		List<String> data = shardedJedis.lrange(key, 0, -1);
		System.out.println("================="+key+"数据=======================");
		for(String temp:data){
			System.out.print(temp + "\t");
		}
		System.out.println("\n=================================================");
	}
}
