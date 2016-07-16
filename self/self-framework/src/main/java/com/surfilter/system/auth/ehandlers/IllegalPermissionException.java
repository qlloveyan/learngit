/**
 * Project Name:smcs
 * File Name:IllegalPermissionException.java
 * Package Name:com.smcs.framework.system.auth.ehandlers
 * Date:2013年9月23日下午2:32:07
 *
*/
/**
 * Project Name:smcs
 * File Name:IllegalPermissionException.java
 * Package Name:com.smcs.framework.system.auth.ehandlers
 * Date:2013年9月23日下午2:32:07
 *
 */

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.auth.AuthorizationException;

/**
 * ClassName: IllegalPermissionException. <br/>
 * 用户权限异常，当判断为错误的用户权限时抛出这个异常.<p/>
 * date: 2013年9月23日 下午2:32:07 <br/>
 *
 * @author Tuyan
 * @version 
 * @since JDK 1.6
 */
@SuppressWarnings("serial")
public class IllegalPermissionException extends AuthorizationException {

	/**
	 * Creates a new instance of IllegalPermissionException.
	 *
	 */
	public IllegalPermissionException() {
		super();
	}

	/**
	 * Creates a new instance of IllegalPermissionException.
	 *
	 * @param message	message
	 */
	public IllegalPermissionException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of IllegalPermissionException.
	 *
	 * @param cause	cause
	 */
	public IllegalPermissionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of IllegalPermissionException.
	 *
	 * @param message	message
	 * @param cause	cause
	 */
	public IllegalPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

}

