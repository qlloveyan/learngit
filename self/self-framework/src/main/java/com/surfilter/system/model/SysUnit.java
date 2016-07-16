/**
 * Project Name:smcs<br>
 * File Name:SysUnit.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2013年10月25日  下午07:41:42<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;

import com.surfilter.framework.model.BaseEntity;


/**
 * ClassName:SysUnit.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年10月25日  下午07:41:42<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysUnit extends BaseEntity implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *ID
	 */
	private Long id;
	/**
	 *单位中文描述
	 */
	private String unitName;
	/**
	 *单位父ID
	 */
	private String parentId;
	/**
	 *备注
	 */
	private String remark;
	/**
	 *单位级别
	 */
	private String unitLevel;
	/**
	 * 级别id
	 */
	private String nativeId;

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}

	/**	 
	 *设置 :ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :ID
	 */
	public Long getId() {
		return this.id;
	}


	/**	 
	 *设置 :单位中文描述
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**	 
	 *获取 :单位中文描述
	 */
	public String getUnitName() {
		return this.unitName;
	}

	/**	 
	 *设置 :单位父ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**	 
	 *获取 :单位父ID
	 */
	public String getParentId() {
		return this.parentId;
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

	/**	 
	 *设置 :单位级别
	 */
	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}

	/**	 
	 *获取 :单位级别
	 */
	public String getUnitLevel() {
		return this.unitLevel;
	}

}
