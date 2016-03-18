/**
 * Project Name:shutdown
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年2月18日下午7:59:24
 *
*/

package com.surfilter.self.jse;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月18日 下午7:59:24 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		Collection<String> cs = new LinkedList<String>();
		
		/**
		 * 一、工厂模式 ：主要是一个具体的工厂类中常常使用一个特定的方法生成对象，而不是直接次采用构造函数的方式
		 * 	  常见写法：  **类   name = **类.newInstance();
		 */
		//抽象工厂代码
		DocumentBuilderFactory documentBuilder = DocumentBuilderFactory.newInstance();
		TransformerFactory transformFactory = TransformerFactory.newInstance();
		//工厂模式
		Calendar cal = Calendar.getInstance();
		
		/**
		 * 二、建造者模式
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("test");
	}
}

