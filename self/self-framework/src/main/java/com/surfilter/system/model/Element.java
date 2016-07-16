/**
 * Project Name:smcs<br>
 * File Name:Element.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2013年11月06日  下午01:41:41<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;

import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:Element.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:41<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Element extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *页面元素控管表ID
	 */
	private Long id;
	/**
	 *页面元素编码
	 */
	private String elementCode;
	/**
	 *功能ID
	 */
	private Long funcId;
	
	/**
	 * 功能名称
	 */
	private String funcName;
	/**
	 *元素名称
	 */
	private String elementName;
	


	/**	 
	 *设置 :页面元素控管表ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :页面元素控管表ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :页面元素编码
	 */
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	/**	 
	 *获取 :页面元素编码
	 */
	public String getElementCode() {
		return this.elementCode;
	}

	/**	 
	 *设置 :功能ID
	 */
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	/**	 
	 *获取 :功能ID
	 */
	public Long getFuncId() {
		return this.funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	
	

}
