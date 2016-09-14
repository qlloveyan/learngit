/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.enumerate.inter
 * Date:2016年8月16日下午9:13:00
 *
*/

package com.surfilter.self.jse.enumerate.inter;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年8月16日 下午9:13:00 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		List<IOperationEnum> lists = Arrays.asList(IOperationEnum.values());
		for( IOperationEnum temp : lists ){
			System.out.println(temp.deal(3, 5));
		}
		
		TestMain tm = new TestMain();
		tm.test(1,"awdawd",3,4.0);
	}
	
	public void test(Object... obj){
		for(Object temp:obj){
			System.out.println(temp.toString());
		}
	}
}

