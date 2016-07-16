/**
 * Project Name:smcs
 * File Name:NullHttpSessionExceptionHandler.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月22日下午4:34:51
 *
*/

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.web.auth.WebAuthorizationExceptionHandler;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ClassName:NullHttpSessionExceptionHandler. <br/>
 * 处理Http Session为空的异常响应<p/>
 * Date:     2013年9月22日 下午4:34:51 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationExceptionHandler
 */
public class NullHttpSessionExceptionHandler extends WebAuthorizationExceptionHandler {

	/**
	 * Creates a new instance of NullHttpSessionExceptionHandler.
	 *
	 */
	public NullHttpSessionExceptionHandler() {
		super();
	}

	@Override
	protected void handleException(WebUnauthorizedToken token) {
		//TODO 添加重定向到登录页面的代码
		token.responseRedirectURL("/");
	}
}

