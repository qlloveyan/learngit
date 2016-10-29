/**
 * Project Name:designer
 * File Name:WingFly.java
 * Package Name:strategy_model.son
 * Date:2016年10月24日下午7:44:05
 *
*/

package strategy_model.parent.impl;

import strategy_model.parent.IFlyAble;

/**
 * ClassName:WingFly <br/>
 * Function: 采用翅膀飞行. <br/>
 * Date:     2016年10月24日 下午7:44:05 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WingFly implements IFlyAble {

	@Override
	public void fly() {
		System.out.println("我用翅膀飞行!");
	}

}

