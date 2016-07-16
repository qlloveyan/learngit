/**
 * Project Name:smcs
 * File Name:AuthorizationException.java
 * Package Name:com.smcs.core.auth
 * Date:2013年9月18日上午10:58:20
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:AuthorizationException. <br/>
 * 权限验证异常.<br/>
 * Date:     2013年9月18日 上午10:58:20 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("serial")
public class AuthorizationException extends Exception {

	/**
	 * Creates a new instance of AuthorizationException.
	 *
	 */
	public AuthorizationException() {
		super();
	}

	/**
	 * Creates a new instance of AuthorizationException.
	 *
	 * @param message	message
	 */
	public AuthorizationException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of AuthorizationException.
	 *
	 * @param cause	exception
	 */
	public AuthorizationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of AuthorizationException.
	 *
	 * @param message	message
	 * @param cause	exception
	 */
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}

