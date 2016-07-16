/**
 * Project Name:smcs
 * File Name:URLAuthorizationFilter.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月22日下午6:46:26
 *
*/

package com.surfilter.framework.web.auth;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.utils.StringUtils;

/**
 * ClassName:URLAuthorizationFilter. <br/>
 * URL匹配的过滤器<p/>
 * Date:     2013年9月22日 下午6:46:26 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationFilter
 */
public abstract class URLAuthorizationFilter extends WebAuthorizationFilter {
	
	/**
	 * url: 过滤器相匹配的URL.
	 * @since JDK 1.6
	 */
	private String url;
	
	/**
	 * pathMatcher: URL路径匹配器.
	 * @since JDK 1.6
	 */
	private PathMatcher pathMatcher = new AntPathMatcher();

	/**
	 * Creates a new instance of URLAuthorizationFilter.
	 *
	 */
	public URLAuthorizationFilter() {
		super();
	}

	/**
	 * 在过滤之前先匹配URL，如果不是所匹配的URL则不执行过滤器.
	 * @param token	权限对象封装
	 * @throws AuthorizationException	权限异常
	 * @return True/False
	 * @see com.surfilter.framework.auth.AbstractAuthorizationFilter#beforeFilter(com.surfilter.framework.auth.UnauthorizedToken)
	 */
	@Override
	protected boolean beforeFilter(WebUnauthorizedToken token) throws AuthorizationException {
		if (StringUtils.isEmpty(url)) {
			//如果URL为空，那么回滚，不执行下面过滤的方法
			return false;
		}
		if (!pathMatcher.match(url, token.getURI())) {
			return false;
		}
		return true;
	}

	/**
	 * url.
	 *
	 * @return  the url
	 * @since   JDK 1.6
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * url.
	 *
	 * @param   url    the url to set
	 * @since   JDK 1.6
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}

