/**
 * Project Name:smcs<br>
 * File Name:Position.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年02月25日  下午07:43:23<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:Position.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Position extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;
	/**
	 *职位名称
	 */
	private String positionName;
	/**
	 *单位ID
	 */
	private Long unitId;
	/**
	 *部门ID
	 */
	private Long departId;
	


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
	 *设置 :职位名称
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**	 
	 *获取 :职位名称
	 */
	public String getPositionName() {
		return this.positionName;
	}

	/**	 
	 *设置 :单位ID
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	/**	 
	 *获取 :单位ID
	 */
	public Long getUnitId() {
		return this.unitId;
	}

	/**	 
	 *设置 :部门ID
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**	 
	 *获取 :部门ID
	 */
	public Long getDepartId() {
		return this.departId;
	}

}
