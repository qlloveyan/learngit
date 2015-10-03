/**
 * Project Name:smcs<br>
 * File Name:UserLogin.java<br>
 * Package Name:com.ql.basepro.system.model<br>
 * Date:2015年06月03日  下午08:12:32<br>
 *
*/
package com.ql.basepro.system.model;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:UserLogin.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月03日  下午08:12:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserLogin implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Long id;
	/**
	 *主键
	 */
	private String userid;
	/**
	 *用户登录时间
	 */
	private Date logintime;
	/**
	 *登录客户端版本
	 */
	private String remark;
	

	/**	 
	 *设置 :
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :主键
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**	 
	 *获取 :主键
	 */
	public String getUserid() {
		return this.userid;
	}


	/**	 
	 *设置 :用户登录时间
	 */
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	/**	 
	 *获取 :用户登录时间
	 */
	public Date getLogintime() {
		return this.logintime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
