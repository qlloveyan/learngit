/**
 * Project Name:smcs<br>
 * File Name:Safety.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年03月11日  下午02:05:48<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;

import java.util.Date;

/**
 * ClassName:Safety.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年03月11日  下午02:05:48<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Safety extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;
	/**
	 *Ukey编码
	 */
	private String ukeyCode;
	/**
	 *UKEY_ID
	 */
	private String ukeyId;
	/**
	 *用户ID关联SY0004表的主键
	 */
	private Long userId;
	/**
	 *最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 *密码错误次数，每次成功登录后清零
	 */
	private Long passErrorCount;
	/**
	 *密码过期时间
	 */
	private Date passPastDate;
	/**
	 *用户绑定IP
	 */
	private String boundIp;
	/**
	 *用户绑定ip值
	 */
	private Long boundIpValue;
	/**
	 *用户锁定时间
	 */
	private Date lockTime;
	/**
	 *密码最后修改时间
	 */
	private Date passUpdateTime;
	/**
	 *查询起始时间
	 */
	private Date lockTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date lockTimeAfter;
	/**
	 *查询起始时间
	 */
	private Date passUpdateTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date passUpdateTimeAfter;
	/**
	 *查询起始时间
	 */
	private Date passPastDateBefore;
	/**
	 *查询截止时间
	 */
	private Date passPastDateAfter;
	/**
	 *查询起始时间
	 */
	private Date lastLoginTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date lastLoginTimeAfter;
	/**
	 * 用户登陆状态
	 */
	private Long loginState;

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
	 *设置 :Ukey编码
	 */
	public void setUkeyCode(String ukeyCode) {
		this.ukeyCode = ukeyCode;
	}

	/**	 
	 *获取 :Ukey编码
	 */
	public String getUkeyCode() {
		return this.ukeyCode;
	}

	/**	 
	 *设置 :UKEY_ID
	 */
	public void setUkeyId(String ukeyId) {
		this.ukeyId = ukeyId;
	}

	/**	 
	 *获取 :UKEY_ID
	 */
	public String getUkeyId() {
		return this.ukeyId;
	}

	/**	 
	 *设置 :用户ID关联SY0004表的主键
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**	 
	 *获取 :用户ID关联SY0004表的主键
	 */
	public Long getUserId() {
		return this.userId;
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
	 *设置 :密码错误次数，每次成功登录后清零
	 */
	public void setPassErrorCount(Long passErrorCount) {
		this.passErrorCount = passErrorCount;
	}

	/**	 
	 *获取 :密码错误次数，每次成功登录后清零
	 */
	public Long getPassErrorCount() {
		return this.passErrorCount;
	}

	/**	 
	 *设置 :密码过期时间
	 */
	public void setPassPastDate(Date passPastDate) {
		this.passPastDate = passPastDate;
	}

	/**	 
	 *获取 :密码过期时间
	 */
	public Date getPassPastDate() {
		return this.passPastDate;
	}

	/**	 
	 *设置 :用户绑定IP
	 */
	public void setBoundIp(String boundIp) {
		this.boundIp = boundIp;
	}

	/**	 
	 *获取 :用户绑定IP
	 */
	public String getBoundIp() {
		return this.boundIp;
	}

	/**	 
	 *设置 :用户绑定ip值
	 */
	public void setBoundIpValue(Long boundIpValue) {
		this.boundIpValue = boundIpValue;
	}

	/**	 
	 *获取 :用户绑定ip值
	 */
	public Long getBoundIpValue() {
		return this.boundIpValue;
	}

	/**	 
	 *设置 :用户锁定时间
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	/**	 
	 *获取 :用户锁定时间
	 */
	public Date getLockTime() {
		return this.lockTime;
	}

	/**	 
	 *设置 :密码最后修改时间
	 */
	public void setPassUpdateTime(Date passUpdateTime) {
		this.passUpdateTime = passUpdateTime;
	}

	/**	 
	 *获取 :密码最后修改时间
	 */
	public Date getPassUpdateTime() {
		return this.passUpdateTime;
	}

	/**	 
	 *获取 :lockTimeBefore
	 */
    public Date getLockTimeBefore() {
        return this.lockTimeBefore;
    }

	/**	 
	 *设置 :lockTimeBefore
	 */
    public void setLockTimeBefore(Date lockTimeBefore) {
        this.lockTimeBefore = lockTimeBefore;
    }


	/**	 
	 *获取 :lockTimeAfter
	 */
    public Date getLockTimeAfter() {
        return this.lockTimeAfter;
    }

	/**	 
	 *设置 :lockTimeAfter
	 */
    public void setLockTimeAfter(Date lockTimeAfter) {
        this.lockTimeAfter = lockTimeAfter;
    }

	/**	 
	 *获取 :passUpdateTimeBefore
	 */
    public Date getPassUpdateTimeBefore() {
        return this.passUpdateTimeBefore;
    }

	/**	 
	 *设置 :passUpdateTimeBefore
	 */
    public void setPassUpdateTimeBefore(Date passUpdateTimeBefore) {
        this.passUpdateTimeBefore = passUpdateTimeBefore;
    }


	/**	 
	 *获取 :passUpdateTimeAfter
	 */
    public Date getPassUpdateTimeAfter() {
        return this.passUpdateTimeAfter;
    }

	/**	 
	 *设置 :passUpdateTimeAfter
	 */
    public void setPassUpdateTimeAfter(Date passUpdateTimeAfter) {
        this.passUpdateTimeAfter = passUpdateTimeAfter;
    }

	/**	 
	 *获取 :passPastDateBefore
	 */
    public Date getPassPastDateBefore() {
        return this.passPastDateBefore;
    }

	/**	 
	 *设置 :passPastDateBefore
	 */
    public void setPassPastDateBefore(Date passPastDateBefore) {
        this.passPastDateBefore = passPastDateBefore;
    }


	/**	 
	 *获取 :passPastDateAfter
	 */
    public Date getPassPastDateAfter() {
        return this.passPastDateAfter;
    }

	/**	 
	 *设置 :passPastDateAfter
	 */
    public void setPassPastDateAfter(Date passPastDateAfter) {
        this.passPastDateAfter = passPastDateAfter;
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

	public Long getLoginState() {
		return loginState;
	}

	public void setLoginState(Long loginState) {
		this.loginState = loginState;
	}
    
}
