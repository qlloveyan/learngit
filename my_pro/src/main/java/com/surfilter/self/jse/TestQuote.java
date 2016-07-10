/**
 * Project Name:my_pro
 * File Name:TestQuote.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年7月6日下午9:41:23
 *
*/

package com.surfilter.self.jse;

import javax.sound.midi.Synthesizer;

/**
 * ClassName:TestQuote <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月6日 下午9:41:23 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestQuote {

	public static void main(String[] args) {
		int i = 1;
		String[] strs= new String[]{"1","2"};
		System.out.println(strs[1].hashCode());
		change(i,strs);
//		System.out.println( i + ";" +strs[1]);
	}

	private static void change(int i, String[] strs) {
		i = 3;
		System.out.println(strs[1].hashCode());
		strs[1] = "3";
	}
}

