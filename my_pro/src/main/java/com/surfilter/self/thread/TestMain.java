/**
 * Project Name:shutdown
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.thread
 * Date:2016年2月18日下午3:22:54
 *
*/

package com.surfilter.self.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月18日 下午3:22:54 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		final AtomicInteger j = new AtomicInteger(0) ;
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0 ; i < 10 ; i++){
			Runnable run = new Runnable() {
				public void run() {
					try {
						Thread.sleep(2000);
						int i = j.intValue();
						j.set(i+1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			es.submit(run);
		}
		es.shutdown();
		System.out.println(j);
	}
}

