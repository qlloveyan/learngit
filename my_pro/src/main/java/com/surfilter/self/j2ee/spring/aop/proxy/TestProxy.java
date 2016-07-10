/**
 * Project Name:my_pro
 * File Name:TestProxy.java
 * Package Name:com.surfilter.self.j2ee.spring.aop.proxy
 * Date:2016年4月17日下午1:38:55
 *
*/

package com.surfilter.self.j2ee.spring.aop.proxy;

import java.lang.reflect.Constructor;

import com.surfilter.self.j2ee.spring.aop.dao.UserDao;

/**
 * ClassName:TestProxy <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月17日 下午1:38:55 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestProxy {

	public static void main(String[] args) {
		UserDao dao = new UserDaoProxyImpl();
		try {
			
			Class cls = Class.forName("com.surfilter.self.j2ee.spring.aop.proxy.UserDaoProxyImpl");
			Constructor[] clss = cls.getConstructors();
			UserDao dao1 = (UserDao) clss[0].newInstance();
			dao1.add();
		} catch (Exception e) {
		}
		MyInvocationHandler mih = new MyInvocationHandler(dao);
		
		UserDao proxy = (UserDao) mih.getProxy();
		proxy.add();
	}
}

