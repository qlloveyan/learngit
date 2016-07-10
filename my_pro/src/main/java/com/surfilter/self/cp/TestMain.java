/**
 * Project Name:shutdown
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.cp
 * Date:2016年2月17日上午9:55:18
 *
*/

package com.surfilter.self.cp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月17日 上午9:55:18 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		Storage stor = new Storage();
		
		ExecutorService execu = Executors.newFixedThreadPool(10);
		
		Producter p1 = new Producter(stor);
		execu.submit(p1);
		Producter p2 = new Producter(stor);
		execu.submit(p2);
		Producter p3 = new Producter(stor);
		execu.submit(p3);
		Consumer c1 = new Consumer(stor);
		execu.submit(c1);
//		Consumer c2 = new Consumer(stor);
//		execu.submit(c2);
//		Consumer c3 = new Consumer(stor);
//		execu.submit(c3);
//		Consumer c4 = new Consumer(stor);
//		execu.submit(c4);
		
		execu.shutdown();
	}
}

