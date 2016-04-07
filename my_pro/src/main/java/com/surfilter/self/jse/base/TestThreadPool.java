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
import java.util.concurrent.atomic.AtomicInteger;

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
		
		TestThreadPool tt = new TestThreadPool();
		
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
		
		//常见用法说
		Thread t = new Thread(tt.new MyThread("测试线程!"));
		es.submit(t);
		es.shutdown();
		
		//在这里介绍 java.util.concurrent.atomic下的封装类,例如AtomicInteger
		AtomicInteger ai = new AtomicInteger();
		ai.set(2);//设置一个整型值
		System.out.println(ai.get());//输出2
		ai.compareAndSet(3, 4);//参数：(预期值,修改值)通过比较预期值与原值是否相同,相同则设置修改值
		System.out.println(ai.get());//输出2
		ai.compareAndSet(2, 4);
		System.out.println(ai.get());//输出4
		ai.getAndAdd(4);//取值并相加
		System.out.println(ai.get());//输出8
		
		/**
		 * 上面的功能可以理解为一个比较高级的计数器,为什么不用count++或++count的方式去实现,
		 * 原因在与多线程高并发的情况下,传统的计数器在处理此情况下会存在线程并发问题,java.util.concurrent.atomic下封装的类,已经比较好的
		 * 支持了高并发的情况,不需要自己去实现,因此在多线程的情况下,如果有用到这种情况的功能,建议采用该包下的封装类
		 */
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

