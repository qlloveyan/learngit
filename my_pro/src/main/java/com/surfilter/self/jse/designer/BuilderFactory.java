/**
 * Project Name:my_pro
 * File Name:BuilderFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午7:46:23
 *
*/

package com.surfilter.self.jse.designer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:BuilderFactory <br/>
 * Function: 建造者模式实例. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午7:46:23 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BuilderFactory {
	//所谓建造者,类似工厂模式,但是创建者适用于创建多个类实例
	public static void main(String[] args) {
		List<String> strs = StringsBuilder.createStrs(10);
		for(String str :strs){
			System.out.println(str);
		}
	}
	
	/**
	 * 
	 * ClassName: StringsBuilder <br/>
	 * Function: String创建者. <br/>
	 * Reason: TODO ADD REASON(可选). <br/>
	 * date: 2016年3月18日 下午7:55:17 <br/>
	 *
	 * @author quanli
	 * @version BuilderFactory
	 * @since JDK 1.6
	 */
	static class StringsBuilder{
		
		public static List<String> createStrs(int num){
			List<String> strs = new ArrayList<String>();
			for(int i = 0 ; i < num ; i++){
				strs.add("String-"+i);
			}
			return strs;
		}
	}
}

