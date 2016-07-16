/**
 * Project Name:smcs<br>
 * File Name:Dictionary.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import java.util.List;

import com.surfilter.framework.model.BaseEntity;


/**
 * ClassName:Dictionary.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Dictionary extends BaseEntity implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *字典类型
	 */
	private String codeType;
	/**
	 *KEY
	 */
	private String codeKey;
	/**
	 *VALUE
	 */
	private String codeValue;
	/**
	 *字典排序，用来进行排序
	 */
	private Long codeSort;
	/**
	 *描述
	 */
	private String description;
	/**
	 *是否可操作（1.可以删除，0不能删除，默认1）
	 */
	private Long isOperate;
	
	/**
	 * 节点是否展开  closed/open
	 */
	private String state;
	
	/**
	 * 冗余字段，该节点下的所有子节点集合
	 */
	private List<Dictionary> children;
	
	/**
	 *null
	 */
	private Long id;
	/**
	 *父亲ID
	 */
	private Long parentId;
	


	/**	 
	 *设置 :字典类型
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**	 
	 *获取 :字典类型
	 */
	public String getCodeType() {
		return this.codeType;
	}

	/**	 
	 *设置 :KEY
	 */
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	/**	 
	 *获取 :KEY
	 */
	public String getCodeKey() {
		return this.codeKey;
	}

	/**	 
	 *设置 :VALUE
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	/**	 
	 *获取 :VALUE
	 */
	public String getCodeValue() {
		return this.codeValue;
	}

	/**	 
	 *设置 :字典排序，用来进行排序
	 */
	public void setCodeSort(Long codeSort) {
		this.codeSort = codeSort;
	}

	/**	 
	 *获取 :字典排序，用来进行排序
	 */
	public Long getCodeSort() {
		return this.codeSort;
	}

	/**	 
	 *设置 :描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**	 
	 *获取 :描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**	 
	 *设置 :是否可操作（1.可以删除，0不能删除，默认1）
	 */
	public void setIsOperate(Long isOperate) {
		this.isOperate = isOperate;
	}

	/**	 
	 *获取 :是否可操作（1.可以删除，0不能删除，默认1）
	 */
	public Long getIsOperate() {
		return this.isOperate;
	}

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
	 *设置 :父亲ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**	 
	 *获取 :父亲ID
	 */
	public Long getParentId() {
		return this.parentId;
	}

	/**	 
	 *获取 :子节点集合
	 */
	public List<Dictionary> getChildren() {
		return children;
	}

	/**	 
	 *设置 :子节点集合
	 */
	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}

	/**	 
	 *获取 :获取叶子的展开状态
	 */
	public String getState() {
		return state;
	}

	/**	 
	 *设置 :叶子的展开状态
	 */
	public void setState(String state) {
		this.state = state;
	}

}
