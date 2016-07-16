/**
 * Project Name:smcs
 * File Name:WebUnauthorizedToken.java
 * Package Name:com.smcs.core.web.auth
 * Date:2013年9月18日上午10:00:53
 *
*/

package com.surfilter.framework.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.auth.Authorization;
import com.surfilter.framework.auth.UnauthorizedToken;
import com.surfilter.framework.utils.StringUtils;

/**
 * ClassName:WebUnauthorizedToken. <br/>
 * WEB权限对象封装.<p/>
 * Date:     2013年9月18日 上午10:00:53 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 UnauthorizedToken
 */
public class WebUnauthorizedToken implements UnauthorizedToken {
	
	/**
	 * request: 用户请求.
	 * @since JDK 1.6
	 */
	private HttpServletRequest request;
	
	/**
	 * response: 用户响应.
	 * @since JDK 1.6
	 */
	private HttpServletResponse response;
	
	/**
	 * isRequestResponse: 是否请求响应.
	 * @since JDK 1.6
	 */
	private boolean isRequestResponse = false;
	
	/**
	 * isRequestResponse: 是否请求响应.
	 * @since JDK 1.6
	 */
	private boolean isRequestResponseMessage = false;
	
	/**
	 * isResponseWithFilterChainDone: 是否在过滤器执行完毕之后请求响应.
	 * @since JDK 1.6
	 */
	private boolean isResponseWithFilterChainDone = true;
	
	/**
	 * requestResponseURL: 请求响应的地址.
	 * @since JDK 1.6
	 */
	private String requestResponseURL;
	
	/**
	 * requestResponseMessage: 请求响应的信息.
	 * @since JDK 1.6
	 */
	private String requestResponseMessage;
	
	/**
	 * isRedirectResponse: 是否为重定向响应.
	 * @since JDK 1.6
	 */
	private boolean isRedirectResponse;
	
	/**
	 * Creates a new instance of WebUnauthorizedToken.
	 *
	 * @param request	用户请求
	 * @param response	用户响应
	 */
	public WebUnauthorizedToken(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 从Http Session中获取对应属性名称的对象.
	 * @param tokenName	属性名称
	 * @param clazz	对象类型
	 * @param <T>	对象泛型
	 * @return	http session attr token object
	 * @see com.surfilter.framework.auth.UnauthorizedToken#getAttribute(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T getAttribute(String tokenName, Class<T> clazz) {
		Object obj = request.getSession(true).getAttribute(tokenName);
		return clazz.cast(obj);
	}

	/**
	 * 从Http Session中获取对应属性名称的对象.
	 * @param tokenName	属性名称
	 * @return	http session attr token object
	 * @see com.surfilter.framework.auth.UnauthorizedToken#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String tokenName) {
		return request.getSession(true).getAttribute(tokenName);
	}

	@Override
	public void setAttribute(String tokenName, Object tokenValue) {
		request.getSession(true).setAttribute(tokenName, tokenValue);
	}

	@Override
	public void removeAttribute(String tokenName) {
		request.getSession(true).removeAttribute(tokenName);
	}

	@Override
	public void clearAttributes() {
		request.getSession(true).invalidate();
	}

	/**
	 * getURI: 获得访问的相对路径URI. <br/>
	 *
	 * @author Tuyan
	 * @return	URI
	 * @since JDK 1.6
	 */
	public String getURI() {
		return StringUtils.replace(request.getRequestURI(), request.getContextPath(), "");
	}
	
	/**
	 * requestResponseURL.
	 *
	 * @return  the requestResponseURL
	 * @since   JDK 1.6
	 */
	public String getRequestResponseURL() {
		return requestResponseURL;
	}

	/**
	 * requestResponseMessage.
	 *
	 * @return  the requestResponseMessage
	 * @since   JDK 1.6
	 */
	public String getRequestResponseMessage() {
		return requestResponseMessage;
	}


	/**
	 * requestResponseURL.
	 *
	 * @param   requestResponseURL    the requestResponseURL to set
	 * @param	isRedirectResponse	是否为重定向
	 * @since   JDK 1.6
	 */
	public void setRequestResponseURL(String requestResponseURL, boolean isRedirectResponse) {
		this.requestResponseURL = requestResponseURL;
		this.isRedirectResponse = isRedirectResponse;
	}
	
	/**
	 * requestResponseURL.
	 *
	 * @param   requestResponseMessage    the requestResponseMessage to set
	 * @param	isRedirectResponse	是否为重定向
	 * @since   JDK 1.6
	 */
	public void setRequestResponseMessage(String requestResponseMessage, boolean isRequestResponseMessage) {
		this.requestResponseMessage = requestResponseMessage;
		this.isRequestResponseMessage = isRequestResponseMessage;
	}
	
	/**
	 * 是否为重定向到响应.
	 *
	 * @return  the isRedirectResponse
	 * @since   JDK 1.6
	 */
	public boolean isRedirectResponse() {
		return isRedirectResponse;
	}

	
	public boolean isRequestResponseMessage() {
		return isRequestResponseMessage;
	}


	/**
	 * responseRedirectURL: 响应一个重定向的地址. <br/>
	 *
	 * @author Tuyan
	 * @param url	要响应的地址
	 * @since JDK 1.6
	 */
	public void responseRedirectURL(String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * message: 响应信息. <br/>
	 *
	 * @author Tuyan
	 * @param message	响应信息
	 * @since JDK 1.6
	 */
	public void responseMessage(String message) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 是否请求响应.
	 *
	 * @return  the isRequestResponse
	 * @since   JDK 1.6
	 */
	public boolean isRequestResponse() {
		return isRequestResponse;
	}

	/**
	 * 是否在过滤器执行完毕之后请求响应.
	 *
	 * @return  the isResponseWithFilterChainDone
	 * @since   JDK 1.6
	 */
	public boolean isResponseWithFilterChainDone() {
		return isResponseWithFilterChainDone;
	}

	/**
	 * enableRequestResponse: 启用响应. <br/>
	 *
	 * @author Tuyan
	 * @since   JDK 1.6
	 */
	public void enableRequestResponse() {
		this.isRequestResponse = true;
	}
	
	/**
	 * disableRequestResponse: 禁用响应. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	public void disableRequestResponse() {
		this.isRequestResponse = false;
	}

	/**
	 * enableResponseWithFilterChainDone: 启用在过滤器执行完之后马上进行响应而不执行下面的过滤器. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	public void enableResponseWithFilterChainDone() {
		this.isResponseWithFilterChainDone = true;
	}
	
	/**
	 * disableResponseWithFilterChainDone: 禁用在过滤器执行完之后马上进行响应而不执行下面的过滤器.<br/> 
	 * 如果执行了这个方法，那么会继续执行下面的过滤器，直到执行完所有的过滤器才会进行响应. <br/>
	 *
	 * @author Tuyan
	 * @since JDK 1.6
	 */
	public void disableResponseWithFilterChainDone() {
		this.isResponseWithFilterChainDone = false;
	}
	
	/**
	 * responseDispatchURI: 响应一个相对当前URL的一个URI地址. <br/>
	 *
	 * @author Tuyan
	 * @param uri	相对路径
	 * @since JDK 1.6
	 */
	public void responseDispatchURI(String uri) {
		try {
			request.getRequestDispatcher(uri).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public <T> T getParameter(String parameterName, Class<T> clazz) {
		String param = request.getParameter(parameterName);
		return clazz.cast(param);
	}

	@Override
	public String getParameter(String parameterName) {
		return request.getParameter(parameterName);
	}

	@Override
	public void setParameter(String parameterName, Object parameterValue) {
		throw new UnsupportedOperationException("不允许该操作！");
	}
	
	@Override
	public void removeParameter(String parameterName) {
		throw new UnsupportedOperationException("不允许该操作！");
	}

	@Override
	public void clearParameters() {
		throw new UnsupportedOperationException("不允许该操作！");
	}
	
	@Override
	public void setAuthorization(Authorization authorization) {
		request.getSession(true).setAttribute(FrameworkGlobal.AUTHORIZATION_TOKEN, authorization);
	}
	
	@Override
	public Authorization getAuthorization() {
		return (Authorization) request.getSession(true).getAttribute(FrameworkGlobal.AUTHORIZATION_TOKEN);
	}

	/**
	 * request.
	 *
	 * @return  the request
	 * @since   JDK 1.6
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	
	/**
	 * request.
	 *
	 * @return  the request
	 * @since   JDK 1.6
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * response.
	 *
	 * @return  the response
	 * @since   JDK 1.6
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	
	
}

