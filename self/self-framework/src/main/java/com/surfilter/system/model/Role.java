/**
 * Project Name:smcs<br>
 * File Name:Role.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;

import com.surfilter.framework.model.BaseEntity;


/**
 * ClassName:Role.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Role extends BaseEntity implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *角色ID
	 */
	private Long id;
	/**
	 *角色名称
	 */
	private String roleName;
	/**
	 *角色描述，这个角色做什么
	 */
	private String description;
	
	/**
	 * 继承角色集合
	 */
	private String parentIds;
	
	/**
	 * 创建人用户id
	 */
	private Long createUserId;
	


	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**	 
	 *设置 :角色ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :角色ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**	 
	 *获取 :角色名称
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**	 
	 *设置 :角色描述，这个角色做什么
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**	 
	 *获取 :角色描述，这个角色做什么
	 */
	public String getDescription() {
		return this.description;
	}

}
