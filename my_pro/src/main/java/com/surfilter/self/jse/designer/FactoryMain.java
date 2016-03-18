/**
 * Project Name:my_pro
 * File Name:FactoryMain.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午4:22:15
 *
*/

package com.surfilter.self.jse.designer;
/**
 * ClassName:FactoryMain <br/>
 * Function: 对三种工厂模式的讲解说明. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午4:22:15 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FactoryMain {
	
	public static void main(String[] args) {
		FactoryMain obj = new FactoryMain();
		try {
			SimpleFacotry sf = obj.new SimpleFacotry();
			System.out.println(sf.createObj("java.lang.String"));
			
			System.out.println(StaticFactory.createObj("java.lang.String"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ClassName: SimpleFacotry <br/>
	 * Function: 简单工厂模式. <br/>
	 * Reason: TODO ADD REASON(可选). <br/>
	 * date: 2016年3月18日 下午4:23:24 <br/>
	 *
	 * @author quanli
	 * @version FactoryMain
	 * @since JDK 1.6
	 */
	class SimpleFacotry{
		
		public SimpleFacotry(){}
		
		//创建一个对象的实例
		public Object createObj(String classStr) throws ClassNotFoundException{
			return Class.forName(classStr);
		}
	}
	
	/**
	 * 
	 * ClassName: AbstractFactory <br/>
	 * Function: 静态工厂类,一般方法定义为static对象，然后直接创建. <br/>
	 * Reason: TODO ADD REASON(可选). <br/>
	 * date: 2016年3月18日 下午4:25:02 <br/>
	 *
	 * @author quanli
	 * @version FactoryMain
	 * @since JDK 1.6
	 */
	static class StaticFactory{
		public static Object createObj(String classStr) throws ClassNotFoundException{
			return Class.forName(classStr);
		}
	}
}

