/**
 * Project Name:smcs<br>
 * File Name:SysParam.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;

import com.surfilter.framework.model.BaseEntity;


/**
 * ClassName:SysParam.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysParam extends BaseEntity implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *null
	 */
	private Long id;
	/**
	 *参数英文名称
	 */
	private String paramCode;
	/**
	 *参数值
	 */
	private String paramValue;
	/**
	 *参数名称
	 */
	private String paramCnName;
	/**
	 *参数描述
	 */
	private String description;
	/**
	 *参数组（表示局部参数，默认为全局参数）
	 */
	private String paramGroupType;
	


	/**	 
	 *设置 :null
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :null
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :参数英文名称
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	/**	 
	 *获取 :参数英文名称
	 */
	public String getParamCode() {
		return this.paramCode;
	}

	/**	 
	 *设置 :参数值
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**	 
	 *获取 :参数值
	 */
	public String getParamValue() {
		return this.paramValue;
	}

	/**	 
	 *设置 :参数名称
	 */
	public void setParamCnName(String paramCnName) {
		this.paramCnName = paramCnName;
	}

	/**	 
	 *获取 :参数名称
	 */
	public String getParamCnName() {
		return this.paramCnName;
	}

	/**	 
	 *设置 :参数描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**	 
	 *获取 :参数描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**	 
	 *设置 :参数组（表示局部参数，默认为全局参数）
	 */
	public void setParamGroupType(String paramGroupType) {
		this.paramGroupType = paramGroupType;
	}

	/**	 
	 *获取 :参数组（表示局部参数，默认为全局参数）
	 */
	public String getParamGroupType() {
		return this.paramGroupType;
	}

}
