/**
 * Project Name:my_pro
 * File Name:TestThreadPool.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年4月6日下午8:03:44
 *
*/

package com.surfilter.self.jse.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:TestThreadPool <br/>
 * Function: 学习线程池. <br/>
 * Date:     2016年4月6日 下午8:03:44 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestThreadPool {
	
	/**
	 * 在java中,创建线程池常用Executors类,不要用Thread什么的,比较low(囧)
	 * 代码中将介绍具体的创建以及使用方式
	 */
	public static void main(String[] args) {
		
		ExecutorService es = null;
		/**
		 * 1、newCachedThreadPool:创建一个自适应的线程池
		 */
		es = Executors.newCachedThreadPool();
		/**
		 * 2、newFixedThreadPool:创建固定大小的线程池
		 */
		es = Executors.newFixedThreadPool(20);
		/**
		 * 3、newSingleThreadExecutor:创建一个单一线程
		 */
		es = Executors.newSingleThreadExecutor();
	}
	
	class MyThread implements Runnable{
		
		private String name;
		
		public MyThread(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("我是线程："+this.name);
		}
	}
}

