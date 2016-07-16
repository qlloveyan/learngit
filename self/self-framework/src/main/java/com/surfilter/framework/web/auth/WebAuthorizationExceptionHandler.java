/**
 * Project Name:smcs
 * File Name:WebAuthorizationExceptionHandler.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月22日下午4:31:31
 *
*/

package com.surfilter.framework.web.auth;

import com.surfilter.framework.auth.AuthorizationExceptionHandler;
import com.surfilter.framework.auth.UnauthorizedToken;

/**
 * ClassName:WebAuthorizationExceptionHandler. <br/>
 * WEB的权限异常处理<p/>
 * Date:     2013年9月22日 下午4:31:31 <br/>
 * @author   Tuyan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class WebAuthorizationExceptionHandler implements AuthorizationExceptionHandler {

	/**
	 * Creates a new instance of WebAuthorizationExceptionHandler.
	 *
	 */
	public WebAuthorizationExceptionHandler() {
		super();
	}

	@Override
	public void handleException(UnauthorizedToken token) {
		//为了让子类直接使用Web的AuthorizationToken对象，所以抽象出一个方法
		handleException((WebUnauthorizedToken) token);
	}
	
	/**
	 * handleException: 权限异常处理实际响应方法，叫给子类去实现. <br/>
	 *
	 * @author Tuyan
	 * @param token	权限对象封装
	 * @since JDK 1.6
	 */
	protected abstract void handleException(WebUnauthorizedToken token);
}

