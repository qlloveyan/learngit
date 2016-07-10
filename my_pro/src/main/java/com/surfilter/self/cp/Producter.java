/**
 * Project Name:shutdown
 * File Name:Producter.java
 * Package Name:com.surfilter.self.cp
 * Date:2016年2月17日上午9:53:29
 *
*/

package com.surfilter.self.cp;
/**
 * ClassName:Producter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月17日 上午9:53:29 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Producter implements Runnable {

	private Storage stor;
	
	public Producter(Storage stor) {
		super();
		this.stor = stor;
	}

	public void run() {
		while (true) {
			try {
				Thread t = Thread.currentThread();
				System.out.println("生产线程："+t.getName());
				stor.product();
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

