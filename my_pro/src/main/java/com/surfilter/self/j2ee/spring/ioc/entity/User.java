/**
 * Project Name:my_pro
 * File Name:User.java
 * Package Name:com.surfilter.self.j2ee.spring.entity
 * Date:2016年4月4日下午3:11:31
 *
*/

package com.surfilter.self.j2ee.spring.ioc.entity;

import java.util.List;

/**
 * ClassName:User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月4日 下午3:11:31 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User {

	private String userName;
	
	private List<String> kecheng;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getKecheng() {
		return kecheng;
	}

	public void setKecheng(List<String> kecheng) {
		this.kecheng = kecheng;
	}

	public User() {
		super();
	}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}
	
}

