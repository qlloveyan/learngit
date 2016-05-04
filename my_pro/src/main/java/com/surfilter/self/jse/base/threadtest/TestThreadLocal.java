/**
 * Project Name:my_pro
 * File Name:TestThreadLocal.java
 * Package Name:com.surfilter.self.jse.base.threadtest
 * Date:2016年4月25日下午9:02:42
 *
*/

package com.surfilter.self.jse.base.threadtest;

/**
 * ClassName:TestThreadLocal <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月25日 下午9:02:42 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestThreadLocal implements Runnable{
	
	public static ThreadLocal session = new ThreadLocal();

	@Override
	public void run() {
		session.set("quanli");
	}
	

}

