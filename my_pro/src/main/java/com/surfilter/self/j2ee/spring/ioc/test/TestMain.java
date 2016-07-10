/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.j2ee.spring.test
 * Date:2016年4月4日下午3:03:14
 *
*/

package com.surfilter.self.j2ee.spring.ioc.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.surfilter.self.j2ee.spring.ioc.entity.User;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月4日 下午3:03:14 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		//加载Spring的配置文件
		//1、加载src下的xml配置文件
//		BeanFactory factory = new ClassPathXmlApplicationContext("application.xml");
		//2、采用文件系统加载对应的配置文件
		BeanFactory factory = new FileSystemXmlApplicationContext("/src/main/java/com/surfilter/self/j2ee/spring/ioc/application.xml");
//		UserDao userDao = (UserDao) factory.getBean("userDao");
//		userDao.add();
		
		User user = (User) factory.getBean("user");
		System.out.println(user.getUserName());
		System.out.println(user.getKecheng().size());
	}
}

