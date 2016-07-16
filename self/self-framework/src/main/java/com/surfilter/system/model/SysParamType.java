/**
 * Project Name:smcs<br>
 * File Name:SysParamType.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.model<br>
 * Date:2014年01月02日  下午07:21:08<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:SysParamType.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:08<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysParamType extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *ID
	 */
	private Long id;
	/**
	 *分类中文名称
	 */
	private String typeCn;
	/**
	 *分类英文名称
	 */
	private String typeEn;
	/**
	 *父类ID
	 */
	private Long parentId;
	/**
	 * 业务类型0=系统参数，1=接口参数
	 */
	private Long businessType;
	/**
	 * 父类型名称
	 */
	private String parentName;

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
	 *设置 :分类中文名称
	 */
	public void setTypeCn(String typeCn) {
		this.typeCn = typeCn;
	}

	/**	 
	 *获取 :分类中文名称
	 */
	public String getTypeCn() {
		return this.typeCn;
	}

	/**	 
	 *设置 :分类英文名称
	 */
	public void setTypeEn(String typeEn) {
		this.typeEn = typeEn;
	}

	/**	 
	 *获取 :分类英文名称
	 */
	public String getTypeEn() {
		return this.typeEn;
	}

	/**	 
	 *设置 :父类ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**	 
	 *获取 :父类ID
	 */
	public Long getParentId() {
		return this.parentId;
	}
	/**	 
	 *设置 :业务类型0=系统参数，1=接口参数
	 */
	public Long getBusinessType() {
		return businessType;
	}
	/**	 
	 *获取 :业务类型0=系统参数，1=接口参数
	 */
	public void setBusinessType(Long businessType) {
		this.businessType = businessType;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
