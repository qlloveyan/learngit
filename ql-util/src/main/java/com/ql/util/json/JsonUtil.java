/**
 * Project Name:cherry-chain-http
 * File Name:JsonUtil.java
 * Package Name:org.cherry.application.chain.http.util
 * Date:2013年10月24日上午10:59:46
 *
*/

package com.ql.util.json;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;




/**
 * ClassName:JsonUtil. <br/>
 * JSON工具类.<p/>
 * Date:     2013年10月24日 上午10:59:46 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 */
public abstract class JsonUtil {

	/**
	 * readObject: 从json字符串中读取一个对象. <br/>
	 *
	 * @author Tuyan
	 * @param json	需要转换成对象的json字符串
	 * @param clazz	要读取的对象类型
	 * @return
	 * @since JDK 1.6
	 */
	public static <T> T readObject(String json, Class<T> clazz) {
		if (null == json || "".equals(json)) {
			return null;
		}
		Object obj = null;
		ObjectMapper om = new ObjectMapper();
		ObjectReader reader = om.reader(clazz);
		
		reader.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			obj = reader.readValue(json);
			return clazz.cast(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * writeObject: 将一个对象转换成json字符串. <br/>
	 *
	 * @author Tuyan
	 * @param obj	要转换成json的对象
	 * @return	转换之后的json字符串
	 * @since JDK 1.6
	 */
	public static String writeObject(Object obj) {
		if (null == obj) {
			return null;
		}
		String json = null;
		ObjectMapper om = new ObjectMapper();
		ObjectWriter writer = om.writer();
		try {
			json = writer.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
		return json;
	}
	
	public static <T> List<T> convertListObject(String jsons,TypeReference typeRefe) throws Exception{
		ObjectMapper jacksonMapper = new ObjectMapper();
		return jacksonMapper.readValue(jsons, typeRefe);

	}
}

