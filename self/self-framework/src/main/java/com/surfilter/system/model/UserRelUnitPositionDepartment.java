/**
 * Project Name:smcs<br>
 * File Name:UserRelUnitPositionDepartment.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年02月25日  下午07:43:23<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:UserRelUnitPositionDepartment.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserRelUnitPositionDepartment extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *用户ID SY0004表主键
	 */
	private Long userId;
	/**
	 *单位ID
	 */
	private Long unitId;
	/**
	 *部门ID 关联SY0024表主键
	 */
	private Long departId;
	/**
	 *职位ID
	 */
	private Long postionId;
	


	/**	 
	 *设置 :主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :用户ID SY0004表主键
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**	 
	 *获取 :用户ID SY0004表主键
	 */
	public Long getUserId() {
		return this.userId;
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
	 *设置 :部门ID 关联SY0024表主键
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**	 
	 *获取 :部门ID 关联SY0024表主键
	 */
	public Long getDepartId() {
		return this.departId;
	}

	/**	 
	 *设置 :职位ID
	 */
	public void setPostionId(Long postionId) {
		this.postionId = postionId;
	}

	/**	 
	 *获取 :职位ID
	 */
	public Long getPostionId() {
		return this.postionId;
	}

}
