/**
 * Project Name:smcs<br>
 * File Name:User.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:06:32<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;

/**
 * ClassName:User.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:06:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private String id;
	/**
	 *用户登录时使用的用户名，唯一标识用户的身份
	 */
	private String username;
	/**
	 *用户密码
	 */
	private String password;
	/**
	 *用户真实姓名
	 */
	private String realname;
	/**
	 *用户性别
	 */
	private String sex;
	/**
	 *用户年龄
	 */
	private String age;
	/**
	 *用户联系电话
	 */
	private String tel;
	


	/**	 
	 *设置 :主键id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键id
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :用户登录时使用的用户名，唯一标识用户的身份
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**	 
	 *获取 :用户登录时使用的用户名，唯一标识用户的身份
	 */
	public String getUsername() {
		return this.username;
	}

	/**	 
	 *设置 :用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**	 
	 *获取 :用户密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**	 
	 *设置 :用户真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**	 
	 *获取 :用户真实姓名
	 */
	public String getRealname() {
		return this.realname;
	}

	/**	 
	 *设置 :用户性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**	 
	 *获取 :用户性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**	 
	 *设置 :用户年龄
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**	 
	 *获取 :用户年龄
	 */
	public String getAge() {
		return this.age;
	}

	/**	 
	 *设置 :用户联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**	 
	 *获取 :用户联系电话
	 */
	public String getTel() {
		return this.tel;
	}

}
