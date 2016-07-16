/**
 * Project Name:smcs
 * File Name:AuthorizationFilterChainProxy.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午9:03:40
 *
*/
package com.surfilter.framework.web.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.auth.AuthorizationFilterChain;
import com.surfilter.framework.utils.StringUtils;
import com.surfilter.framework.web.converter.XssHttpServletRequestWrapper;

/**
 * ClassName:AuthorizationFilterChainProxy. <br/>
 * 权限过滤链。进行权限的过滤.这个是基于WEB的权限过滤器<br/>
 * Date:     2013年9月18日 上午9:03:40 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public class AuthorizationFilterChainProxy extends AuthorizationFilterChain implements Filter {
	
	/**
	 * logger: 日志记录.
	 * @since JDK 1.6
	 */
	private static Log logger = LogFactory.getLog(AuthorizationFilterChainProxy.class);
	
	/**
	 * pathMatcher: URL匹配规则.
	 * @since JDK 1.6
	 */
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	/**
	 * anonymousUrls: 可以匿名访问的URL.
	 * @since JDK 1.6
	 */
	private String[] anonymousUrls;
	
	/**
	 * hasAnonymousUrls: 有没有可以匿名访问的URL.
	 * @since JDK 1.6
	 */
	private boolean hasAnonymousUrls = false;
	
	/**
	 * printExceptionTrack: 打印异常堆栈.
	 * @since JDK 1.6
	 */
	private boolean printExceptionTrack = false;
	
	/**
	 * Creates a new instance of AuthorizationFilterChainProxy.
	 *
	 */
	public AuthorizationFilterChainProxy() {
		super();
		if (getAuthorizationExceptionHandler() == null) {
			//设置默认的权限异常处理
			setAuthorizationExceptionHandler(new DefaultWebAuthorizationExceptionHandler());
		}
	}

	/**
	 * 初始化.
	 * @param filterConfig	Filter Config
	 * @throws ServletException Servlet Exception
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	/**
	 * 过滤.
	 * @param request	Http Servlet Request
	 * @param response	Http Servlet Response
	 * @param filterChain	Filter Chain
	 * @throws IOException	IO Exception
	 * @throws ServletException	ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//防止sql注入 XSS跨站
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(  
		            (HttpServletRequest) request); 
		
		String uri = StringUtils.replace(req.getRequestURI(), req.getContextPath(), "");
		if (logger.isDebugEnabled()) {
			logger.debug("Filter uri: " + uri);
		}
		//如果有可以匿名访问的URL那么进行处理，如果没有就直接进行权限过滤
		if (this.hasAnonymousUrls) {
			if (isAnonymousUrls(uri)) {
				//如果是可以匿名访问的URL，那么不经过权限过滤链，直接访问
				filterChain.doFilter(xssRequest, response);
			} else {
				doAuthorizationFilterChain(xssRequest, resp, filterChain);
			}
		} else {
			doAuthorizationFilterChain(xssRequest, resp, filterChain);
		}
		
		 
	}
	
	/**
	 * doAuthorizationFilterChain: 执行权限过滤链. <br/>
	 *
	 * @author Tuyan
	 * @param request	Http Servlet Request
	 * @param response	Http Servlet Response
	 * @param filterChain	Filter Chain
	 * @throws IOException	IO Exception
	 * @throws ServletException	ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @since JDK 1.6
	 */
	protected void doAuthorizationFilterChain(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		//如果是不可以匿名访问的URL，那么经过权限过滤链，过滤权限
		WebUnauthorizedToken token = createAuthorizationToken(request, response);
		try {
			doFilterChain(token);
			doAuthorizationResponse(token, filterChain);
		} catch (AuthorizationException e) {
			handleException(e, token, printExceptionTrack);
		}
	}
	
	/**
	 * doAuthorizationResponse: 执行授权响应. <br/>
	 *
	 * @author Tuyan
	 * @param token	授权对象
	 * @param filterChain	Filter Chain
	 * @throws IOException	IO Exception
	 * @throws ServletException	ServletException
	 * @since JDK 1.6
	 */
	protected void doAuthorizationResponse(WebUnauthorizedToken token, FilterChain filterChain) throws IOException, ServletException {
		if (token.isRequestResponse()) {
			if (token.isRedirectResponse()) {
				token.responseRedirectURL(token.getRequestResponseURL());
			}else if(token.isRequestResponseMessage()){
				token.responseMessage(token.getRequestResponseMessage());
			} else {
				token.responseDispatchURI(token.getRequestResponseURL());
			}
		}else {
			filterChain.doFilter(token.getRequest(), token.getResponse());
		}
	}
	
	/**
	 * printExceptionTrack.
	 *
	 * @return  the printExceptionTrack
	 * @since   JDK 1.6
	 */
	public boolean isPrintExceptionTrack() {
		return printExceptionTrack;
	}

	/**
	 * printExceptionTrack.
	 *
	 * @param   printExceptionTrack    the printExceptionTrack to set
	 * @since   JDK 1.6
	 */
	public void setPrintExceptionTrack(boolean printExceptionTrack) {
		this.printExceptionTrack = printExceptionTrack;
	}

	/**
	 * anonymous urls.
	 *
	 * @return  the anonymousUrls
	 * @since   JDK 1.6
	 */
	public String[] getAnonymousUrls() {
		return anonymousUrls;
	}

	/**
	 * anonymous urls.
	 *
	 * @param   anonymousUrls    the anonymousUrls to set
	 * @since   JDK 1.6
	 */
	public void setAnonymousUrls(String[] anonymousUrls) {
		this.anonymousUrls = anonymousUrls;
		hasAnonymousUrls = !(null == this.anonymousUrls || 0 == this.anonymousUrls.length);
	}

	/**
	 * createAuthorizationToken: 创建一个权限对象. <br/>
	 *
	 * @author Tuyan
	 * @param request	http servlet request
	 * @param response	http servlet response
	 * @return	authorization token
	 * @since JDK 1.6
	 */
	private WebUnauthorizedToken createAuthorizationToken(HttpServletRequest request, HttpServletResponse response) {
		return new WebUnauthorizedToken(request, response);
	}
	
	/**
	 * isAnonymousUrls: 是否为可以匿名访问的URL. <br/>
	 *
	 * @author Tuyan
	 * @param uri	访问的URI
	 * @return	True/False
	 * @since JDK 1.6
	 */
	private boolean isAnonymousUrls(String uri) {
		boolean isAnonymousUrl = false;
		for (String anonymousUrl : anonymousUrls) {
			if (pathMatcher.match(anonymousUrl, uri)) {
				isAnonymousUrl = true;
				break;
			}
		}
		return isAnonymousUrl;
	}
	
	/**
	 * 消亡.
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {}
}

