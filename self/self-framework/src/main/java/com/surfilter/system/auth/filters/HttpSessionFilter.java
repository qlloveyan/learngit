/**
 * Project Name:smcs
 * File Name:HttpSessionFilter.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月18日上午11:07:19
 *
*/

package com.surfilter.system.auth.filters;

import com.surfilter.framework.auth.Authorization;
import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.auth.WebAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ClassName:HttpSessionFilter. <br/>
 * Date:     2013年9月18日 上午11:07:19 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationFilter
 */
public class HttpSessionFilter extends WebAuthorizationFilter {
	
	/**
	 * Creates a new instance of HttpSessionFilter.
	 *
	 */
	public HttpSessionFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		Authorization authorization = token.getAuthorization();
		
		if (null == authorization) {
			//启用响应
			token.enableRequestResponse();
			//马上进行响应
			token.disableResponseWithFilterChainDone();
			//设置响应的URL以及是否重定向
			token.setAttribute("isTimeout", "true");
			token.setRequestResponseURL("/" + FileUtil.getResouseValue("SYSTEM_NAME") + "/timeout.jsp", true);
			//throw new NullHttpSessionException("在Http Session中找不到授权对象！请重新进行授权！");
		}
	}
}

