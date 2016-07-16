/**
 * Project Name:smcs
 * File Name:WebAuthorizationFilter.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月22日下午6:42:00
 *
*/

package com.surfilter.framework.web.auth;

import com.surfilter.framework.auth.AbstractAuthorizationFilter;
import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.auth.UnauthorizedToken;

/**
 * ClassName:WebAuthorizationFilter. <br/>
 * WEB的权限过滤器<p/>
 * Date:     2013年9月22日 下午6:42:00 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 AbstractAuthorizationFilter
 */
public abstract class WebAuthorizationFilter extends AbstractAuthorizationFilter {

	/**
	 * Creates a new instance of WebAuthorizationFilter.
	 *
	 */
	public WebAuthorizationFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(UnauthorizedToken token) throws AuthorizationException {
		doInternalFilter((WebUnauthorizedToken) token);
		
	}

	@Override
	protected boolean beforeFilter(UnauthorizedToken token) throws AuthorizationException {
		WebUnauthorizedToken t = (WebUnauthorizedToken) token;
		//判断是否有请求响应的过滤器，如果有，并且需要马上进行响应，那么就不执行下一个过滤器
		if (t.isRequestResponse() && !t.isResponseWithFilterChainDone()) {
			return false;
		}
		return beforeFilter(t);
	}

	@Override
	protected void afterFilter(UnauthorizedToken token) throws AuthorizationException {
		afterFilter((WebUnauthorizedToken) token);
	}

	/**
	 * beforeFilter:过滤之前. <br/>
	 *
	 * @author Tuyan
	 * @param token	权限对象封装
	 * @throws AuthorizationException	权限对象
	 * @return True/False
	 * @since JDK 1.6
	 * @see	#beforeFilter(UnauthorizedToken)
	 */
	protected boolean beforeFilter(WebUnauthorizedToken token) throws AuthorizationException {
		return true;
	}
	
	/**
	 * afterFilter:过滤之后. <br/>
	 *
	 * @author Tuyan
	 * @param token	权限对象封装
	 * @throws AuthorizationException	权限对象
	 * @since JDK 1.6
	 * @see	#afterFilter(UnauthorizedToken)
	 */
	protected void afterFilter(WebUnauthorizedToken token) throws AuthorizationException {}
	
	/**
	 * doInternalFilter: 进行过滤验证. <br/>
	 *
	 * @author Tuyan
	 * @param token	权限对象封装
	 * @throws AuthorizationException	权限异常
	 * @since JDK 1.6
	 */
	protected abstract void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException;
}

