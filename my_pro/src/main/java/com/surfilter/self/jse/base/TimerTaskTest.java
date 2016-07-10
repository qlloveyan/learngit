/**
 * Project Name:my_pro
 * File Name:TimeTaskTest.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年7月10日下午1:04:44
 *
*/

package com.surfilter.self.jse.base;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName:TimeTaskTest <br/>
 * Function: 测试java任务调度. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月10日 下午1:04:44 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TimerTaskTest {

	public static void main(String[] args) {
		TimerTaskTest ttt = new TimerTaskTest();
		MyTask mt = ttt.new MyTask();
		Timer timer = new Timer("定时任务线程");
//		timer.schedule(mt,new Date(),3000);
//		timer.schedule(mt, 3000, 3000);
		timer.schedule(mt, new Date());
	}
	
	/**
	 * 
	 * ClassName: MyTask <br/>
	 * Function: 自定义时间任务. <br/>
	 *
	 * @author quanli
	 * @version TimerTaskTest
	 * @since JDK 1.6
	 */
	class MyTask extends TimerTask{

		@Override
		public void run() {
			System.out.println("执行……");
		}
		
	}
}

