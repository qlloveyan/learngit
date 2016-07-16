/**
 * Project Name:smcs<br>
 * File Name:FuncModule.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2013年11月06日  下午01:41:40<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import java.util.List;

import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:FuncModule.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:40<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FuncModule extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *功能模块表ID
	 */
	private Long id;
	/**
	 *功能名称
	 */
	private String funcName;
	/**
	 *功能编码
	 */
	private String funcCode;
	/**
	 *功能类型
	 */
	private String funcType;
	/**
	 *父功能ID
	 */
	private Long parentId;
	
	/**
	 * 父功能名称
	 */
	private String parentModuleName;
	/**
	 *功能URL前缀
	 */
	private String url;
	/**
	 *拦截功能参数
	 */
	private String params;
	/**
	 *小图标
	 */
	private String icon;
	/**
	 *功能文件路径
	 */
	private String funcFilePath;
	/**
	 *序号
	 */
	private Long funcSort;
	
	/**
	 * 是否为叶子节点
	 */
	private String isLeaf;
	
	/**
	 * 子节点
	 */
	private List<FuncModule> children;
	
	/**
	 * 菜单图片路径
	 */
	private String pic;
	


	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	/**	 
	 *设置 :功能模块表ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :功能模块表ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :功能名称
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	/**	 
	 *获取 :功能名称
	 */
	public String getFuncName() {
		return this.funcName;
	}

	/**	 
	 *设置 :功能编码
	 */
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	/**	 
	 *获取 :功能编码
	 */
	public String getFuncCode() {
		return this.funcCode;
	}

	/**	 
	 *设置 :功能类型
	 */
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	/**	 
	 *获取 :功能类型
	 */
	public String getFuncType() {
		return this.funcType;
	}

	/**	 
	 *设置 :父功能ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**	 
	 *获取 :父功能ID
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**	 
	 *设置 :功能URL前缀
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**	 
	 *获取 :功能URL前缀
	 */
	public String getUrl() {
		return this.url;
	}

	/**	 
	 *设置 :拦截功能参数
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**	 
	 *获取 :拦截功能参数
	 */
	public String getParams() {
		return this.params;
	}

	/**	 
	 *设置 :图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**	 
	 *获取 :图标
	 */
	public String getIcon() {
		return this.icon;
	}

	/**	 
	 *设置 :功能文件路径
	 */
	public void setFuncFilePath(String funcFilePath) {
		this.funcFilePath = funcFilePath;
	}

	/**	 
	 *获取 :功能文件路径
	 */
	public String getFuncFilePath() {
		return this.funcFilePath;
	}

	/**	 
	 *设置 :序号
	 */
	public void setFuncSort(Long funcSort) {
		this.funcSort = funcSort;
	}

	/**	 
	 *获取 :序号
	 */
	public Long getFuncSort() {
		return this.funcSort;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	public List<FuncModule> getChildren() {
		return children;
	}

	public void setChildren(List<FuncModule> children) {
		this.children = children;
	}
	
	

}
