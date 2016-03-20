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
 * Function: 对象的适配器模式学习. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午8:39:57 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ObjectAdapterFactory {
	//对象的适配器,一般用于类不满足需求,需要在原方法的基础上做修改,一般用于持有目标类,然后做相应的修改
	
	public static void main(String[] args) {
		ObjectAdapterFactory caf = new ObjectAdapterFactory();
		A a = caf.new A();
		a.say();
		//现在我用A进行处理不满足需求,需要在A的say方法上添加其它逻辑,但是不能修改A的源代码。所以在不改变原有方法的逻辑上,采用持有实例的方式做扩展
		B b = caf.new B();
		b.say();
	}
	
	class A{
		public void say(){
			System.out.println("我是原始方法!");
		}
		public void say1(){
			System.out.println("我是另一个原始方法!");
		}
	}
	
	class B{
		
		private A a = new A();//持有A对象
		
		public void say(){
			System.out.println("我在适配原始方法!");
			a.say();
			System.out.println("我在适配原始方法!");
		}
	}
}

