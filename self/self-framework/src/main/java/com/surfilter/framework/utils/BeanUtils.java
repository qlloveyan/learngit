package com.surfilter.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Alex
 * @version $1.0, 2010-4-23 2010-4-23 下午10:06:26
 * @since JDK5
 */
public class BeanUtils implements ApplicationContextAware {
	/**
	 * Creates a new instance of BeanUtils.
	 *
	 */
	private BeanUtils(){}
	/**
	 * Spring应用上下文环境.
	 * 
	 * @since JDK 1.6
	 */
	private static ApplicationContext APPLICATIONCONTEXT;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境.
	 * 
	 * @param applicationContext
	 *            ApplicationContext
	 * @throws BeansException
	 *             BeansException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtils.APPLICATIONCONTEXT = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return APPLICATIONCONTEXT;
	}

	/**
	 * 获取对象.
	 * 
	 * @param name
	 *            bean的名称.
	 * @return Object 一个以所给名字注册的bean的实例.
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return APPLICATIONCONTEXT.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）.
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class requiredType) throws BeansException {
		return APPLICATIONCONTEXT.getBean(name, requiredType);
	}

	/**
	 * 如果还未初始化，通过该方法传入applicationContext，取得bean.
	 * 
	 * @param name
	 *            name
	 * @param ac
	 *            applicationContext
	 * @return Object bean对象
	 */
	public static Object getBean(String name, ApplicationContext ac) {
		return ac.getBean(name);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true.
	 * 
	 * @param name bean名称
	 * @return boolean 是否存在
	 */
	public static boolean containsBean(String name) {
		return APPLICATIONCONTEXT.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）.
	 * 
	 * @param name name
	 * @return boolean 存在/不存在
	 * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return APPLICATIONCONTEXT.isSingleton(name);
	}

	/**
	 * 取得注册对象的类.
	 * 
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return APPLICATIONCONTEXT.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名.
	 * 
	 * @param name bean名称
	 * @return 别名
	 * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return APPLICATIONCONTEXT.getAliases(name);
	}

	/**
	 * 获取连接对象.
	 * 
	 * @return BasicDataSource对象
	 * @throws BeansException
	 */
	public static BasicDataSource getDataSource() throws BeansException {
		return (BasicDataSource) APPLICATIONCONTEXT.getBean("sef.dataSource");
	}

	/**
	 * convertToMap:将对象转换成一个属性-属性值的对象映射，这个方法是针对标准的javabean来封装的. <br/>
	 *
	 * @author Tkiyer
	 * @param javabean	java bean object
	 * @return	map
	 * @since JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertToMap(Object javabean) {
		if (null == javabean) {
			return null;
		}
		Map<String, Object> map = null;
		try {
			map = org.apache.commons.beanutils.BeanUtils.describe(javabean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (null != map) {
			map.remove("class");
			map = clearNull(map);
		}
		return map;
	}
	
	/**
	 * clearNull:清楚map中value为null值的键值对. <br/>
	 *
	 * @author Tkiyer
	 * @param objectMap	要操作的map
	 * @return	清除之后的map
	 * @since JDK 1.6
	 */
	@SuppressWarnings("rawtypes")
	public static Map clearNull(Map objectMap) {
		Iterator keyIter = objectMap.keySet().iterator();
		while (keyIter.hasNext()) {
			Object key = keyIter.next();
			Object val = objectMap.get(key);
			if (null == val) {
				keyIter.remove();
				objectMap.remove(key);
				continue;
			}
			if (val instanceof String) {
				if ("".equals(val)) {
					keyIter.remove();
					objectMap.remove(key);
				}
			}
		}
		return objectMap;
	}
}
