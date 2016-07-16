/**
 * Project Name:cannikin
 * File Name:EhCacheUtils.java
 * Package Name:com.cannikin.core.util
 * Date:2013-5-27下午9:20:57
 * Copyright (c) 2013, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
 */

package com.surfilter.framework.utils;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * ClassName:EhCacheUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013-5-27 下午9:20:57 <br/>
 * 
 * @author Alex
 * @version
 * @since JDK 1.6
 * @see
 */
public class EhCacheUtils {

	/**
	 * instance:ehcacheUtils实例.
	 * 
	 * @since JDK 1.6
	 */
	private static EhCacheUtils instance;

	/**
	 * manager:Cache管理实例.
	 * 
	 * @since JDK 1.6
	 */
	private CacheManager manager;

	/**
	 * Creates a new instance of EhCacheUtils.
	 * 
	 */
	private EhCacheUtils() {
		URL url=getClass().getResource("/ehcache.xml");
		manager = CacheManager.newInstance(url);
	}

	/**
	 * getInstance:取得EhCacheUtils实例. <br/>
	 * 
	 * @author Alex
	 * @return EhCacheUtils
	 * @since JDK 1.6
	 */
	public static EhCacheUtils getInstance() {
		if (instance == null) {
			instance = new EhCacheUtils();
		}
		return instance;
	}

	/**
	 * getCache:根据名称获取cache对象. <br/>
	 * 
	 * @author Alex
	 * @param cachename
	 *            cache名称
	 * @return Cache
	 * @since JDK 1.6
	 */
	public Cache getCache(String cachename) {
		Cache cache = manager.getCache(cachename);
		cache.flush();
		return cache;
	}

	/**
	 * manager.
	 * 
	 * @return the manager
	 * @since JDK 1.6
	 */
	public CacheManager getManager() {
		return manager;
	}

	/**
	 * manager.
	 * 
	 * @param manager
	 *            the manager to set
	 * @since JDK 1.6
	 */
	private void setManager(CacheManager manager) {
		this.manager = manager;
	}

	/**
	 * put:在缓存中放入值. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            String
	 * @param key
	 *            String
	 * @param value
	 *            T
	 * @since JDK 1.6
	 */
	public <T extends Serializable> void put(String cacheName, String key, T value) {
		Cache cache = getCache(cacheName);
		Element e = new Element(key, value);
		cache.put(e);
	}

	/**
	 * put:在缓存中存入永恒值. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            String 缓存名
	 * @param key
	 *            String 存入对象主键
	 * @param value
	 *            T 存入对象
	 * @param eternal
	 *            boolean 是否永恒
	 * @since JDK 1.6
	 */
	public <T extends Serializable> void put(String cacheName, String key, T value, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = new Element(key, value);
		e.setEternal(eternal);
		cache.put(e);
		
	}

	/**
	 * put:在缓存中存入永恒值. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            String 缓存名
	 * @param key
	 *            String 存入对象主键
	 * @param value
	 *            T 存入对象
	 * @param live
	 *            int 最大存活时间
	 * @param idle
	 *            int 最大访问间隔时间
	 * @since JDK 1.6
	 */
	public <T extends Serializable> void put(String cacheName, String key, T value, int live, int idle) {
		Cache cache = getCache(cacheName);
		Element e = new Element(key, value);
		e.setTimeToLive(live);
		e.setTimeToIdle(idle);
		cache.put(e);
		
	}

	/**
	 * remove:删除缓存值. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            String 键值
	 * @since JDK 1.6
	 */
	public void remove(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		cache.remove(key);
		
	}

	/**
	 * get:获取缓存中的值. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            String 键值
	 * @return Object
	 * @since JDK 1.6
	 */
	public Object get(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		if(cache==null){
			return null;
		}
		Element e = cache.get(key);
		if(e==null){
			return null;
		}
		
		return e.getObjectValue();
	}

	/**
	 * addToList:将对象增加list到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param value
	 *            存储到List对象
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToList(String cacheName, String key, Serializable value, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (value != null) {
			List<Serializable> list = Collections.synchronizedList(new LinkedList<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	/**
	 * addToList:将对象集合增加list到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param values
	 *            存储的List对象集
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToList(String cacheName, String key, Collection<? extends Serializable> values, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = Collections.synchronizedList(new LinkedList<Serializable>());
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	/**
	 * addToList:将对象增加set到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param value
	 *            存储的Set对象
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToHashSet(String cacheName, String key, Serializable value, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			Set<Serializable> list = Collections.synchronizedSet(new HashSet<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	/**
	 * addToList:将对象集合增加list到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param values
	 *            存储的Set对象集
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToHashSet(String cacheName, String key, Collection<? extends Serializable> values, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			Set<Serializable> list = Collections.synchronizedSet(new HashSet<Serializable>());
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	/**
	 * addToList:将对象增加Arraylist到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param value
	 *            存储的Set对象
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToArrayList(String cacheName, String key, Serializable value, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = Collections.synchronizedList(new ArrayList<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	/**
	 * addToList:将对象集合增加Arraylist到临时缓存中. <br/>
	 * 
	 * @author Alex
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            键值
	 * @param values
	 *            存储的List对象集
	 * @param eternal
	 *            是否持久化到磁盘
	 * @since JDK 1.6
	 */
	public void addToArrayList(String cacheName, String key, Collection<? extends Serializable> values, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = Collections.synchronizedList(new ArrayList<Serializable>());
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.addAll(values);
			e = new Element(key, list);
			e.setEternal(eternal);
			cache.put(e);
		}
		
	}

	public <T extends Serializable> T popFromList(String cacheName, String key, Class<T> T, boolean eternal) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			for (Iterator it = list.iterator(); it.hasNext();) {
				Serializable obj = (Serializable) it.next();
				it.remove();
				e = new Element(key, list);
				e.setEternal(eternal);
				cache.put(e);
				
				return (T) obj;
			}
		}
		
		return null;
	}
	
	/**
	 * createCache:创建新的缓存. <br/>
	 * @author Alex
	 * @param cacheName 缓存名称
	 * @since JDK 1.6
	 */
	public void createCache(String cacheName){
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName(cacheName);
		cacheConfiguration.setEternal(false);
		Cache cache = new Cache(cacheConfiguration);
		getManager().addCache(cache);
	}
	

	/**
	 * main:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @author Alex
	 * @param args
	 * @since JDK 1.6
	 */
	public static void main(String[] args) {
		System.out.println(EhCacheUtils.getInstance().getCache(""));
	}

}
