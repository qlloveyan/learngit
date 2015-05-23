package com.ql.util.xml;

import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * xml操作工具类
 * @author ql
 * time : 2014年11月24日12:01:36
 */
public class XMLUtil {
	
	public static final String XML_HEAD_UTF8="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
	
	/**
	 * 将实体bean转换成xml字符串
	 * @param obj 解析的实体
	 * @param map 实体中存在的非基础数据的类集合
	 * @return 生成后的字符串
	 */
	public static String bean2xml(Object obj,Map<String,Class<?>> map){
		StringBuffer xml = new StringBuffer();
		xml.append(XML_HEAD_UTF8);
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);  
		aliasReference(map, xstream);
		xml.append( xstream.toXML(obj) );
		
		return xml.toString();
	}
	
	/**
	 * 将xml文件抓换为实体bean
	 * @param xml 获取到的xml文件内容
	 * @param map 实体中存在的非基础数据的类集合
	 * @return 转换后的实体
	 */
	public static Object xml2Bean(String xml,Map<String,Class<?>> map){
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);  
		aliasReference(map, xstream);
		return xstream.fromXML(xml);
	}
	
	public static void aliasReference(Map<String, Class<?>> map, XStream xstream) {
		if(map!=null && map.size()>0){
			Iterator<String> it=map.keySet().iterator();
			while(it.hasNext()){
				String nodeName=it.next();
				xstream.alias(nodeName, map.get(nodeName));
			}
		}
	}
}
