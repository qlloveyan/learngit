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
public class XiaoMing {

	/**
	 * caculate:在此添加小明的计算方法
	 *
	 * @author quanli
	 * @param a		加数
	 * @param b		被加数
	 */
	public void caculate(int a,int b){
		new XiaoHong().caculate(a,b,this);
	}
	
	/**
	 * 
	 * syo:提供给小红的回调函数
	 *
	 * @author quanli
	 * @param a			加数
	 * @param b			被加数
	 * @param result	结果
	 * @since JDK 1.6
	 */
	public void syo(int a,int b,int result){
		System.out.println(a+"+"+b+"="+result);
	}
	
	public static void main(String[] args) {
		XiaoMing entity = new XiaoMing();
		entity.caculate(1, 2);
	}
}

