/**
 * Project Name:cherry-application
 * File Name:StringUtils.java
 * Package Name:org.cherry.application.util
 * Date:2013年9月24日下午4:21:43
 *
 */

package com.surfilter.framework.utils;

import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

import org.springframework.util.ClassUtils;


/**
 * ClassName:StringUtils. <br/>
 * String工具类. Date: 2013年9月24日 下午4:21:43 <br/>
 * 
 * @author Tuyan
 * @version 1.0.0
 * @since JDK 1.6
 */
public abstract class StringUtils {
	
	/**
	 * NEW_N_LINE_CHAR: 换行符.
	 * @since JDK 1.6
	 */
	public final static char NEW_N_LINE_CHAR = '\n';
	
	/**
	 * NEW_R_LINE_CHAR: 回车符.
	 * @since JDK 1.6
	 */
	public final static char NEW_R_LINE_CHAR = '\r';
	
	/**
	 * NEW_UNICODE_LINE_CHAR: Unicode编码的换行符.
	 * @since JDK 1.6
	 */
	public final static char NEW_UNICODE_LINE_CHAR = '\u2028';

	/**
	 * APPLICATION_MAP_ARG_SPLIT: 应用程序-map参数中的分割符.
	 * 
	 * @since JDK 1.6
	 */
	public final static String APPLICATION_MAP_ARG_SPLIT = "^";

	/**
	 * isEmpty: 判断字符串是否为空. <br/>
	 * 
	 * @author Tuyan
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回True
	 * @since JDK 1.6
	 */
	public static boolean isEmpty(String str) {
		return (null == str || "".equals(str));
	}
	
	/**
	 * isEmpty: 判断字符串是否为空. <br/>
	 * 
	 * @author Tuyan
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回True
	 * @since JDK 1.6
	 */
	public static boolean isNotEmpty(String str) {
		return (null != str && !"".equals(str));
	}

	/**
	 * formatPercent: 将数字格式化为百分比形式的字符串. <br/>
	 * 
	 * @author Tuyan
	 * @param fraction
	 *            要格式化的数字
	 * @param decimalPlaces
	 *            转化为百分比后需要精确到小数点后几位
	 * @return
	 * @since JDK 1.6
	 */
	public static String formatPercent(double fraction, int decimalPlaces) {
		return format("%." + decimalPlaces + "f%%", fraction * 100);
	}

	/**
	 * format: 格式化字符串. <br/>
	 * 
	 * @author Tuyan
	 * @param format
	 * @param objects
	 * @return
	 * @since JDK 1.6
	 */
	public static String format(final String format, final Object... objects) {
		return String.format(Locale.ENGLISH, format, objects);
	}

	/**
	 * upperFirst: 将字符串的首字母转换为大写. <br/>
	 * 
	 * @author Tuyan
	 * @param str
	 *            要转换的字符串
	 * @return 转换之后的字符串
	 * @since JDK 1.6
	 */
	public static String upperFirst(String str) {
		if (isEmpty(str)) {
			return null;
		}
		if (1 == str.length()) {
			return str.toUpperCase();
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * replaceWhitespace: 替换空字符串，如果有连续的空字符串只替换一个，其他的删除掉. <br/>
	 * 
	 * @author Tuyan
	 * @param source
	 *            替换的源
	 * @param replacement
	 *            要替换的字符串
	 * @return 替换之后的字符串
	 * @since JDK 1.6
	 */
	public static String replaceWhitespace(String source, String replacement) {
		source = trimLeadingTrailingWhitespace(source);
		if (isEmpty(source)) {
			return null;
		}
		if (isEmpty(replacement)) {
			return source;
		}

		// FIXME 需要加入将source中的连续多个空格换算成单个空格的方法
		StringBuffer sb = new StringBuffer(0);
		int strLen = source.length();
		for (int i = 0; i < strLen; i++) {
			char c = source.charAt(i);
			if (Character.isWhitespace(c)) {
				sb.append(replacement);
			} else {
				sb.append(Character.toString(c));
			}
		}
		sb.trimToSize();
		return sb.toString();
	}

	/**
	 * trimWhitespace: 消除字符串中的空格. <br/>
	 * 
	 * @author Tuyan
	 * @param source
	 *            字符串
	 * @return 消除空格之后的字符串
	 * @since JDK 1.6
	 */
	public static String trimWhitespace(String source) {
		if (isEmpty(source)) {
			return null;
		}
		StringBuffer sb = new StringBuffer(0);
		int strLen = source.length();
		for (int i = 0; i < strLen; i++) {
			char c = source.charAt(i);
			if (!Character.isWhitespace(c)) {
				sb.append(Character.toString(c));
			}
		}
		sb.trimToSize();
		return sb.toString();
	}

	/**
	 * trimLeadingTrailingWhitespace: 删除字符串开头和结尾的所有空格. <br/>
	 * 
	 * @author Tuyan
	 * @param str
	 *            字符串
	 * @return 删除空格之后的字符串
	 * @since JDK 1.6
	 */
	public static String trimLeadingTrailingWhitespace(String str) {
		return trimLeadingWhitespace(trimTrailingWhitespace(str));
	}

	/**
	 * 删除字符串开头的所有空格，复制的spring的方法
	 * 
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 删除字符串结尾的所有空格，复制的spring的方法
	 * 
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * countOf:判断字符串中某个字符串出现的次数. <br/>
	 * 
	 * @author Tuyan
	 * @param source
	 *            源字符串
	 * @param countStr
	 *            要判断出现次数的字符串
	 * @return
	 * @since JDK 1.6
	 */
	public static int countOf(String source, String countStr) {
		if (isEmpty(source) || isEmpty(countStr)) {
			return 0;
		}
		int count = 0;
		String[] arr = source.split(countStr);
		if (null != arr && 1 < arr.length) {
			count = arr.length - 1;
		}
		return count;
	}

	/**
	 * 将srcByte2连接到srcByte1后组成一个byte数组
	 * 
	 * @param srcByte1
	 *            数组1
	 * @param srcByte2
	 *            数组2
	 * @return 串接srcByte1和srcByte2的数组
	 */
	public static final byte[] joinBytes(byte[] srcByte1, byte[] srcByte2) {
		// TODO 这里int长度可能不太满足
		int srcByte1Length = srcByte1.length;
		int srcByte2Length = srcByte2.length;
		byte[] destByte = new byte[srcByte1Length + srcByte2Length];
		System.arraycopy(srcByte1, 0, destByte, 0, srcByte1Length); // copy第一个数组
		System.arraycopy(srcByte2, 0, destByte, srcByte1Length, srcByte2Length); // copy第二个数组
		return destByte;
	}

	/**
	 * arrayToString: 将数组的每一个元素拼接成一个字符串. <br/>
	 * 拼接的字符串以空格分隔.
	 * @author Tuyan
	 * @param strArray	要转换的数组
	 * @return	拼接之后的字符串
	 * @since JDK 1.6
	 */
	public static String arrayToString(String[] strArray) {
		StringBuilder sb = new StringBuilder(0);
		for (String str : strArray) {
			sb.append(str).append(" ");
		}
		String s = sb.toString();
		return trimTrailingWhitespace(s);
	}
	
	/**
	 * byteToUpperHex: 将字节数组转换成大写的16进制的字符串. <br/>
	 * 
	 * @author Tuyan
	 * @param b
	 *            要缓存的字节数组
	 * @return 转换之后的大写的16进制字符串
	 * @since JDK 1.6
	 */
	public static String byteToUpperHex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * hexToBytes: 将16进制字符串转换为一个字节数组. <br/>
	 *
	 * @author Tuyan
	 * @param strHex	要转换的16进制的字符串
	 * @return	转换之后的字节数组
	 * @since JDK 1.6
	 */
	public static byte[] hexToBytes(String strHex) {
		if (StringUtils.isEmpty(strHex)) {
			return null;
		}
		int l = strHex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strHex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}
	
	/**
	 * reverseSubstring: 逆向截取字符串. <br/>
	 * 将字符串从反方向开始截取，也就是从字符串的最后一个字符开始进行截取操作，最后一个字符的索引值为0。
	 * 
	 * @author Tuyan
	 * @param src	要截取的源字符串
	 * @param beginIndex	逆向索引值，字符串中最后一个字符的索引值为0
	 * @return	截取之后的字符串
	 * @since JDK 1.6
	 */
	public static String reverseSubstring(String src, int beginIndex) {
		int srcLenIndex = src.length() - 1;
		return src.substring(srcLenIndex - beginIndex);
	}
	
	/**
	 * reverseSubstring: 根据要截取的长度逆向截取字符串. <br/>
	 * 将字符串从反方向开始截取，也就是从字符串的最后一个字符开始进行截取操作，最后一个字符的索引值为0。
	 * @author Tuyan
	 * @param src	要截取的源字符串
	 * @param beginIndex	逆向索引值，字符串中最后一个字符的索引值为0
	 * @param interceptCount	要截取的字符个数
	 * @return	截取之后的字符串
	 * @since JDK 1.6
	 */
	public static String reverseSubstring(String src, int beginIndex, int interceptCount) {
		int count = src.length();
		int endIndex = src.length() - beginIndex;
		return src.substring(endIndex - interceptCount, count - beginIndex);
	}
	
	/**
	 * isNewLineChar: 判断一个字符是否为一个换行符. <br/>
	 *
	 * @author Tuyan
	 * @param c	要判断的字符
	 * @return	如果是一个换行符返回True
	 * @since JDK 1.6
	 */
	public static boolean isNewLineChar(char c) {
		return c == NEW_R_LINE_CHAR || c == NEW_N_LINE_CHAR || c == NEW_UNICODE_LINE_CHAR;
	}
	
	public static void main(String[] args) {
		System.out.println(replaceWhitespace("App ad adsf   asdf ", "-"));
		System.out.println(trimWhitespace("App ad adsf   asdf "));
		System.out.println(countOf("/cherry/log/file", "/"));
		System.out.println(reverseSubstring("/cherry/log/file", 1));
		System.out.println(reverseSubstring("/cherry/log/file", 0, 2));
		String s = "cherry/proxy/";
		System.out.println(s.length());
		System.out.println(s.lastIndexOf("/"));
		System.out.println(s.length() == s.lastIndexOf("/") + 1);
		StringTokenizer st = new StringTokenizer(s, "/");
		String[] arr = new String[3];
		int i = 0;
		while (st.hasMoreTokens() && i < 3) {
			String token = st.nextToken();
			arr[i] = token;
			i++;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 *
	 * 将数字转换成字节
	 *
	 * @param number
	 * @return
	 */
	public static String convertNumber(final Long number) {
		return Long.toBinaryString(number);
	}

	/**
	 * buildPathByBeanClass:根据类名称和包名称来组成自定义的URL映射. <br/>
	 * 
	 * @author Tkiyer
	 * @param beanClass
	 *            controller class.
	 * @param actionPackageSuffixs
	 *            controller package suffixs.
	 *            
	 * @param frameworkPackagePrefixs framework package prefix
	 * @param caseSensitive
	 *            是否区分大小写
	 * @return 自定义的URL映射约定
	 * @since JDK 1.6
	 */
	@SuppressWarnings("rawtypes")
	public static String buildPathByBeanClass(Class beanClass, String[] frameworkPackagePrefixs, String[] actionPackageSuffixs, boolean caseSensitive) {
		String packageName = ClassUtils.getPackageName(beanClass);
		if (StringUtils.isEmpty(packageName)) {
			return "";
		}
		String actionPackageSuffix = "";
		for (String packageSuffix : actionPackageSuffixs) {
			if (packageName.endsWith(packageSuffix)) {
				actionPackageSuffix = packageSuffix;
				break;
			}
		}
		String newpackageName="";
		for (String frameworkPackagePrefix : frameworkPackagePrefixs) {
			if (packageName.startsWith(frameworkPackagePrefix)) {
				newpackageName = StringUtils.replace(packageName, frameworkPackagePrefix, "");
//				break;
			}
		}
		newpackageName = replace(newpackageName, actionPackageSuffix, "");
		newpackageName = replace(newpackageName, ".", "/");
		return (caseSensitive ? newpackageName : newpackageName.toLowerCase()) + "/";
	}
	
	
	/**
	 * Replace all occurrences of a substring within a string with
	 * another string.
	 * @param inString {@code String} to examine
	 * @param oldPattern {@code String} to replace
	 * @param newPattern {@code String} to insert
	 * @return a {@code String} with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}
	
	
	/**
	 * Check that the given {@code CharSequence} is neither {@code null} nor
	 * of length 0.
	 * <p>Note: this method returns {@code true} for a {@code CharSequence}
	 * that purely consists of whitespace.
	 * <p><pre class="code">
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the {@code CharSequence} to check (may be {@code null})
	 * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * containsText: 判断字符串中是否有指定的符号. <br/>
	 *
	 * @author Tuyan
	 * @param str	要判断的字符串
	 * @param delimiter	指定的符号
	 * @return	True/False
	 * @since JDK 1.6
	 */
	public static boolean containsText(String str, String delimiter) {
		if (str.indexOf(delimiter) > -1) {
			return true;
		}
		return false;
	}	
	
	
}
