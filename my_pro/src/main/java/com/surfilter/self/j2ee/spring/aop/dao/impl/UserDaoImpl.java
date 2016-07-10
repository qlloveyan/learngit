/**
 * Project Name:my_pro
 * File Name:UserDaoImpl.java
 * Package Name:com.surfilter.self.j2ee.spring.aop.dao.impl
 * Date:2016年4月4日下午10:50:14
 *
*/

package com.surfilter.self.j2ee.spring.aop.dao.impl;

import com.surfilter.self.j2ee.spring.aop.dao.UserDao;

/**
 * ClassName:UserDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月4日 下午10:50:14 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserDaoImpl implements UserDao {

	@Override
	public void add() {
		System.out.println("新增用户信息!");
	}

	@Override
	public void delete() {
		System.out.println("删除用户信息!");
	}

	
}

