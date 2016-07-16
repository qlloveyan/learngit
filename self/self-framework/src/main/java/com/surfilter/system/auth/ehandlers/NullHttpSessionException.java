/**
 * Project Name:smcs
 * File Name:NullHttpSessionException.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月18日上午11:08:58
 *
*/

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.auth.AuthorizationException;

/**
 * ClassName:NullHttpSessionException. <br/>
 * 在Http Session中找不到所需要的对象.
 * Date:     2013年9月18日 上午11:08:58 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("serial")
public class NullHttpSessionException extends AuthorizationException {

	/**
	 * Creates a new instance of NullHttpSessionException.
	 *
	 */
	public NullHttpSessionException() {
		super();
	}

	/**
	 * Creates a new instance of NullHttpSessionException.
	 *
	 * @param message	message
	 */
	public NullHttpSessionException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of NullHttpSessionException.
	 *
	 * @param cause	exception
	 */
	public NullHttpSessionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of NullHttpSessionException.
	 *
	 * @param message	message
	 * @param cause	exception
	 */
	public NullHttpSessionException(String message, Throwable cause) {
		super(message, cause);
	}

}

