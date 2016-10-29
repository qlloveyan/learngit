/**
 * Project Name:designer
 * File Name:MonthQuack.java
 * Package Name:strategy_model.parent.impl
 * Date:2016年10月24日下午7:45:21
 *
*/

package strategy_model.parent.impl;

import strategy_model.parent.IQuackAble;

/**
 * ClassName:MonthQuack <br/>
 * Function: 采用机器嘴叫唤. <br/>
 * Date:     2016年10月24日 下午7:45:21 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MachineQuack implements IQuackAble {

	@Override
	public void quack() {
		System.out.println("鸭鸣器  呱呱呱!");
	}

}

