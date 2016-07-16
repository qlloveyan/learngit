/**
 * Project Name:smcs
 * File Name:DefaultWebAuthorizationExceptionHandler.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月22日下午4:57:06
 *
*/

package com.surfilter.framework.web.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;

/**
 * ClassName:DefaultWebAuthorizationExceptionHandler. <br/>
 * 默认的WEB权限异常处理器<p/>
 * Date:     2013年9月22日 下午4:57:06 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationExceptionHandler
 */
public class DefaultWebAuthorizationExceptionHandler extends WebAuthorizationExceptionHandler {
	
	/**
	 * logger: 记录日志.
	 * @since JDK 1.6
	 */
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Creates a new instance of DefaultWebAuthorizationExceptionHandler.
	 *
	 */
	public DefaultWebAuthorizationExceptionHandler() {
		super();
	}

	@Override
	protected void handleException(WebUnauthorizedToken token) {
		if (logger.isDebugEnabled()) {
			logger.debug("重定向到登录页面！");
		}
		token.setAttribute(FrameworkGlobal.SESSION_ERROR, "用户名或密码错误！");
		token.responseRedirectURL("/" + FileUtil.getResouseValue("SYSTEM_NAME")+"?success=false");
	}
}

