/**
 * Project Name:my_pro
 * File Name:StateFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年4月7日下午9:04:56
 *
*/

package com.surfilter.self.jse.designer;
/**
 * ClassName:StateFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午9:04:56 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StateFactory {

	/**
	 *	状态模式：根据不同的状态做不同的处理操作
	 */
	
	public static void main(String[] args) {
		StateFactory sf = new StateFactory();
		StateClass sc = sf.new StateClass();
		sc.setState("1");
		sc.doSth();
		sc.setState("2");
		sc.doSth();
	}
	
	class StateClass{
		private String state;

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
		public void doSth(){
			switch (state) {
			case "1":
				System.out.println("我现在在线!");
				break;
			case "2":
				System.out.println("我现在离线!");
				break;
			default:
				break;
			}
		}
	}
}

