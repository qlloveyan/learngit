/**
 * Project Name:my_pro
 * File Name:TestFindBugs.java
 * Package Name:com.surfilter.self.testfindbugs
 * Date:2016年4月17日下午1:01:26
 *
*/

package com.surfilter.self.testfindbugs;

import java.util.Scanner;

/**
 * ClassName:TestFindBugs <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月17日 下午1:01:26 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestFindBugs {

	public static void main(String[] args) {
		int a = 0;
		
		int b = 4;
		
		System.out.println("请输入被除数");
		Scanner input = new Scanner(System.in);//此处,findbugs会提示一个警告,让你指定编码
//		Scanner input = new Scanner(System.in,"utf-8");
		a = input.nextInt();
		
		System.out.println(b/a);
	}
}

