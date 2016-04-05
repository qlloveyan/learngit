/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.j2ee.spring.aop
 * Date:2016年4月4日下午10:53:56
 *
*/

package com.surfilter.self.j2ee.spring.aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.surfilter.self.j2ee.spring.aop.dao.UserDao;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月4日 下午10:53:56 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		BeanFactory factory = new FileSystemXmlApplicationContext("/src/main/java/com/surfilter/self/j2ee/spring/aop/applicationContext.xml");
		UserDao dao = (UserDao) factory.getBean("userDaoNew");
		dao.add();
	}
}

