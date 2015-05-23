/**
 * Project Name:smcs<br>
 * File Name:Staff.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:08:04<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;

/**
 * ClassName:Staff.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:08:04<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Staff implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *节目参加人员的编号
	 */
	private String id;
	/**
	 *节目参加人员的姓名
	 */
	private String name;
	/**
	 *节目参加人员的国籍
	 */
	private String country;
	/**
	 *节目参加人员的年龄
	 */
	private String age;
	/**
	 *节目参加人员的性别
	 */
	private String sex;
	/**
	 *职业类别
	 */
	private String career;
	/**
	 *参加的节目的名称
	 */
	private String pname;
	


	/**	 
	 *设置 :节目参加人员的编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :节目参加人员的编号
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :节目参加人员的姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**	 
	 *获取 :节目参加人员的姓名
	 */
	public String getName() {
		return this.name;
	}

	/**	 
	 *设置 :节目参加人员的国籍
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**	 
	 *获取 :节目参加人员的国籍
	 */
	public String getCountry() {
		return this.country;
	}

	/**	 
	 *设置 :节目参加人员的年龄
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**	 
	 *获取 :节目参加人员的年龄
	 */
	public String getAge() {
		return this.age;
	}

	/**	 
	 *设置 :节目参加人员的性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**	 
	 *获取 :节目参加人员的性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**	 
	 *设置 :职业类别
	 */
	public void setCareer(String career) {
		this.career = career;
	}

	/**	 
	 *获取 :职业类别
	 */
	public String getCareer() {
		return this.career;
	}

	/**	 
	 *设置 :参加的节目的名称
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**	 
	 *获取 :参加的节目的名称
	 */
	public String getPname() {
		return this.pname;
	}

}
