/**
 * Project Name:my_pro
 * File Name:XiaoMing.java
 * Package Name:com.surfilter.self.jse.base.callbacktest
 * Date:2016年5月23日下午7:59:39
 *
*/

package com.surfilter.self.jse.base.callbacktest;
/**
 * ClassName:XiaoMing <br/>
 * Function: 探讨java回调机制. <br/>
 * Date:     2016年5月23日 下午7:59:39 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class XiaoHong {

	public void caculate(int a,int b,XiaoMing xiaoMing){
		xiaoMing.syo(a, b, a+b); //计算结果,回调原类回调函数
	}
}

