/**
 * Project Name:my_pro
 * File Name:TestSerial.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年4月25日下午8:26:17
 *
*/

package com.surfilter.self.jse.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * ClassName:TestSerial <br/>
 * Function: 测试序列化. <br/>
 * Date:     2016年4月25日 下午8:26:17 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestSerial {

	public static void main(String[] args) {
		//比较重要的一个类 ObjectOutputStream
		try {
			FileOutputStream fos = new FileOutputStream(new File("D:/test.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject("我在测试");
			oos.writeObject("\n");
			oos.writeObject("呵呵……");
			oos.flush();
			
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

