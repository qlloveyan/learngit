/**
 * Project Name:smcs
 * File Name:UserCodeOrPasswdException.java
 * Package Name:com.smcs.framework.system.auth.ehandlers
 * Date:2013年9月23日上午10:03:48
 *
*/

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.auth.AuthorizationException;

/**
 * ClassName:UserCodeOrPasswdException. <br/>
 * 用户名或密码错误异常.
 * Date:     2013年9月23日 上午10:03:48 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("serial")
public class UserCodeOrPasswdException extends AuthorizationException {

	/**
	 * Creates a new instance of UserCodeOrPasswdException.
	 *
	 */
	public UserCodeOrPasswdException() {
		super();
	}

	/**
	 * Creates a new instance of UserCodeOrPasswdException.
	 *
	 * @param message	message
	 */
	public UserCodeOrPasswdException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of UserCodeOrPasswdException.
	 *
	 * @param cause	cause
	 */
	public UserCodeOrPasswdException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of UserCodeOrPasswdException.
	 *
	 * @param message	message
	 * @param cause	cause
	 */
	public UserCodeOrPasswdException(String message, Throwable cause) {
		super(message, cause);
	}

}

