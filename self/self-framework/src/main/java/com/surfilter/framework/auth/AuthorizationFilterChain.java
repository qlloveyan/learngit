/**
 * Project Name:smcs
 * File Name:AuthorizationFilterChainProxy.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午9:03:40
 *
*/

package com.surfilter.framework.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:AuthorizationFilterChainProxy. <br/>
 * 权限过滤链。进行权限的过滤.<br/>
 * Date:     2013年9月18日 上午9:03:40 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public class AuthorizationFilterChain {
	
	/**
	 * logger: 记录日志.
	 * @since JDK 1.6
	 */
	private static Log logger = LogFactory.getLog(AuthorizationFilterChain.class);
	
	/**
	 * authorizationFilters: 所有的权限过滤器.
	 * @since JDK 1.6
	 */
	private List<AuthorizationFilter> authorizationFilters = new ArrayList<AuthorizationFilter>();
	
	/**
	 * exceptionHandlers: 权限异常处理器.
	 * @since JDK 1.6
	 */
	private Map<String, AuthorizationExceptionHandler> exceptionHandlers = new HashMap<String, AuthorizationExceptionHandler>();
	
	/**
	 * authorizationExceptionHandler: 全局权限异常处理器，如果这个属性被初始化了，那么当不在权限异常处理器集合中的权限异常被抛出的时候将执行这个类进行权限异常处理.
	 * @since JDK 1.6
	 */
	private AuthorizationExceptionHandler authorizationExceptionHandler;
	
	/**
	 * Creates a new instance of AuthorizationFilterChainProxy.
	 *
	 */
	public AuthorizationFilterChain() {
		super();
	}
	
	/**
	 * doFilterChain: 执行过滤器链. <br/>
	 *
	 * @author Tuyan
	 * @param token	权限枚举对象
	 * @exception AuthorizationException	权限异常
	 * @since JDK 1.6
	 */
	public void doFilterChain(UnauthorizedToken token) throws AuthorizationException {
		if (hasFilters()) {
			AuthorizationFilter filter = authorizationFilters.get(0);
			filter.doFilter(token);
			while (filter.hasNextFilter()) {
				filter = filter.next();
				filter.doFilter(token);
			}
		} else {
			//过滤器链中没有过滤器，只给出警告，不给出异常
			logger.warn("过滤器链中没有过滤器，不会进行过滤!");
		}
	}
	
	/**
	 * authorization filters.
	 *
	 * @return  the authorizationFilters
	 * @since   JDK 1.6
	 */
	public List<AuthorizationFilter> getAuthorizationFilters() {
		return authorizationFilters;
	}

	/**
	 * authorization filters.
	 *
	 * @param   authorizationFilters    the authorizationFilters to set
	 * @since   JDK 1.6
	 */
	public void setAuthorizationFilters(List<AuthorizationFilter> authorizationFilters) {
		this.authorizationFilters = authorizationFilters;
		initFilterChain();
	}
	
	/**
	 * exceptionHandlers.
	 *
	 * @return  the exceptionHandlers
	 * @since   JDK 1.6
	 */
	public Map<String, AuthorizationExceptionHandler> getExceptionHandlers() {
		return exceptionHandlers;
	}


	/**
	 * exceptionHandlers.
	 *
	 * @param   exceptionHandlers    the exceptionHandlers to set
	 * @since   JDK 1.6
	 */
	public void setExceptionHandlers(Map<String, AuthorizationExceptionHandler> exceptionHandlers) {
		this.exceptionHandlers = exceptionHandlers;
	}

	/**
	 * authorizationExceptionHandler.
	 *
	 * @return  the authorizationExceptionHandler
	 * @since   JDK 1.6
	 */
	public AuthorizationExceptionHandler getAuthorizationExceptionHandler() {
		return authorizationExceptionHandler;
	}

	/**
	 * authorizationExceptionHandler.
	 *
	 * @param   authorizationExceptionHandler    the authorizationExceptionHandler to set
	 * @since   JDK 1.6
	 */
	public void setAuthorizationExceptionHandler(AuthorizationExceptionHandler authorizationExceptionHandler) {
		this.authorizationExceptionHandler = authorizationExceptionHandler;
	}


	/**
	 * initFilterChain: 初始化过滤链. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	protected void initFilterChain() {
		if (!hasFilters()) {
			return;
		}
		for (int i = 0; i < authorizationFilters.size() - 1; i++) {
			AuthorizationFilter filter = authorizationFilters.get(i);
			filter.setNextFilter(authorizationFilters.get((i + 1)));
		}
	}
	
	/**
	 * hasFilters: 判断过滤器链中有没有过滤器. <br/>
	 *
	 * @author Tuyan
	 * @return	True/False
	 * @since JDK 1.6
	 */
	public boolean hasFilters() {
		return !authorizationFilters.isEmpty();
	}
	
	/**
	 * handleException: 处理异常. <br/>
	 *
	 * @author Tuyan
	 * @param exception	要处理的异常
	 * @param token	权限对象封装
	 * @since JDK 1.6
	 */
	protected void handleException(AuthorizationException exception, UnauthorizedToken token) {
		handleException(exception, token, false);
	}
	
	/**
	 * handleException: 处理异常. <br/>
	 *
	 * @author Tuyan
	 * @param exception	要处理的异常
	 * @param token	权限对象封装
	 * @param isPrintTrack	是否打印异常堆栈到控制台
	 * @since JDK 1.6
	 */
	protected void handleException(AuthorizationException exception, UnauthorizedToken token, boolean isPrintTrack) {
		logger.error(exception);
		if (isPrintTrack) {
			exception.printStackTrace();
		}
		AuthorizationExceptionHandler handler = getExceptionHandler(exception);
		if (null == handler) {
			if (null != this.authorizationExceptionHandler) {
				this.authorizationExceptionHandler.handleException(token);
			}
		} else {
			handler.handleException(token);
		}
	}
	
	/**
	 * addAuthorizationFilter: 添加一个权限过滤器到权限过滤器链中. <br/>
	 *
	 * @author Tuyan
	 * @param filter	权限过滤器
	 * @since JDK 1.6
	 */
	public void addAuthorizationFilter(AuthorizationFilter filter) {
		if (!hasFilters()) {
			this.authorizationFilters.add(filter);
		} else {
			//取出最后一个过滤器，将其的下一个过滤器设置为当前要注册的过滤器
			AuthorizationFilter lastFilter = this.authorizationFilters.get(this.authorizationFilters.size() - 1);
			lastFilter.setNextFilter(filter);
		}
	}
	
	/**
	 * registerExceptionHandler: 注册异常处理类. <br/>
	 *
	 * @author Tuyan
	 * @param exception	异常类
	 * @param handler	异常处理类
	 * @since JDK 1.6
	 */
	public void registerExceptionHandler(Class<? extends AuthorizationException> exception, AuthorizationExceptionHandler handler) {
		registerExceptionHandler(exception.getName(), handler);
	}
	
	/**
	 * registerExceptionHandler: 注册异常处理类. <br/>
	 *
	 * @author Tuyan
	 * @param exceptionName	异常类全名称
	 * @param handler	异常处理类
	 * @since JDK 1.6
	 */
	public void registerExceptionHandler(String exceptionName, AuthorizationExceptionHandler handler) {
		exceptionHandlers.put(exceptionName, handler);
	}
	
	/**
	 * getExceptionHandler: 获取权限异常处理类. <br/>
	 *
	 * @author Tuyan
	 * @param exception	权限异常类
	 * @return	authorization exception handler
	 * @since JDK 1.6
	 */
	public AuthorizationExceptionHandler getExceptionHandler(AuthorizationException exception) {
		return getExceptionHandler(exception.getClass().getName());
	}
	
	/**
	 * getExceptionHandler: 获取权限异常处理类. <br/>
	 *
	 * @author Tuyan
	 * @param exceptionName	权限异常名称
	 * @return	authorization exception handler
	 * @since JDK 1.6
	 */
	public AuthorizationExceptionHandler getExceptionHandler(String exceptionName) {
		return exceptionHandlers.get(exceptionName);
	}
}

