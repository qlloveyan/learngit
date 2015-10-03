/**
 * Project Name:smcs<br>
 * File Name:CommonRegion.java<br>
 * Package Name:com.etribe.model<br>
 * Date:2015年06月07日  下午12:18:31<br>
 *
 */
package com.ql.basepro.system.model;

import java.io.Serializable;

//import com.etribe.framework.BaseEntity;

/**
 * ClassName:CommonRegion.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason: TODO ADD REASON.<br>
 * Date: 2015年06月07日 下午12:18:31<br>
 * 
 * @author ql
 * @version
 * @since JDK 1.6
 * @see
 */
// public class CommonRegion extends BaseEntity implements Serializable{
public class CommonRegion implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 区域码
	 */
	private Long areaCode;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 区域类型 1 省 2市 3区
	 */
	private String areaType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否有效 0无效 1失效
	 */
	private String areaEnabled;
	/**
	 * 层级
	 */
	private String levelId;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 设置 :区域码
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取 :区域码
	 */
	public Long getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 设置 :区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 获取 :区域名称
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * 设置 :区域类型 1 省 2市 3区
	 */

	/**
	 * 获取 :区域类型 1 省 2市 3区
	 */

	/**
	 * 设置 :备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaEnabled() {
		return areaEnabled;
	}

	public void setAreaEnabled(String areaEnabled) {
		this.areaEnabled = areaEnabled;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	/**
	 * 获取 :备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 :主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取 :主键
	 */
	public Long getId() {
		return this.id;
	}

}
