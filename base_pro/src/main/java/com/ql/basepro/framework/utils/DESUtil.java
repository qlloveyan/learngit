package com.ql.basepro.framework.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DESUtil {
	// MD5加密
	private Key key;// 密钥的key值
	private byte[] DESkey;
	private byte[] DESIV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB,
			(byte) 0xCD, (byte) 0xEF };
	private AlgorithmParameterSpec iv = null;// 加密算法的参数接口

	public DESUtil() {
		try {
			this.DESkey = ConfigUtil.getConfig("DES_KEY", "etribe.net").getBytes("UTF-8");// 设置密钥
			DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
			iv = new IvParameterSpec(DESIV);// 设置向量
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	// 加密
	public  String KL(String inputString) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String outputString = "";
		try {
			byteMing = inputString.getBytes("UTF-8");
			byteMi = this.getEncCode(byteMing);
			outputString = Base64Utils.encode(byteMi);
		} catch (Exception e) {
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return outputString;
	}

	// 解密
	public  String JM(String inputString) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = Base64Utils.decode(inputString);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	private  byte[] getEncCode(byte[] bt) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			// 得到Cipher实例
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byteFina = cipher.doFinal(bt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param bt
	 *            待解密的字节码
	 * @return 解密后的字节码
	 */
	private byte[] getDesCode(byte[] bt) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			// 得到Cipher实例
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byteFina = cipher.doFinal(bt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}
	
	public static void main(String[] args) {
		DESUtil desUtil = new DESUtil();
		
		String str = "{\"jsonrpc\":\"2.0\",\"method\":\"deliveryOrderReEntry\",\"params\":{\"tokenId\":\"eJwFwQcBAAAIA6BMu2r/YgJSygVEWKnCG3YfPF8E4A==\",\"userId\":\"9994052\",\"ordId\":\"54\",\"comment\":\"配送订单异常取消之后重新录入#$*\\||\"},\"id\":\"1\"}";
		
		String klstr = desUtil.KL(str);
		System.out.println(klstr);
		
		String jmstr = desUtil.JM(klstr);
		System.out.println(jmstr);
	}
}