/**
 * Project Name:smcs
 * File Name:AbstractAuthorizationFilter.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午9:42:36
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:AbstractAuthorizationFilter. <br/>
 * 抽象的权限过滤器类，所有的权限过滤器应该继承于这个类，更方便管理和控制，也可以自己去实现 {@link AuthorizationFilter}<br/>
 * Date:     2013年9月18日 上午9:42:36 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class AbstractAuthorizationFilter implements AuthorizationFilter {
	
	/**
	 * nextFilter: 下一个过滤器.
	 * @since JDK 1.6
	 */
	private AuthorizationFilter nextFilter = null;
	
	/**
	 * hasNextFilter: 是否有下一个过滤器.
	 * @since JDK 1.6
	 */
	private boolean hasNextFilter = false;

	/**
	 * Creates a new instance of AbstractAuthorizationFilter.
	 *
	 */
	public AbstractAuthorizationFilter() {
		super();
	}
	
	@Override
	public void doFilter(UnauthorizedToken token) throws AuthorizationException {
		if (beforeFilter(token)) {
			//这里这么做是为了提升程序灵活性，方便改变过滤行为，使得整体修改得到保证，而且可以不修改单个的过滤器的具体过滤逻辑
			doInternalFilter(token);
		}
		afterFilter(token);
	}

	@Override
	public boolean hasNextFilter() {
		return this.hasNextFilter;
	}

	@Override
	public void setNextFilter(AuthorizationFilter nextFilter) {
		this.nextFilter = nextFilter;
		hasNextFilter = !(null == nextFilter);
	}

	@Override
	public AuthorizationFilter next() {
		return this.nextFilter;
	}
	
	/**
	 * beforeFilter: 在过滤之前进行的操作. <br/>
	 *
	 * @author Tuyan
	 * @param token	要过滤的东西
	 * @throws AuthorizationException	权限异常
	 * @return True/False
	 * @since JDK 1.6
	 */
	protected boolean beforeFilter(UnauthorizedToken token) throws AuthorizationException {
		return true;
	}
	
	/**
	 * doInternalFilter: 主要实现过滤的逻辑，这里放空给子类去实现. <br/>
	 *
	 * @author Tuyan
	 * @param token	要过滤的东西
	 * @throws AuthorizationException	权限异常
	 * @since JDK 1.6
	 */
	protected abstract void doInternalFilter(UnauthorizedToken token) throws AuthorizationException;
	
	/**
	 * afterFilter: 在过滤之后进行的操作. <br/>
	 *
	 * @author Tuyan
	 * @param token	要过滤的东西
	 * @throws AuthorizationException	权限异常
	 * @since JDK 1.6
	 */
	protected void afterFilter(UnauthorizedToken token) throws AuthorizationException {}
}

