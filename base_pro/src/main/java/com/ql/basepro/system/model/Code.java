/**
 * Project Name:smcs<br>
 * File Name:Code.java<br>
 * Package Name:com.etribe.model<br>
 * Date:2015年06月04日  下午03:28:50<br>
 *
*/
package com.ql.basepro.system.model;
import java.io.Serializable;

/**
 * ClassName:Code.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月04日  下午03:28:50<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Code implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private Long id;
	/**
	 *字典类型，用于标注字典
	 */
	private String codeType;
	/**
	 *字典名称
	 */
	private String codeName;
	/**
	 *关键词
	 */
	private String codeKey;
	/**
	 *排序
	 */
	private String codeSort;
	/**
	 *父id
	 */
	private String pid;
	/**
	 * 父名称
	 */
	private String pName;
	/**
	 * 是否启用，用于标识划钱规则
	 */
	private Integer isUsing;

	/**	 
	 *设置 :主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键id
	 */
	public Long getId() {
		return this.id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeSort() {
		return codeSort;
	}

	public void setCodeSort(String codeSort) {
		this.codeSort = codeSort;
	}

	/**	 
	 *设置 :父id
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**	 
	 *获取 :父id
	 */
	public String getPid() {
		return this.pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Integer isUsing) {
		this.isUsing = isUsing;
	}
}
