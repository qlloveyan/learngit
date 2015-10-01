package com.ql.basepro;

/**
 * 系统常用变量字典表
 * @author ql
 *
 */
public class Constants {
	
	/*接口返回状态码*/
	//返回状态码键
	public final static String RETCODE = "retCode";
	//返回消息键
	public final static String MESSAGE = "message";
	//成功
	public final static String RET_CODE_SUCCESS = "1";
	//失败
	public final static String RET_CODE_FAIL = "-1";
	//服务器数据解析异常
	public final static String RET_CODE_SERVER = "-100";
	
	/*接口返回状态信息*/
	//json格式解析异常
	public final static String RET_MSG_SERVER = "服务器处理异常!";
}
