/**
 * Project Name:smcs
 * File Name:Authorization.java
 * Package Name:com.smcs.core.auth
 * Date:2013年9月22日下午8:35:11
 *
*/

package com.surfilter.framework.auth;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ClassName:Authorization. <br/>
 * 已授权对象.
 * Date:     2013年9月22日 下午8:35:11 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface Authorization extends Serializable {
	
	/**
	 * getSubject: 获得已授权主体. <br/>
	 *
	 * @author Tuyan
	 * @return	授权实体
	 * @since JDK 1.6
	 */
	Object getSubject();

	/**
	 * hasPermission: 判断是否拥有权限. <br/>
	 *
	 * @author Tuyan
	 * @param permission	权限表达式
	 * @return	True/False
	 * @since JDK 1.6
	 */
	boolean hasPermission(String permission);
	
	
	/**
	 * 
	* @Title: hasNoPermission 
	* @Description: TODO(判断禁用权限) 
	* @param permission
	* @return boolean    返回类型 
	* @throws 
	* @author wangguohong
	 */
	boolean hasNoPermission(String permission);
	/**
	 * getPermissions: 获取所有权限. <br/>
	 *
	 * @author Tuyan
	 * @return	permission list
	 * @since JDK 1.6
	 */
	List<String> getPermissions();
	
	/**
	 * getPermissionsMap: 获取所有权限. <br/>
	 *
	 * @author wangguohong
	 * @return	permission list
	 * @since JDK 1.6
	 */
	Map<String,List<String>> getPermissionsMap();
	
	/**
	 * addPermission: 添加一个权限到授权对象中. <br/>
	 * <pre>
	 * 权限表达式为： userToken|roleToken|urlToken|controlToken
	 * </pre>
	 * @author Tuyan
	 * @param permission	要添加到权限表达式
	 * @since JDK 1.6
	 */
	void addPermission(String permission);
	
	/**
	 * addPermissions: 添加多个权限到授权对象中. <br/>
	 * <pre>
	 * 权限表达式为： userToken|roleToken|urlToken|controlToken
	 * </pre>
	 * @author Tuyan
	 * @param permission	要添加到权限表达式
	 * @since JDK 1.6
	 */
	void addPermissions(List<String> permission);
	
	
	/** 
	* @Title: addPermissions 
	* @Description: TODO(添加权限集合 平台标示做为key) 
	* @param permissionMap    权限对象map 
	* @return void    返回类型 
	* @throws 
	* @author wangguohong
	*/
	void addPermissions(Map<String,List<String>> permissionMap);
	
	/**
	 * addPermissions: 添加多个页面元素权限到授权对象中. <br/>
	 * <pre>
	 * 权限表达式为： userToken|roleToken|urlToken|controlToken
	 * </pre>
	 * @author Tuyan
	 * @param permission	要添加到权限表达式
	 * @since JDK 1.6
	 */
	void addElementPermissions(List<String> permission);
	
	/**
	 * addPermissions: 添加多个页面元素权限到授权对象中. <br/>
	 * <pre>
	 * 权限表达式为： userToken|roleToken|urlToken|controlToken
	 * </pre>
	 * @author Tuyan
	 * @param permission	要添加到权限表达式
	 * @since JDK 1.6
	 */
	void addElementPermissions(Map<String,List<String>> permissionMap);
	
	/**
	 * getPermissions: 获取所有权限. <br/>
	 *
	 * @author Tuyan
	 * @return	permission list
	 * @since JDK 1.6
	 */
	List<String> getElementPermissions();
}

