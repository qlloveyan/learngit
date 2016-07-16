/**
 * Project Name:smcs
 * File Name:UnauthorizedToken.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午9:09:46
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:UnauthorizedToken. <br/>
 * 要过滤的东西.
 * Date:     2013年9月18日 上午9:09:46 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface UnauthorizedToken {
	
	/**
	 * setAuthorization: 设置授权. <br/>
	 *
	 * @author Tuyan
	 * @param authorization	已授权对象
	 * @since JDK 1.6
	 */
	void setAuthorization(Authorization authorization);
	
	/**
	 * getAuthorization: 获得授权. <br/>
	 *
	 * @author Tuyan
	 * @return	已授权对象
	 * @since JDK 1.6
	 */
	Authorization getAuthorization();

	/**
	 * getToken:获得权限的枚举实体. <br/>
	 *
	 * @author Tuyan
	 * @param tokenName	token名称
	 * @param clazz	token类型
	 * @param <T>	泛型
	 * @return	token对象
	 * @since JDK 1.6
	 */
	<T> T getAttribute(String tokenName, Class<T> clazz);
	
	/**
	 * getToken:获得权限的枚举实体. <br/>
	 *
	 * @author Tuyan
	 * @param tokenName	token名称
	 * @return	token对象
	 * @since JDK 1.6
	 */
	Object getAttribute(String tokenName);
	
	/**
	 * setAttribute: 设置权限属性. <br/>
	 *
	 * @author Tuyan
	 * @param tokenName		属性的名称
	 * @param tokenValue	属性的值
	 * @since JDK 1.6
	 */
	void setAttribute(String tokenName, Object tokenValue);
	
	/**
	 * removeAttribute: 移除一个属性. <br/>
	 *
	 * @author Tuyan
	 * @param tokenName	属性的名称
	 * @since JDK 1.6
	 */
	void removeAttribute(String tokenName);
	
	/**
	 * clearAttributes: 清空所有属性. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	void clearAttributes();
	
	/**
	 * getParameter: 获取一个指定的参数. <br/>
	 *
	 * @author Tuyan
	 * @param parameterName	参数名称
	 * @param clazz	参数类型
	 * @param <T>	泛型
	 * @return	参数
	 * @since JDK 1.6
	 */
	<T> T getParameter(String parameterName, Class<T> clazz);
	
	/**
	 * getParameter: 获取一个指定的参数. <br/>
	 *
	 * @author Tuyan
	 * @param parameterName	参数名称
	 * @return	参数
	 * @since JDK 1.6
	 */
	String getParameter(String parameterName);
	
	/**
	 * setParameter: 设置一个参数. <br/>
	 *
	 * @author Tuyan
	 * @param parameterName		参数的名称
	 * @param parameterValue	参数的值
	 * @since JDK 1.6
	 */
	void setParameter(String parameterName, Object parameterValue);
	
	/**
	 * removeParameter: 移除一个参数. <br/>
	 *
	 * @author Tuyan
	 * @param parameterName	参数的名称
	 * @since JDK 1.6
	 */
	void removeParameter(String parameterName);
	
	/**
	 * clearParameters: 清空所有参数. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	void clearParameters();
}

