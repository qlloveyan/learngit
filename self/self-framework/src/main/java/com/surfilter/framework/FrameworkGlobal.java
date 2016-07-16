package com.surfilter.framework;

/**
 * 整个工程的公共变量.<br/>
 * 
 * @author Tuyan
 *
 */
public interface FrameworkGlobal {

	/**
	 * LOGIN_USER: 登录用户.
	 * @since JDK 1.6
	 */
	String LOGIN_USER = "loginUser";
	
	/**
	 * AUTH_URL_TOKENS: 授权的URL路径.
	 * @since JDK 1.6
	 */
	String AUTH_URL_TOKENS = "authUrlTokens";
	
	/**
	 * AUTH_CONTROL_TOKENS: 授权的控件表达式.
	 * @since JDK 1.6
	 */
	String AUTH_CONTROL_TOKENS = "authControlTokens";
	
	/**
	 * AUTHORIZATION_TOKEN: 已授权对象识别常量.
	 * @since JDK 1.6
	 */
	String AUTHORIZATION_TOKEN = "authorizationToken";
	
	/**
	 * SESSION_ERROR: 会话错误.
	 * @since JDK 1.6
	 */
	String SESSION_ERROR = "sessionError";
	
	/**
	 * TEMPLATE_PATH:TODO(导出模板下载路径).
	 * @since JDK 1.6
	 */
	String TEMPLATE_PATH = "templatepath";
	
	/**
	 * DATA_PATH:TODO(数据下载保存路径).
	 * @since JDK 1.6
	 */
	String DATA_PATH = "datapath";
	
	/**
	 * EXCEL_NAME:TODO(导出数据excel文件名称).
	 * @since JDK 1.6
	 */
	String EXCEL_NAME = "excelName";
	
	/**
	 * EXCEL_NAME:TODO(导出数据excel路径).
	 * @since JDK 1.6
	 */
	String FULL_EXCEL_PATH = "fullExcelPath";
	
	/**
	 * 批量导出批次号
	 */
	String BATCHNUM = "batchnum";
	/**
	 * 批量导出批次大小
	 */
	String BATCSIZE = "batcsize";
	/**
	 * 批量导出是否结束标记
	 */
	String BATCHEND = "batchEnd";
	
	/**
	 * SYSTEM_PLATFORM:系统平台id.
	 * @since JDK 1.6
	 */
	String SYSTEM_PLATFORM = "SYSTEM_PLATFORM";
}
