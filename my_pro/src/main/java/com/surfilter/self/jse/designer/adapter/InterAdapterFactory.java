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
 * Function: 接口的适配器模式学习. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午8:39:57 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class InterAdapterFactory {
	//接口的适配器,一般用于对于一个接口实现时，并不希望实现所有的方法,因此可以采用中间类的方式去做处理
	
	public static void main(String[] args) {
		InterAdapterFactory caf = new InterAdapterFactory();
		Adapeter dog = caf.new Dog();
		dog.say();
		//现在我用A进行处理不满足需求,需要在A的say方法上添加其它逻辑,但是不能修改A的源代码。所以在不改变原有方法的逻辑上,采用持有实例的方式做扩展
		Adapeter bird = caf.new Bird();
		bird.fly();
	}
	
	//原始接口
	interface Source{
		void say();
		void fly();
	}
	
	//中间适配类
	class Adapeter implements Source{

		//适配器类实现之后并不需要实现所有的方法，可以采用需要的子类去实现对应的方法
		@Override
		public void say() {
		}

		@Override
		public void fly() {
		}
		
	}
	
	//子类通过继承中间适配类之后,不需要实现所有的方法
	class Dog extends Adapeter{
		@Override
		public void say(){
			System.out.println("汪汪汪……");
		}
	}
	
	class Bird extends Adapeter{
		
		@Override
		public void fly(){
			System.out.println("I'm flying……");
		}
	}
}

