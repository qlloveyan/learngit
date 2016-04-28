/**
 * Project Name:my_pro
 * File Name:SingletonMain.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午4:55:11
 *
*/

package com.surfilter.self.jse.designer;

import java.lang.reflect.Constructor;
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
		//测试多线程情况下的单例模式
//		SingletonMain sm = new SingletonMain();
//		ExecutorService threadService = Executors.newFixedThreadPool(10);
//		for(int i = 0 ; i < 10 ; i++){
//			Thread t = new Thread( sm.new TestSingle(i));
//			threadService.execute(t);
//		}
//		threadService.shutdown();
		
		//采用反射做单例破解
		try {
			Class sd = Class.forName("com.surfilter.self.jse.designer.SingletonMain.SingleDog");
			Constructor constru = sd.getConstructor();
			constru.setAccessible(true);//获取构造函数设置参数为public即可
			SingleDog sd1 = (SingleDog) constru.newInstance();
			SingleDog sd2 = (SingleDog) constru.newInstance();
			
			System.out.println(sd1.equals(sd2));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
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
					if( instance == null ){
						//情况：线程A和B , A和B方法同时执行判断空操作,未赋值-空,A获取锁,赋值执行;释放锁,B从判断空之后执行,此时由于之前进入了空判断,所以可能还会执行一次赋值
						instance = new SingleDog();
					}
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

