/**
 * Project Name:smcs
 * File Name:IllegalURLExceptionHandler.java
 * Package Name:com.smcs.framework.system.auth.ehandlers
 * Date:2013年9月22日下午4:38:30
 *
*/

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.web.auth.WebAuthorizationExceptionHandler;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ClassName:IllegalURLExceptionHandler. <br/>
 * 错误的URL处理.<p/>
 * Date:     2013年9月22日 下午4:38:30 <br/>
 * @author   Tuyan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class IllegalURLExceptionHandler extends WebAuthorizationExceptionHandler {

	/**
	 * Creates a new instance of IllegalURLExceptionHandler.
	 *
	 */
	public IllegalURLExceptionHandler() {
		super();
	}

	@Override
	protected void handleException(WebUnauthorizedToken token) {
		//TODO 添加重定向到错误的请求页面的方法
		token.responseRedirectURL("");
	}
}

