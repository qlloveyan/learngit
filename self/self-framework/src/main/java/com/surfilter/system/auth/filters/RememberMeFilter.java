/**
 * Project Name:smcs
 * File Name:RememberMeFilter.java
 * Package Name:com.smcs.framework.system.auth.filters
 * Date:2013年9月22日下午7:01:13
 *
*/

package com.surfilter.system.auth.filters;

import com.surfilter.framework.auth.Authorization;
import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.auth.AuthorizationManager;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.auth.URLAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;
import com.surfilter.system.auth.ehandlers.UserCodeOrPasswdException;

/**
 * ClassName:RememberMeFilter. <br/>
 * 权限登录过滤器.<p/>
 * Date:     2013年9月22日 下午7:01:13 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 URLAuthorizationFilter
 */
public class RememberMeFilter extends URLAuthorizationFilter {
	
	/**
	 * authorizationManager: 授权管理.
	 * @since JDK 1.6
	 */
	private AuthorizationManager authorizationManager;

	/**
	 * Creates a new instance of RememberMeFilter.
	 *
	 */
	public RememberMeFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		String[] message;
		Authorization authorization = null;
		String responseURL = "/" + FileUtil.getResouseValue("SYSTEM_NAME") + "/index.jsp";
		try {
			authorization = authorizationManager.authenticate(token);
		} catch (UserCodeOrPasswdException e) {
			//System.out.println(e.getMessage());
			message = e.getMessage().split("#");
			token.setAttribute("username", message[0]);
			token.setAttribute("errorType", message[1]);
			token.setAttribute("message", message[2]);
			responseURL = "/" + FileUtil.getResouseValue("SYSTEM_NAME") + "/system/user/loginFail.do";
		}
		token.setAuthorization(authorization);
		//启用响应
		token.enableRequestResponse();
		//马上进行响应
		token.disableResponseWithFilterChainDone();
		//设置响应的URL以及是否重定向
		token.setRequestResponseURL(responseURL, true);
	}

	/**
	 * authorizationManager.
	 *
	 * @return  the authorizationManager
	 * @since   JDK 1.6
	 */
	public AuthorizationManager getAuthorizationManager() {
		return authorizationManager;
	}

	/**
	 * authorizationManager.
	 *
	 * @param   authorizationManager    the authorizationManager to set
	 * @since   JDK 1.6
	 */
	public void setAuthorizationManager(AuthorizationManager authorizationManager) {
		this.authorizationManager = authorizationManager;
	}
}

