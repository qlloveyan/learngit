/**
 * Project Name:smcs
 * File Name:IllegalURLException.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月22日下午2:26:09
 *
*/
/**
 * Project Name:smcs
 * File Name:IllegalURLException.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月22日下午2:26:09
 *
 */

package com.surfilter.system.auth.ehandlers;

import com.surfilter.framework.auth.AuthorizationException;

/**
 * ClassName:IllegalURLException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013年9月22日 下午2:26:09 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 AuthorizationException
 */
@SuppressWarnings("serial")
public class IllegalURLException extends AuthorizationException {

	/**
	 * Creates a new instance of IllegalURLException.
	 *
	 */

	public IllegalURLException() {
		super();
	}

	/**
	 * Creates a new instance of IllegalURLException.
	 *
	 * @param message	message
	 */

	public IllegalURLException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of IllegalURLException.
	 *
	 * @param cause	cause
	 */

	public IllegalURLException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of IllegalURLException.
	 *
	 * @param message	message
	 * @param cause	cause
	 */

	public IllegalURLException(String message, Throwable cause) {
		super(message, cause);
	}

}

