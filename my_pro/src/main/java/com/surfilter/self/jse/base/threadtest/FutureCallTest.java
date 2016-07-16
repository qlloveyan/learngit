/**
 * Project Name:my_pro
 * File Name:FutureCallTest.java
 * Package Name:com.surfilter.self.jse.base.threadtest
 * Date:2016年7月10日上午11:26:46
 *
*/

package com.surfilter.self.jse.base.threadtest;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * ClassName:FutureCallTest <br/>
 * Function: 测试future相关的线程执行回调问题. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月10日 上午11:26:46 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FutureCallTest {

	public static void main(String[] args) {
		FutureCallTest test = new FutureCallTest();
		ExecutorService execu = Executors.newFixedThreadPool(5);
		try {
			//1、采用Future+Callable模式
			FutureSon task = test.new FutureSon();
			FutureSon task2 = test.new FutureSon();
			Future<Integer> result = execu.submit(task);
			Future<Integer> result2 = execu.submit(task2);
			execu.shutdown();
			System.out.println("结果：" + result.get() );
			System.out.println("结果：" + result2.get() );
			
			//2、采用FutureTask+Callable模式
//			FutureTaskSon futureTask = test.new FutureTaskSon();
//			FutureTask<Integer> ft = new FutureTask<Integer>(futureTask);
//			execu.submit(ft);
//			execu.shutdown();
//			System.out.println(ft.get());
//			//2.1 采用Thread执行
//			Thread t = new Thread(ft);
//			t.start();
//			System.out.println(ft.get());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ClassName: FutureSon <br/>
	 * Function: 采用future+callable处理线程返回. <br/>
	 *
	 * @author quanli
	 * @version FutureCallTest
	 * @since JDK 1.6
	 */
	class FutureSon implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("开始执行运算!");
			Integer sum = 0;
			for( int i = 0 ; i < new Random().nextInt(10) ; i++ ){
				sum += new Random().nextInt(100);
			}
			Thread.sleep(5000);
			return sum;
		}
		
	}
	
	/**
	 * 
	 * ClassName: FutureTaskSon <br/>
	 * Function: 采用FutureTask+Callable模式获取回调. <br/>
	 *
	 * @author quanli
	 * @version FutureCallTest
	 * @since JDK 1.6
	 */
	class FutureTaskSon implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("开始执行运算!");
			Integer sum = 0;
			for( int i = 0 ; i < new Random().nextInt(10) ; i++ ){
				sum += new Random().nextInt(100);
			}
			Thread.sleep(5000);
			return sum;
		}
		
	}
}

