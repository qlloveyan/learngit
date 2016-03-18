/**
 * Project Name:my_pro
 * File Name:SingletonMain.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午4:55:11
 *
*/

package com.surfilter.self.jse.designer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:SingletonMain <br/>
 * Function: 单例模式,为一个具体的类创建单个实例. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午4:55:11 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SingletonMain {

	public static void main(String[] args) {
		SingletonMain sm = new SingletonMain();
		ExecutorService threadService = Executors.newFixedThreadPool(10);
		for(int i = 0 ; i < 10 ; i++){
			Thread t = new Thread( sm.new TestSingle(i));
			threadService.execute(t);
		}
		threadService.shutdown();
	}
	
	//单身狗类
	static class SingleDog{
		
		private static SingleDog instance = null;
		
		//私有化构造函数
		private SingleDog(){}
		
		public static SingleDog getSingleDog(int i){
			//有时候可能线程阻塞问题存在一定的非单例行为,因此在下面还需要添加同步设置
			if( instance == null ){
				synchronized (instance) {
					instance = new SingleDog();
				}
			}
			return instance;
		}
	}
	
	//测试类
	class TestSingle implements Runnable{

		private int i;
		
		public TestSingle(int i) {
			super();
			this.i = i;
		}
		
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}

		@Override
		public void run(){
			
			while (true) {
				try {
					SingleDog temp = SingleDog.getSingleDog(i);
					System.out.println(temp);
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

