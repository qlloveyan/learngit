package com.surfilter.smcs.utils;



import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * 公共常用工具类
 * @author xxu
 *
 */
public class CommonUtil {
	
	
	/**
	 * JSON字符串转换成对应的对象
	 * @param jsons
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToObject(String jsons,Class<T> clazz) throws Exception{
		ObjectMapper jacksonMapper = new ObjectMapper();
		return jacksonMapper.readValue(jsons,clazz);
	}
	/**
	 * 对象转换成对应的JSON字符串
	 * @param obj
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static String ObjectToJson(Object obj) throws Exception{
		ObjectMapper jacksonMapper = new ObjectMapper();
		return jacksonMapper.writeValueAsString(obj);
	}
	
	/**
	 * 
	 * valiDomain:域名格式校验. <br/>
	 *
	 * @author hongcheng
	 * @param domain 域名
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean valiDomain(String domain){
		String regex = "^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?$";
		return domain.matches(regex);
	}
	
	/**
	 * 
	 * valiDomain:域名格式校验. <br/>
	 *
	 * @author hongcheng
	 * @param domain 域名
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean valiIp(String ip){
		String regex = "^((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}$";
		return ip.matches(regex);
	}
	
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static boolean valiNum(String num){
		String regex = "^[0-9]*$";
		return num.matches(regex);
	}
}
