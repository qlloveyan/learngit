/**
 * Project Name:my_pro
 * File Name:User.java
 * Package Name:com.surfilter.self.jse.designer.iterator
 * Date:2016年4月7日下午8:09:38
 *
*/

package com.surfilter.self.jse.designer.iterator;
/**
 * ClassName:User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午8:09:38 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User {

	private String name;

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "name is :"+this.name;
	}
}

