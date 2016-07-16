/**
 * Project Name:smcs
 * File Name:AuthorizationExceptionHandler.java
 * Package Name:com.smcs.core.auth
 * Date:2013年9月22日下午4:09:11
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:AuthorizationExceptionHandler. <br/>
 * 权限异常处理<p/>
 * 
 * Date:     2013年9月22日 下午4:09:11 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface AuthorizationExceptionHandler {

	/**
	 * handleException: 处理权限异常. <br/>
	 *
	 * @author Tuyan
	 * @param token	要处理的权限信息封装
	 * @since JDK 1.6
	 */
	void handleException(UnauthorizedToken token);
}

