/**
 * Project Name:smcs
 * File Name:AuthorizationFilter.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午9:11:06
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:AuthorizationFilter. <br/>
 * Date:     2013年9月18日 上午9:11:06 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface AuthorizationFilter {

	/**
	 * doFilter: 进行过滤. <br/>
	 *
	 * @author Tuyan
	 * @param token	要过滤的东西
	 * @throws AuthorizationException	权限异常
	 * @since JDK 1.6
	 */
	void doFilter(UnauthorizedToken token) throws AuthorizationException;
	
	/**
	 * hasNextFilter: 是否有下一个过滤器. <br/>
	 *
	 * @author Tuyan
	 * @return	True/False
	 * @since JDK 1.6
	 */
	boolean hasNextFilter();
	
	/**
	 * setNextFilter: 设置下一个过滤器. <br/>
	 *
	 * @author Tuyan
	 * @param nextFilter	下一个过滤器
	 * @since JDK 1.6
	 */
	void setNextFilter(AuthorizationFilter nextFilter);
	
	/**
	 * next: 获得下一个过滤器. <br/>
	 *
	 * @author Tuyan
	 * @return	下一个过滤器
	 * @since JDK 1.6
	 */
	AuthorizationFilter next();
}

