/**
 * Project Name:smcs
 * File Name:SurfilterException.java
 * Package Name:com.smcs.core.exception
 * Date:2013年9月12日上午9:34:00
 *
*/

package com.surfilter.framework.exception;
/**
 * ClassName:SurfilterException <br/>
 * Function: 异常信息. <br/>
 * Reason:	 异常信息. <br/>
 * Date:     2013年9月12日 上午9:34:00 <br/>
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SurfilterException extends RuntimeException{

	/**
	 * serialVersionUID:序列号
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -218815507439174984L;
	
	/**
	 * 错误码.
	 */
	private String errorCode;
	
	/**
	 * 错误信息.
	 */
	private String errorMsg;
	

	/**
	 * 构造函数.
	 *
	 * @param errorCode 错误码
	 * @param errorMsg 错误信息
	 */
	public SurfilterException(String errorCode,String errorMsg){
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	/**
	 * 构造函数.
	 *
	 * @param errorCode 错误码.
	 * @param ex 异常信息.
	 */
	public SurfilterException(String errorCode,Exception ex){
		super(ex);
		this.errorCode = errorCode;
	}

	/**
	 * errorCode.
	 *
	 * @return  the errorCode
	 * @since   JDK 1.6
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * errorCode.
	 *
	 * @param   errorCode    the errorCode to set
	 * @since   JDK 1.6
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * errorMsg.
	 *
	 * @return  the errorMsg
	 * @since   JDK 1.6
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * errorMsg.
	 *
	 * @param   errorMsg    the errorMsg to set
	 * @since   JDK 1.6
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	

}

