/**
 * Project Name:shutdown
 * File Name:Storage.java
 * Package Name:com.surfilter.self.cp
 * Date:2016年2月17日上午9:45:36
 *
*/

package com.surfilter.self.cp;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName:Storage <br/>
 * Function: 仓库类. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月17日 上午9:45:36 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Storage {
	
	private int num = 0;

	//定义全局变量用于存储创建的产品,这里采用阻塞队列做处理
	private LinkedBlockingQueue<Integer> products = new LinkedBlockingQueue<Integer>(10);
	
	public LinkedBlockingQueue<Integer> getProducts() {
		return products;
	}
	public void setProducts(LinkedBlockingQueue<Integer> products) {
		this.products = products;
	}


	public void product() throws Exception{
		products.put(num);
		System.out.println("【生产产品】："+num);
		num++;
	}
	
	
	public Integer consumer() throws Exception{
		return products.take();
	}
}

