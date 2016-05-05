/**
 * Project Name:my_pro
 * File Name:ConfigUtils.java
 * Package Name:com.surfilter.self.common
 * Date:2016年5月4日下午8:05:29
 *
*/

package com.surfilter.self.common;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * ClassName:ConfigUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年5月4日 下午8:05:29 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ConfigUtils {
	
	private static final String CONFIG_FILE = "config.properties";
	
	private static Properties pro = null;
	
	static{
		try {
			if( pro == null ){
				pro = new Properties();
				pro.load(ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getParam(String key , String defaultVal){
		if(StringUtils.isNotBlank(pro.getProperty(key))){
			return pro.getProperty(key);
		}else{
			return defaultVal;
		}
	}
	
	public static Integer getParam(String key , Integer defaultVal){
		if(StringUtils.isNotBlank(pro.getProperty(key))){
			return Integer.parseInt(pro.getProperty(key));
		}else{
			return defaultVal;
		}
	}
	public static Long getParam(String key , Long defaultVal){
		if(StringUtils.isNotBlank(pro.getProperty(key))){
			return Long.parseLong(pro.getProperty(key));
		}else{
			return defaultVal;
		}
	}
	public static Boolean getParam(String key , Boolean defaultVal){
		if(StringUtils.isNotBlank(pro.getProperty(key))){
			return Boolean.parseBoolean(pro.getProperty(key));
		}else{
			return defaultVal;
		}
	}
}

