/**
 * Project Name:my_pro
 * File Name:StrategyFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年4月7日下午4:43:26
 *
*/

package com.surfilter.self.jse.designer.strategy;

import com.surfilter.self.jse.designer.strategy.inter.ICaculate;

/**
 * ClassName:StrategyFactory <br/>
 * Function: 策略模式. <br/>
 * Date:     2016年4月7日 下午4:43:26 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 策略模式：制定一个特定的策略,其中变化的部分由子类实现
	 * 主要的方式一般是变化的方法放在接口供子类实现,不变的部分放置在一个类中,抽象类或者普通类
	 * 
	 * 例如：计算器
	 * 用抽象类封装表达式拆分规则,然后加减乘除的算法由子类继承接口去实现
	 */
	public static void main(String[] args) {
		ICaculate<Integer> cacu = new AddCaculate();
		System.out.println(cacu.caculate("1+2"));
		cacu = new SubtractCaculate();
		System.out.println(cacu.caculate("3-1"));
		
	}
}

