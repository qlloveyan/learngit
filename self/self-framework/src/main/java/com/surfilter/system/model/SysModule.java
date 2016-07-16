/**
 * Project Name:smcs<br>
 * File Name:SysModule.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;

import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:SysModule.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysModule extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 系统模块类型名称
	 */
	private String sysModuleTypeName;
	/**
	 *ID
	 */
	private Long id;
	/**
	 *模块英文描述
	 */
	private String moduleCode;
	/**
	 *模块中文描述
	 */
	private String moduleName;
	/**
	 *模块类型（菜单，按钮等）
	 */
	private String moduleType;
	/**
	 *模块父ID
	 */
	private String parentId;
	/**
	 *备注
	 */
	private String remark;
	/**
	 *模块对应的JS文件路径（ExtJS）
	 */
	private String moduleFilePath;
	/**
	 *资源路径（url）
	 */
	private String resourcePath;
	/**
	 *排序号
	 */
	private Long moduleSort;
	/**
	 * 是否为叶子节点
	 */
	private String isLeaf;


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
	 *设置 :模块英文描述
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	
	/**	 
	 *获取 :模块英文描述
	 */
	public String getModuleCode() {
		return this.moduleCode;
	}

	public String getSysModuleTypeName() {
		return sysModuleTypeName;
	}

	public void setSysModuleTypeName(String sysModuleTypeName) {
		this.sysModuleTypeName = sysModuleTypeName;
	}

	/**	 
	 *设置 :模块中文描述
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**	 
	 *获取 :模块中文描述
	 */
	public String getModuleName() {
		return this.moduleName;
	}

	/**	 
	 *设置 :模块类型（菜单，按钮等）
	 */
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	/**	 
	 *获取 :模块类型（菜单，按钮等）
	 */
	public String getModuleType() {
		return this.moduleType;
	}

	/**	 
	 *设置 :模块父ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**	 
	 *获取 :模块父ID
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
	 *设置 :模块对应的JS文件路径（ExtJS）
	 */
	public void setModuleFilePath(String moduleFilePath) {
		this.moduleFilePath = moduleFilePath;
	}

	/**	 
	 *获取 :模块对应的JS文件路径（ExtJS）
	 */
	public String getModuleFilePath() {
		return this.moduleFilePath;
	}

	/**	 
	 *设置 :资源路径（url）
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	/**	 
	 *获取 :资源路径（url）
	 */
	public String getResourcePath() {
		return this.resourcePath;
	}

	/**	 
	 *设置 :排序号
	 */
	public void setModuleSort(Long moduleSort) {
		this.moduleSort = moduleSort;
	}

	/**	 
	 *获取 :排序号
	 */
	public Long getModuleSort() {
		return this.moduleSort;
	}

	/**	 
	 *获取 :是否为叶子节点
	 */
	public String getIsLeaf() {
		return isLeaf;
	}

	/**	 
	 *设置 :是否为叶子节点
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

}
