/**
 * Project Name:smcs
 * File Name:ExtJsPath.java
 * Package Name:com.smcs.core.web.bind
 * Date:2013年9月27日下午4:03:58
 *
 */

package com.surfilter.framework.web.bind;

import java.io.Serializable;

/**
 * ClassName:ExtJsPath <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013年9月27日 下午4:03:58 <br/>
 * 
 * @author tangbiao
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressWarnings("serial")
public class JsPath implements Serializable {

	private String id;
	private String jsPath;

	public JsPath() {
	}

	public JsPath(String id, String jsPath) {
		super();
		this.id = id;
		this.jsPath = jsPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

}
