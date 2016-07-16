/**
 * Project Name:lichen
 * File Name:HashKey.java
 * Package Name:com.surfilter.framework.utils
 * Date:2014-4-24上午9:35:40
 *
 */

package com.surfilter.system.ukey;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * key加密 ClassName:HashKey <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-4-24 上午9:35:40 <br/>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 * @see
 */
public class SmcsHashKey {
	/**
	 * 
	 * hash:(获取hash值). <br/>
	 *
	 * @author wangguohong
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	public static String hash(String key) {
		ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
		int seed = 0x1234AB;

		ByteOrder byteOrder = buf.order();
		buf.order(ByteOrder.LITTLE_ENDIAN);

		long m = 0xc6a4a7935bd1e995L;
		int r = 47;

		long h = seed ^ (buf.remaining() * m);

		long k;
		while (buf.remaining() >= 8) {
			k = buf.getLong();

			k *= m;
			k ^= k >>> r;
			k *= m;

			h ^= k;
			h *= m;
		}

		if (buf.remaining() > 0) {
			ByteBuffer finish = ByteBuffer.allocate(8).order(
					ByteOrder.LITTLE_ENDIAN);
			finish.put(buf).rewind();
			h ^= finish.getLong();
			h *= m;
		}

		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;

		buf.order(byteOrder);
		return String.valueOf(h);
	}

	/**
	 * 
	 * encryptBASE64:(加密). <br/>
	 *
	 * @author wangguohong
	 * @param key
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static String encryptBASE64(String key) throws Exception {
		byte[] keybytes = key.getBytes();
		//return replaceBlank((new BASE64Encoder()).encodeBuffer(keybytes));
		return (new BASE64Encoder()).encodeBuffer(keybytes);
	}

	/**
	 * 
	 * decryptBASE64:(解密). <br/>
	 *
	 * @author wangguohong
	 * @param key
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static String decryptBASE64(String key) throws Exception {
		byte[] temp = (new BASE64Decoder()).decodeBuffer(key);
		return new String(temp);
	}

	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	/**
	 * 
	 * randomString:(生成随机字符串). <br/>
	 *
	 * @author wangguohong
	 * @param length 字符长度
	 * @return
	 * @since JDK 1.6
	 */
	public static  String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
               randGen = new Random();
               numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                  "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                 //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
         //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
	}

	public static String MD5(String src){
		String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();//.substring(8, 24);
            //System.out.println(b.length);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
	}
	public static void main(String[] args) {
		System.out.println(SmcsHashKey.MD5("123"));
	}

}
