/**
 * Project Name:smcs
 * File Name:ExtJsObject.java
 * Package Name:com.smcs.core.web.bind
 * Date:2013年9月17日上午10:46:46
 *
*/

package com.ql.basepro.framework;

import java.io.Serializable;

/**
 * ClassName:ExtJsObject <br/>
 * Date:     2013年9月17日 上午10:46:46 <br/>
 * @author   tangbiao
 * @version	 1.0.0  
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("serial")
public class ExtJsObject implements Serializable {

	private boolean success = false;
	
	private String msg = "That's a ext js object!";
	
	private Object result = null;

	public ExtJsObject(boolean success, String msg) {
		this(success, msg, null);
	}
	
	public ExtJsObject(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.result = obj;
	}

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

	/**
	 * result.
	 *
	 * @return  the result
	 * @since   JDK 1.6
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * result.
	 *
	 * @param   result    the result to set
	 * @since   JDK 1.6
	 */
	public void setResult(Object result) {
		this.result = result;
	}
}

