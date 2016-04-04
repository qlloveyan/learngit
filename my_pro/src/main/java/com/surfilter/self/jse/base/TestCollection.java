/**
 * Project Name:my_pro
 * File Name:TestCollection.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年4月1日下午7:27:03
 *
*/

package com.surfilter.self.jse.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;
import java.util.logging.Logger;

/**
 * ClassName:TestCollection <br/>
 * Function: 集合类使用学习. <br/>
 * Date:     2016年4月1日 下午7:27:03 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestCollection {

	public static void main(String[] args) {
		 long start = System.currentTimeMillis();
		 ArrayList list = new ArrayList();
		 Object obj = new Object();
		 for(int i=0;i<5000000;i++){
		 list.add(obj);
		 }
		 long end = System.currentTimeMillis();
		 System.out.println(end-start);

		 start = System.currentTimeMillis();
		 LinkedList list1 = new LinkedList();
		 Object obj1 = new Object();
		 for(int i=0;i<5000000;i++){
		 list1.add(obj1);
		 }
		 end = System.currentTimeMillis();
		 System.out.println(end-start);

		 start = System.currentTimeMillis();
		 Object obj2 = new Object();
		 for(int i=0;i<1000;i++){
		 list.add(0,obj2);
		 }
		 end = System.currentTimeMillis();
		 System.out.println(end-start);

		 start = System.currentTimeMillis();
		 Object obj3 = new Object();
		 for(int i=0;i<1000;i++){
		 list1.add(obj1);
		 }
		 end = System.currentTimeMillis();
		 System.out.println(end-start);

		 start = System.currentTimeMillis();
		 list.remove(0);
		 end = System.currentTimeMillis();
		 System.out.println(end-start);

		 start = System.currentTimeMillis();
		 list1.remove(250000);
		 end = System.currentTimeMillis();
		 System.out.println(end-start);
		
	}
}

