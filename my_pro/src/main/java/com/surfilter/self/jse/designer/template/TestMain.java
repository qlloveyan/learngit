/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.designer.template
 * Date:2016年4月7日下午5:30:48
 *
*/

package com.surfilter.self.jse.designer.template;
/**
 * ClassName:TestMain <br/>
 * Function: 模板方法. <br/>
 * Date:     2016年4月7日 下午5:30:48 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 模板方法模式 ： 其实和策略模式比较类似,但是策略模式的关键在于制定一个策略,其中变化的部分由子类实现
	 * 模板方法的关键在于制定一个特定的规则,子类按照这个规则去做自己的事情即可,一般用一个抽象类做处理,主要利用了java的继承特性,交给子类去处理各自逻辑
	 * 其实大体上差不多
	 * 
	 * 比较经典的一句话：
	 * Template methods use inheritance to vary part of an algorithm. Strategies use delegation to vary the entire algorithm
	 * (模板方法使用继承来改变一个算法的一部分。策略使用委派来改变整个算法)
	 */
	
	public static void main(String[] args) {
		AbstractCaculate ac = new AddCaculate();
		System.out.println(ac.dealNumber("1+2", "\\+"));
		ac = new PlusCaculate();
		System.out.println(ac.dealNumber("2-1", "-"));
	}
}

