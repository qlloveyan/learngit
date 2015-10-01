package com.ql.basepro.framework.excel;

import java.lang.reflect.Field;

/**
 * 
 * ClassName: ReflectUtil <br/>
 * Function:反射工具类. <br/>
 * date: 2013年9月18日 下午2:52:45 <br/>
 *
 * @author hongcheng
 * @version 
 * @since JDK 1.6
 */
public class ReflectUtil {
	
	/**
	 * 
	 * getClassField:获得类的字段. <br/>
	 *
	 * @author hongcheng
	 * @param obj 对象
	 * @param name 属性名称
	 * @return 对象
	 * @throws Exception 异常信息
	 * @since JDK 1.6
	 */
	public static Object getClassField(Object obj, String name) throws Exception {
		Field field = getField(obj.getClass(), name);
		return field.get(obj);
	}
	
	/**
	 * 
	 * setClassField:设置类的字段. <br/>
	 *
	 * @author hongcheng
	 * @param obj 对象
	 * @param name 属性名称
	 * @param value 属性值
	 * @throws Exception 异常信息
	 * @since JDK 1.6
	 */
	public static void setClassField(Object obj, String name, Object value) throws Exception {
		Field field = getField(obj.getClass(), name);
		field.set(obj, value);
	}
	
	/**
	 * 
	 * getSuperClassField:获取父类字段信息. <br/>
	 *
	 * @author hongcheng
	 * @param obj 对象
	 * @param name 属性名称
	 * @return 对象
	 * @throws Exception 异常信息
	 * @since JDK 1.6
	 */
	public static Object getSuperClassField(Object obj, String name) throws Exception {
		Field field = getField(obj.getClass().getSuperclass(), name);
		return field.get(obj);
	}
	
	/**
	 * 
	 * getField:获取字段信息. <br/>
	 *
	 * @author hongcheng
	 * @param clazz 类
	 * @param name 属性名称
	 * @return 字段
	 * @throws Exception 异常信息
	 * @since JDK 1.6
	 */
	private static Field getField(Class<?> clazz, String name) throws Exception {
		Field field = null;
		for(Field f : clazz.getDeclaredFields()) {
			if(f.getName().equals(name)) {
				f.setAccessible(true);
				field = f;
			}
		}
		return field;
	}

	/**
	 * getClassField:(获取字段 包括父类). <br/>
	 *
	 * @author wangguohong
	 * @param aClazz
	 * @param aFieldName
	 * @return
	 * @since JDK 1.6
	 */
	public static Field getAllClassField(Class aClazz, String aFieldName) {
		Field[] declaredFields = aClazz.getDeclaredFields();
		for (Field field : declaredFields) {
			// 注意：这里判断的方式，是用字符串的比较。很傻瓜，但能跑。要直接返回Field。我试验中，尝试返回Class，然后用getDeclaredField(String
			if (field.getName().equals(aFieldName)) {
				return field;// define in this class
			}
		}

		Class superclass = aClazz.getSuperclass();
		if (superclass != null) {// 简单的递归一下
			return getAllClassField(superclass, aFieldName);
		}
		return null;
	}
}