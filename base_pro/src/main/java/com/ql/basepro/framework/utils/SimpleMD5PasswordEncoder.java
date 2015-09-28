
package com.ql.basepro.framework.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SimpleMD5PasswordEncoder{

	private static final int MD5_HEX = 0xFF;

	public SimpleMD5PasswordEncoder() {
		super();
	}

	public static String encode(String str) {
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
	
	public static void main(String[] args) {
		System.out.println(SimpleMD5PasswordEncoder.encode("123456"));
		
	}
}
