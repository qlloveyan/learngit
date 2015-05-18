/*
 * Copyright (C) 2011 Fhpt All Rights Reserved.
 * 
 * ConfigUtil.java
 */
package com.surfilter.scma.framework;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * [文件名称]<br>
 * ConfigUtil <br>
 * <br>
 * [文件描述]<br>
 * 内容摘要.<br>
 * 读取配置文件 <br>
 * [修改记录]<br>
 * 2011-8-30 ver1.00 创建 chenghong<br>
 * 
 * @author chenghong
 * @version 1.00
 */
public class ConfigUtil {

	/**
	 * 配置文件
	 */
	public static String CONFIG_FILE = "jdbc.properties";

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	/**
	 * Properties实例
	 */
	private static Properties props = new Properties();

	/**
	 * 构造方法
	 */
	private ConfigUtil() {

	}

	static {
		loadProperty();
	}

	/**
	 * 从配置文件中读取所有的属性
	 */
	private static void loadProperty() {
		try {
			InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			props.load(in);
		} catch (Exception e) {
			logger.error("读取配置文件出错", e);
		}
	}
	
	/**
	 * 根据属性名获得对应值，如果得不到值返回defaultValue
	 * 
	 * @param name
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return 属性对应的值
	 */
	public static String getConfig(String name, String defaultValue) {

		String ret = props.getProperty(name, defaultValue);
		if (ret == null) {
			return defaultValue;
		} else {
			return ret.trim();
		}
	}

	/**
	 * 根据属性名称获得对应值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性对应的值
	 */
	public static String getConfig(String name) {
		return getConfig(name, null);
	}
	

}
