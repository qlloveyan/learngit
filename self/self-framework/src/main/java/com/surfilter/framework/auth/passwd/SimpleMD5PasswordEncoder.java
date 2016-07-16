/**
 * Project Name:smcs
 * File Name:SimpleMD5PasswordEncoder.java
 * Package Name:com.smcs.core.auth.passwd
 * Date:2013年9月22日下午8:58:26
 *
 */

package com.surfilter.framework.auth.passwd;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.surfilter.framework.auth.PasswordEncoder;

/**
 * ClassName:SimpleMD5PasswordEncoder. <br/>
 * 简单的MD5加密算法.
 * <p/>
 * Date: 2013年9月22日 下午8:58:26 <br/>
 * 
 * @author Tuyan
 * @version 1.0.0
 * @since JDK 1.6
 * @see PasswordEncoder
 */
public class SimpleMD5PasswordEncoder implements PasswordEncoder {

	/**
	 * MD5_HEX: MD5的16进制起始码.
	 * 
	 * @since JDK 1.6
	 */
	private static final int MD5_HEX = 0xFF;

	/**
	 * Creates a new instance of SimpleMD5PasswordEncoder.
	 * 
	 */
	public SimpleMD5PasswordEncoder() {
		super();
	}

	@Override
	public String encode(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(MD5_HEX & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(MD5_HEX & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(MD5_HEX & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}
}
