/**
 * Project Name:my_pro
 * File Name:AdapterFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午8:39:57
 *
*/

package com.surfilter.self.jse.designer.adapter;
/**
 * ClassName:AdapterFactory <br/>
 * Function: 类的适配器模式学习. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午8:39:57 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ClassAdapterFactory {
	//类的适配器,一般用于类不满足需求,需要扩展新功能
	
	public static void main(String[] args) {
		ClassAdapterFactory caf = new ClassAdapterFactory();
		A a = caf.new A();
		a.say();
		//现在我用A进行处理不满足需求,需要重写A的say方法,但是不能修改A的源代码。所以需要重写
		a = caf.new B();
		a.say();
	}
	
	class A{
		public void say(){
			System.out.println("我是原始方法!");
		}
		public void say1(){
			System.out.println("我是另一个原始方法!");
		}
	}
	
	class B extends A{
		
		@Override
		public void say(){
			System.out.println("我是适配后的方法!");
		}
	}
}

