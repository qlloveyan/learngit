/**
 * Project Name:lichen
 * File Name:DestoryJSPListener.java
 * Package Name:com.surfilter.system.auth
 * Date:2015年1月26日下午5:47:04
 *
*/

package com.surfilter.system.auth;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ClassName:DestoryJSPListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月26日 下午5:47:04 <br/>
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DestorySessionListener implements HttpSessionListener,
		HttpSessionAttributeListener{
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
	
}

