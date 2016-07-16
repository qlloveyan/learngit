/**
 * Project Name:smcs<br>
 * File Name:Department.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年02月25日  下午07:43:22<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:Department.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:22<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Department extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;
	/**
	 *所属单位ID 关联SY0010表主键
	 */
	private Long unitId;
	/**
	 *部门名称
	 */
	private String departName;
	/**
	 *父ID
	 */
	private Long pId;
	


	/**	 
	 *设置 :主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :所属单位ID 关联SY0010表主键
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	/**	 
	 *获取 :所属单位ID 关联SY0010表主键
	 */
	public Long getUnitId() {
		return this.unitId;
	}

	/**	 
	 *设置 :部门名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**	 
	 *获取 :部门名称
	 */
	public String getDepartName() {
		return this.departName;
	}

	/**	 
	 *设置 :父ID
	 */
	public void setPId(Long pId) {
		this.pId = pId;
	}

	/**	 
	 *获取 :父ID
	 */
	public Long getPId() {
		return this.pId;
	}

}
