/**
 * Project Name:shutdown
 * File Name:ExtJsObj.java
 * Package Name:com.surfilter.self.angularjs.model
 * Date:2016年2月28日下午7:40:11
 *
*/

package com.surfilter.self.angularjs.model;

import java.io.Serializable;

/**
 * ClassName:ExtJsObj <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月28日 下午7:40:11 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExtJsObj implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	private boolean success;
	
	private String msg;
	
	private Object result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public ExtJsObj(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ExtJsObj(boolean success, String msg, Object result) {
		super();
		this.success = success;
		this.msg = msg;
		this.result = result;
	}
	
}

