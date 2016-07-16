/**
 * Project Name:smcs
 * File Name:PasswordEncoder.java
 * Package Name:com.smcs.core.auth
 * Date:2013年9月22日下午8:32:34
 *
*/

package com.surfilter.framework.auth;
/**
 * ClassName:PasswordEncoder. <br/>
 * 密码加密类.<p/>
 * Date:     2013年9月22日 下午8:32:34 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 
 */
public interface PasswordEncoder {

	/**
	 * encode: 密码加密. <br/>
	 *
	 * @author Tuyan
	 * @param str	待加密密码
	 * @return	encoded password
	 * @since JDK 1.6
	 */
	String encode(String str);
}

