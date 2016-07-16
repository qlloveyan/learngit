/**
 * Project Name:smcs<br>
 * File Name:SysArgs.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.model<br>
 * Date:2014年01月02日  下午07:21:36<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:SysArgs.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:36<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysArgs extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *null
	 */
	private Long id;
	/**
	 *所属分类ID 关联表SY0017
	 */
	private Long typeId;
	/**
	 *所属分类英文名称
	 */
	private String typeEn;
	/**
	 *中文名称
	 */
	private String nameCh;
	/**
	 *键
	 */
	private String key;
	/**
	 *值
	 */
	private String value;
	/**
	 *控件类型0=文本框，1=下拉框，2=时间，3=复选框
	 */
	private String type;
	/**
	 *是否只读0否，1是
	 */
	private Long isReadonly;
	/**
	 *是否必填0否，1是
	 */
	private Long canNotNull;
	/**
	 *可选值,JSON格式比如： {[{key1:value},{key2:value},{key3:value}]}
	 */
	private String chooseValues;
	/**
	 *备注
	 */
	private String remark;
	


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
	 *设置 :所属分类ID 关联表SY0017
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**	 
	 *获取 :所属分类ID 关联表SY0017
	 */
	public Long getTypeId() {
		return this.typeId;
	}

	/**	 
	 *设置 :所属分类英文名称
	 */
	public void setTypeEn(String typeEn) {
		this.typeEn = typeEn;
	}

	/**	 
	 *获取 :所属分类英文名称
	 */
	public String getTypeEn() {
		return this.typeEn;
	}

	/**	 
	 *设置 :中文名称
	 */
	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}

	/**	 
	 *获取 :中文名称
	 */
	public String getNameCh() {
		return this.nameCh;
	}

	/**	 
	 *设置 :键
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**	 
	 *获取 :键
	 */
	public String getKey() {
		return this.key;
	}

	/**	 
	 *设置 :值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**	 
	 *获取 :值
	 */
	public String getValue() {
		return this.value;
	}

	/**	 
	 *设置 :控件类型0=文本框，1=下拉框，2=时间，3=复选框
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**	 
	 *获取 :控件类型0=文本框，1=下拉框，2=时间，3=复选框
	 */
	public String getType() {
		return this.type;
	}

	/**	 
	 *设置 :是否只读0否，1是
	 */
	public void setIsReadonly(Long isReadonly) {
		this.isReadonly = isReadonly;
	}

	/**	 
	 *获取 :是否只读0否，1是
	 */
	public Long getIsReadonly() {
		return this.isReadonly;
	}

	/**	 
	 *设置 :是否必填0否，1是
	 */
	public void setCanNotNull(Long canNotNull) {
		this.canNotNull = canNotNull;
	}

	/**	 
	 *获取 :是否必填0否，1是
	 */
	public Long getCanNotNull() {
		return this.canNotNull;
	}

	/**	 
	 *设置 :可选值,JSON格式比如： {[{key1:value},{key2:value},{key3:value}]}
	 */
	public void setChooseValues(String chooseValues) {
		this.chooseValues = chooseValues;
	}

	/**	 
	 *获取 :可选值,JSON格式比如： {[{key1:value},{key2:value},{key3:value}]}
	 */
	public String getChooseValues() {
		return this.chooseValues;
	}

	/**	 
	 *设置 :备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**	 
	 *获取 :备注
	 */
	public String getRemark() {
		return this.remark;
	}

}
