package com.surfilter.system.model;


/** 
* @ClassName: RoleRelation 
* @Description: TODO(角色之间的继承关系) 
* @author wangguohong 
* @date 2015年9月10日 下午4:11:46 
*  
*/ 
public class RoleRelation {
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 角色父id
	 */
	private Long parentRoleId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getParentRoleId() {
		return parentRoleId;
	}
	public void setParentRoleId(Long parentRoleId) {
		this.parentRoleId = parentRoleId;
	}
	
	
}
