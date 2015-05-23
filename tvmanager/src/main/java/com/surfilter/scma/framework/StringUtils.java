/**
 * Project Name:smcs
 * File Name:StringUtils.java
 * Package Name:com.smcs.core.utils
 * Date:2013年9月18日上午9:03:40
 *
 */
package com.surfilter.scma.framework;

import org.springframework.util.ClassUtils;

/**
 * ClassName: StringUtils. <br/>
 * date: 2013年9月22日 下午2:14:07 <br/>
 *
 * @author Tuyan
 * @version 1.0.0
 * @since JDK 1.6
 * @see	org.springframework.util.StringUtils
 */
public abstract class StringUtils extends org.springframework.util.StringUtils{

	/**
	 * 判断字符串是否为空.
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空 （true为 空，false不为空)
	 */
	public static boolean isEmpty(final String string) {
		return string == null || string.length() == 0;
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
		for (String frameworkPackagePrefix : frameworkPackagePrefixs) {
			if (packageName.startsWith(frameworkPackagePrefix)) {
				packageName = StringUtils.replace(packageName, frameworkPackagePrefix, "");
				break;
			}
		}
		packageName = StringUtils.replace(packageName, actionPackageSuffix, "");
		packageName = StringUtils.replace(packageName, ".", "/");
		return (caseSensitive ? packageName : packageName.toLowerCase()) + "/";
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
}
