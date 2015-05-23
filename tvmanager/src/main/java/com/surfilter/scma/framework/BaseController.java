/**
 * Project Name:cannikin
 * File Name:BaseController.java
 * Package Name:com.smcs.core.web.ctrl
 * Date:2013-5-14下午1:55:07
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.surfilter.scma.framework;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.surfilter.scma.model.User;

/**
 * ClassName:BaseController <br/>
 * Date:     2013-5-14 下午1:55:07 <br/>
 * @author   Tkiyer
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class BaseController {

	/**
	 * logger: logger of controller.
	 * @since JDK 1.6
	 */
	protected Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 
	 * render:向客户端输出文本. <br/>
	 *
	 * @author Tkiyer
	 * @param text	输出的文本
	 * @param contentType	响应的类型
	 * @param response	响应对象
	 * @return	render text
	 * @since JDK 1.6
	 */
	protected String render(String text, String contentType, HttpServletResponse response) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 
	 * renderText:直接输出字符串. <br/>
	 *
	 * @author Tkiyer
	 * @param text	输出的文本
	 * @param response	响应对象
	 * @return	render text
	 * @since JDK 1.6
	 */
	protected String renderText(String text, HttpServletResponse response) {
		return render(text, "text/plain;charset=UTF-8", response);
	}
	
	/**
	 * 
	 * renderHtml:直接输出HTML. <br/>
	 *
	 * @author Tkiyer
	 * @param html	输出的HTML
	 * @param response	响应对象
	 * @return	render HTML
	 * @since JDK 1.6
	 */
	protected String renderHtml(String html, HttpServletResponse response) {
		return render(html, "text/html;charset=UTF-8", response);
	}
	
	/**
	 * 
	 * renderXML:直接输出XML. <br/>
	 *
	 * @author Tkiyer
	 * @param xml	输出的XML
	 * @param response	响应对象
	 * @return	render XML
	 * @since JDK 1.6
	 */
	protected String renderXML(String xml, HttpServletResponse response) {
		return render(xml, "text/xml;charset=UTF-8", response);
	}
	
	/**
	 * renderSuccess: 操作成功. <br/>
	 *
	 * @author tangbiao
	 * @return	返回Ext JS需要的json反馈对象
	 * @since JDK 1.6
	 */
	protected ExtJsObject renderSuccess() {
		return new ExtJsObject(true, "OK!");
	}
	
	/**
	 * renderObject: 返回一个对象. <br/>
	 *
	 * @author tangbiao
	 * @return	返回Ext JS需要的json反馈对象
	 * @since JDK 1.6
	 */
	protected ExtJsObject renderObject(Object obj) {
		return new ExtJsObject(true, "OK!", obj);
	}
	
	/**
	 * renderObject: 返回一个对象. <br/>
	 * @param result 操作结果
	 * @param msg 返回信息
	 * @param obj 对象
	 * 
	 * @author hongcheng
	 * @return	返回Ext JS需要的json反馈对象
	 * @since JDK 1.6
	 */
	protected ExtJsObject renderObject(boolean success,String msg,Object obj) {
		return new ExtJsObject(success, msg, obj);
	}
	
	/**
	 * 
	 * getSessionObj:获得放在http session里面的对象. <br/>
	 *
	 * @author Tkiyer
	 * @param request	请求对象
	 * @param clazz	类型
	 * @param keyName	HttpSession中的key值
	 * @param <E>	对象的类型
	 * @return	Http Session里面的对象
	 * @since JDK 1.6
	 */
	protected <E> E getSessionObj(HttpServletRequest request, Class<E> clazz, String keyName) {
		return clazz.cast(request.getSession(true).getAttribute(keyName));		
	}
	
	/**
	 * 
	 * getSessionObj:获得放在http session里面的对象. <br/>
	 *
	 * @author Tkiyer
	 * @param request	请求对象
	 * @param keyName	HttpSession中的key值
	 * @return	Http Session里面的对象
	 * @since JDK 1.6
	 */
	protected Object getSessionObj(HttpServletRequest request, String keyName) {
		return getSessionObj(request, Object.class, keyName);
	}
	
	/**
	 * 
	 * getRequestObj:获得放在http request里面的对象. <br/>
	 *
	 * @author Tkiyer
	 * @param request	请求对象
	 * @param clazz	类型
	 * @param keyName	HttpSession中的key值
	 * @param <E>	对象的类型
	 * @return	Http Request里面的对象
	 * @since JDK 1.6
	 */
	protected <E> E getRequestObj(HttpServletRequest request, Class<E> clazz, String keyName) {
		return clazz.cast(request.getAttribute(keyName));
	}
	
	/**
	 * 
	 * getRequestObj:获得放在http request里面的对象. <br/>
	 *
	 * @author Tkiyer
	 * @param request	请求对象
	 * @param keyName	HttpSession中的key值
	 * @return	Http Request里面的对象
	 * @since JDK 1.6
	 */
	protected Object getRequestObj(HttpServletRequest request, String keyName) {
		return getRequestObj(request, Object.class, keyName);
	}

	/**
	 * 
	 * getUserObj:获得当前用户. <br/>
	 *
	 * @author hongcheng
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	protected User getUserObj(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		return user;
	}
	
}

