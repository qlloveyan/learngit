/**
 * Project Name:my_pro
 * File Name:TimerTaskTest2.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年7月10日下午8:50:30
 *
*/

package com.surfilter.self.jse.base;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:TimerTaskTest2 <br/>
 * Function: 研究解决Timer类相关缺陷的第二种方法. <br/>
 * Date:     2016年7月10日 下午8:50:30 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TimerTaskTest2 {

	//采用ScheduledExecutorService代替Timer类
	
	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		ses.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行……");
			}
		}, 0,3000, TimeUnit.MILLISECONDS);
	}
}

