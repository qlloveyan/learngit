/**
 * Project Name:smcs<br>
 * File Name:Manager.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:07:04<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;

/**
 * ClassName:Manager.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:04<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Manager implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private Long id;
	/**
	 *管理员登录时使用的用户名，唯一标识管理员的身份
	 */
	private String mname;
	/**
	 *管理员密码
	 */
	private String pswd;
	/**
	 *管理员真实姓名
	 */
	private String realname;
	/**
	 *管理员性别
	 */
	private String sex;
	/**
	 *管理员年龄
	 */
	private String age;
	/**
	 *管理员联系电话
	 */
	private String tel;
	/**
	 *管理员的权限级别，用来限制管理员的管理权限
	 */
	private String entitle;
	


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

	/**	 
	 *设置 :管理员登录时使用的用户名，唯一标识管理员的身份
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**	 
	 *获取 :管理员登录时使用的用户名，唯一标识管理员的身份
	 */
	public String getMname() {
		return this.mname;
	}

	/**	 
	 *设置 :管理员密码
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	/**	 
	 *获取 :管理员密码
	 */
	public String getPswd() {
		return this.pswd;
	}

	/**	 
	 *设置 :管理员真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**	 
	 *获取 :管理员真实姓名
	 */
	public String getRealname() {
		return this.realname;
	}

	/**	 
	 *设置 :管理员性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**	 
	 *获取 :管理员性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**	 
	 *设置 :管理员年龄
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**	 
	 *获取 :管理员年龄
	 */
	public String getAge() {
		return this.age;
	}

	/**	 
	 *设置 :管理员联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**	 
	 *获取 :管理员联系电话
	 */
	public String getTel() {
		return this.tel;
	}

	/**	 
	 *设置 :管理员的权限级别，用来限制管理员的管理权限
	 */
	public void setEntitle(String entitle) {
		this.entitle = entitle;
	}

	/**	 
	 *获取 :管理员的权限级别，用来限制管理员的管理权限
	 */
	public String getEntitle() {
		return this.entitle;
	}

}
