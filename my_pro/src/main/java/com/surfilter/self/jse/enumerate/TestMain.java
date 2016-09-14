/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.enumerate
 * Date:2016年8月16日下午7:01:56
 *
*/

package com.surfilter.self.jse.enumerate;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年8月16日 下午7:01:56 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		//EunmSet使用测试
		EnumSet<EunmerateTest> test = EnumSet.of(EunmerateTest.Monday,EunmerateTest.Tuesday);
		//1、while迭代器
//		Iterator<EunmerateTest> iter = test.iterator();
//		while( iter.hasNext() ){
//			System.out.println(iter.next().toString());
//		}
		//2、for循环迭代器
		for( Iterator<EunmerateTest> iter = test.iterator(); iter.hasNext() ; ){
			System.out.println(iter.next().toString());
		}
		//3、foreach
		for(EunmerateTest temp : Arrays.asList(EunmerateTest.values())){
			System.out.println(temp.toString());
		}
		
		//EunmMap
//		EnumMap<EunmerateTest, String> maps = new EnumMap<EunmerateTest, String>(EunmerateTest.class);
//		maps.put(EunmerateTest.Monday, "周一要上班");
//		for(EunmerateTest temp : EunmerateTest.values()){
//			System.out.println(maps.get(temp));
//		}
		
	}
}

