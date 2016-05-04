/**
 * Project Name:my_pro
 * File Name:TestFileOpt.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年4月30日下午10:31:25
 *
*/

package com.surfilter.self.jse;

import java.io.File;

/**
 * ClassName:TestFileOpt <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月30日 下午10:31:25 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestFileOpt {

	public static void main(String[] args) {
		File file = new File("c:/test");
		file.mkdir();
		file = new File("c:/test","test.txt");
	}
}


