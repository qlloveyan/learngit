/**
 * Project Name:my_pro
 * File Name:ChainFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年4月7日下午8:18:41
 *
*/

package com.surfilter.self.jse.designer;
/**
 * ClassName:ChainFactory <br/>
 * Function: 责任链模式. <br/>
 * Date:     2016年4月7日 下午8:18:41 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ChainFactory {

	/**
	 * 责任链模式：对象包含对象的实例,形成链式结构
	 */
	
	public static void main(String[] args) {
		ChainFactory cf = new ChainFactory();
		ChainClass cc1 = cf.new ChainClass("ql");
		ChainClass cc2 = cf.new ChainClass("zqh");
		ChainClass cc3 = cf.new ChainClass("wxj");
		
		cc2.setObj(cc1);
		cc3.setObj(cc2);
		
		cc3.doSth();
	}
	
	class ChainClass{
		private String name;
		
		private ChainClass obj;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ChainClass getObj() {
			return obj;
		}

		public void setObj(ChainClass obj) {
			this.obj = obj;
		}

		public ChainClass(String name) {
			super();
			this.name = name;
		}
		
		public void doSth(){
			System.out.println("name ："+this.name +"做什么事!");
			if(obj!=null){
				obj.doSth();
			}
		}
	}
}

