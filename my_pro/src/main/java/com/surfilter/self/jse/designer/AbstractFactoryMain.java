/**
 * Project Name:my_pro
 * File Name:AbstractFactoryMain.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午4:38:19
 *
*/

package com.surfilter.self.jse.designer;

/**
 * ClassName:AbstractFactoryMain <br/>
 * Function: 抽象工厂类,用于解决工厂类的不可扩展问题. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午4:38:19 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AbstractFactoryMain {

	public static void main(String[] args) {
		/**
		 * 抽象工厂模式的方式在于,继承一个公共的接口,通过父类引用指向子类对象的方式去做扩展处理
		 */
		try {
			AbstractFactoryMain afm = new AbstractFactoryMain();
			//1、我现在需要一个String对象,我写好了一个String的工厂类
			FatherFactory fatherFactory = afm.new StringFactory();
			System.out.println(fatherFactory.createObj());
			//2、我现在又需要一个List对象,那么扩展方便,实现父工厂类接口,改变引用实例即可
			fatherFactory = afm.new ListFactory();
			System.out.println(fatherFactory.createObj());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ClassName: FatherFactory <br/>
	 * Function: 抽象父类接口. <br/>
	 * Reason: TODO ADD REASON(可选). <br/>
	 * date: 2016年3月18日 下午4:40:44 <br/>
	 *
	 * @author quanli
	 * @version AbstractFactoryMain
	 * @since JDK 1.6
	 */
	interface FatherFactory{
		//定义一个全局的建造方法
		public Object createObj() throws ClassNotFoundException;
	}
	
	//String建造者
	class StringFactory implements FatherFactory{

		@Override
		public Object createObj() throws ClassNotFoundException{
			return Class.forName("java.lang.String");
		}
	}
	
	//List建造者
	class ListFactory implements FatherFactory{

		@Override
		public Object createObj() throws ClassNotFoundException{
			return Class.forName("java.util.ArrayList");
		}
	}
}

