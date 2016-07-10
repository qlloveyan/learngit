/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.base.anno
 * Date:2016年7月4日下午9:37:57
 *
*/

package com.surfilter.self.jse.base.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Generated;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月4日 下午9:37:57 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	@MyAnnoation
	public void name() {
		System.out.println("annoation 注释!");
	}
	
	public static void main(String[] args) {
		try {
			Class cls = TestMain.class;
			Method[] ms = cls.getDeclaredMethods();
			for( Method temp : ms ){
				if( temp.isAnnotationPresent(MyAnnoation.class) ){
					temp.invoke(cls.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

