/**
 * Project Name:my_pro
 * File Name:MyInvocationHandler.java
 * Package Name:com.surfilter.self.j2ee.spring.aop.proxy
 * Date:2016年4月17日下午1:34:16
 *
*/

package com.surfilter.self.j2ee.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

import com.surfilter.self.j2ee.spring.aop.dao.UserDao;

/**
 * ClassName:MyInvocationHandler <br/>
 * Function: 自己实现的动态代理类. <br/>
 * Date:     2016年4月17日 下午1:34:16 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MyInvocationHandler implements InvocationHandler {
	
	private Logger logger = Logger.getLogger(MyInvocationHandler.class);
	
	//需要持有被代理的类
	private UserDao userDao;
	
	public MyInvocationHandler(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		logger.info("动态代理之前1");
		Object result = method.invoke(userDao, args);
		logger.info("动态代理之后1");
		return result;
	}

	//获取被代理类
	public Object getProxy(){
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), userDao.getClass().getInterfaces(), this);
	}
}

