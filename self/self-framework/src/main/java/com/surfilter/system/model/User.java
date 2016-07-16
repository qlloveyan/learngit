/**
 * Project Name:smcs<br>
 * File Name:User.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import java.util.Date;

import com.surfilter.framework.filehandle.excel.ExcelModel;

/**
 * ClassName:User.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User extends ExcelModel  implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *用户id
	 */
	private Long id;
	/**
	 *用户名（账户登录所用）
	 */
	private String userCode;
	/**
	 *密码
	 */
	private String userPass;
	/**
	 *用户中文名
	 */
	private String userName;
	/**
	 *电话
	 */
	private String tel;
	/**
	 *手机
	 */
	private String mobile;
	/**
	 *邮箱
	 */
	private String email;
	/**
	 *传真
	 */
	private String fax;
	/**
	 *办公地址
	 */
	private String addr;
	/**
	 *用户状态（0,正常 1停用）
	 */
	private Long userState;
	/**
	 *创建人ID
	 */
	private Long createUserId;
	/**
	 *创建时间
	 */
	private Date createTime;
	/**
	 *修改时间
	 */
	private Date updateTime;
	/**
	 *用户类型 表示用户的级别  0最高权限管理员，1普通管理员，2普通用户
	 */
	private String userType;
	/**
	 *最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 *最后登录IP地址
	 */
	private String lastLoginIp;
	/**
	 *查询起始时间
	 */
	private Date createTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date createTimeAfter;
	/**
	 *查询起始时间
	 */
	private Date updateTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date updateTimeAfter;
	/**
	 *查询起始时间
	 */
	private Date lastLoginTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date lastLoginTimeAfter;
	/**
	 * 单位ID
	 */
	private Long unitId;
	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 用户所在单位详情
	 */
	private SysUnit sysunit;
	
	/**
	 * 用户所在部门详情
	 */
	private Department dept;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 单位类型id
	 */
	private String parentId;
	/**
	 * 用户id集
	 */
	private Long[] idTemp;
	private Long[] unitIdTemp;
	
	/**
	 * 用户所属管理员id
	 */
	private Long userParentId;

	public Long getUserParentId() {
		return userParentId;
	}

	public void setUserParentId(Long userParentId) {
		this.userParentId = userParentId;
	}

	public SysUnit getSysunit() {
		return sysunit;
	}

	public void setSysunit(SysUnit sysunit) {
		this.sysunit = sysunit;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * 安全认证信息
	 */
	private Safety safety;
	


	/**
	 * safety.
	 *
	 * @return  the safety
	 * @since   JDK 1.6
	 */
	public Safety getSafety() {
		return safety;
	}

	/**
	 * safety.
	 *
	 * @param   safety    the safety to set
	 * @since   JDK 1.6
	 */
	public void setSafety(Safety safety) {
		this.safety = safety;
	}

	/**	 
	 *设置 :用户id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :用户id
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :用户名（账户登录所用）
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**	 
	 *获取 :用户名（账户登录所用）
	 */
	public String getUserCode() {
		return this.userCode;
	}

	/**	 
	 *设置 :密码
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**	 
	 *获取 :密码
	 */
	public String getUserPass() {
		return this.userPass;
	}

	/**	 
	 *设置 :用户中文名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**	 
	 *获取 :用户中文名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**	 
	 *设置 :电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**	 
	 *获取 :电话
	 */
	public String getTel() {
		return this.tel;
	}

	/**	 
	 *设置 :手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**	 
	 *获取 :手机
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**	 
	 *设置 :邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**	 
	 *获取 :邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**	 
	 *设置 :传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**	 
	 *获取 :传真
	 */
	public String getFax() {
		return this.fax;
	}

	/**	 
	 *设置 :办公地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**	 
	 *获取 :办公地址
	 */
	public String getAddr() {
		return this.addr;
	}

	/**	 
	 *设置 :用户状态（0,正常 1停用）
	 */
	public void setUserState(Long userState) {
		this.userState = userState;
	}

	/**	 
	 *获取 :用户状态（0,正常 1停用）
	 */
	public Long getUserState() {
		return this.userState;
	}

	/**	 
	 *设置 :创建人ID
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**	 
	 *获取 :创建人ID
	 */
	public Long getCreateUserId() {
		return this.createUserId;
	}

	/**	 
	 *设置 :创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**	 
	 *获取 :创建时间
	 */
	public Date getCreateTime() {
		if(this.createTime==null){
			return new Date();
		}
		return this.createTime;
	}

	/**	 
	 *设置 :修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**	 
	 *获取 :修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**	 
	 *设置 :用户类型
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**	 
	 *获取 :用户类型
	 */
	public String getUserType() {
		return this.userType;
	}

	/**	 
	 *设置 :最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**	 
	 *获取 :最后登录时间
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**	 
	 *设置 :最后登录IP地址
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**	 
	 *获取 :最后登录IP地址
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**	 
	 *获取 :createTimeBefore
	 */
    public Date getCreateTimeBefore() {
        return this.createTimeBefore;
    }

	/**	 
	 *设置 :createTimeBefore
	 */
    public void setCreateTimeBefore(Date createTimeBefore) {
        this.createTimeBefore = createTimeBefore;
    }


	/**	 
	 *获取 :createTimeAfter
	 */
    public Date getCreateTimeAfter() {
        return this.createTimeAfter;
    }

	/**	 
	 *设置 :createTimeAfter
	 */
    public void setCreateTimeAfter(Date createTimeAfter) {
        this.createTimeAfter = createTimeAfter;
    }

	/**	 
	 *获取 :updateTimeBefore
	 */
    public Date getUpdateTimeBefore() {
        return this.updateTimeBefore;
    }

	/**	 
	 *设置 :updateTimeBefore
	 */
    public void setUpdateTimeBefore(Date updateTimeBefore) {
        this.updateTimeBefore = updateTimeBefore;
    }


	/**	 
	 *获取 :updateTimeAfter
	 */
    public Date getUpdateTimeAfter() {
        return this.updateTimeAfter;
    }

	/**	 
	 *设置 :updateTimeAfter
	 */
    public void setUpdateTimeAfter(Date updateTimeAfter) {
        this.updateTimeAfter = updateTimeAfter;
    }

	/**	 
	 *获取 :lastLoginTimeBefore
	 */
    public Date getLastLoginTimeBefore() {
        return this.lastLoginTimeBefore;
    }

	/**	 
	 *设置 :lastLoginTimeBefore
	 */
    public void setLastLoginTimeBefore(Date lastLoginTimeBefore) {
        this.lastLoginTimeBefore = lastLoginTimeBefore;
    }


	/**	 
	 *获取 :lastLoginTimeAfter
	 */
    public Date getLastLoginTimeAfter() {
        return this.lastLoginTimeAfter;
    }

	/**	 
	 *设置 :lastLoginTimeAfter
	 */
    public void setLastLoginTimeAfter(Date lastLoginTimeAfter) {
        this.lastLoginTimeAfter = lastLoginTimeAfter;
    }

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long[] getIdTemp() {
		return idTemp;
	}

	public void setIdTemp(Long[] idTemp) {
		this.idTemp = idTemp;
	}

	public Long[] getUnitIdTemp() {
		return unitIdTemp;
	}

	public void setUnitIdTemp(Long[] unitIdTemp) {
		this.unitIdTemp = unitIdTemp;
	}
}
