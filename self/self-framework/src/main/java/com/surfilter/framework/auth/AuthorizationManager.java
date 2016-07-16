/**
 * Project Name:smcs
 * File Name:AuthorizationManager.java
 * Package Name:com.smcs.core.auth
 * Date:2013年9月22日下午8:31:35
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:AuthorizationManager. <br/>
 * 权限管理.<p/>
 * Date:     2013年9月22日 下午8:31:35 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface AuthorizationManager {
	
	/**
	 * authenticate: 根据过滤权限对象，创建已授权对象. 进行对象授权！ <br/>
	 *
	 * @author Tuyan
	 * @param token	过滤权限对象封装
	 * @return	已授权对象
	 * @throws AuthorizationException	授权异常
	 * @since JDK 1.6
	 */
	Authorization authenticate(UnauthorizedToken token) throws AuthorizationException;

	/**
	 * setPasswordEncoder: 设置密码加密类. <br/>
	 *
	 * @author Tuyan
	 * @param encoder	密码加密类
	 * @since JDK 1.6
	 */
	void setPasswordEncoder(PasswordEncoder encoder);
}

