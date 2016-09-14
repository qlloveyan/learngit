/**
 * Project Name:my_pro
 * File Name:BuyCandy.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年8月17日上午10:50:59
 *
*/

package com.surfilter.self.jse;

import java.math.BigDecimal;

/**
 * ClassName:BuyCandy <br/>
 * Function: 对使用float和double精确使用的理解. <br/>
 * Date:     2016年8月17日 上午10:50:59 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BuyCandy {

	public static void main(String[] args) {
		//1、采用传统的 double
		double total = 1.0;
		int count = 0;
		for( double temp = 0.1 ; temp <= total ; temp += 0.1 ){
			total -= temp;
			count += 1;
		}
//		System.out.println(count);
//		System.out.println(total);
		
		//2、BigDecimal
		count  = 0;
		final BigDecimal interval = new BigDecimal("0.1");
		BigDecimal funds = new BigDecimal("1.0");
		BigDecimal price = new BigDecimal("0.1");
		for( ; price.compareTo(funds) <= 0 ; price = price.add(interval) ){
			funds = funds.subtract(price);
			count += 1;
		}
//		System.out.println(funds);
//		System.out.println(count);
		
		//3、将 ￥1 变为 100 分 ,原则 :采用最小单位,将其变为整形表示
		//代码略
		
		//总结：需要高精度的计算时,尽量不要采用 double 、 float 类型做数据处理
		
	}
}

