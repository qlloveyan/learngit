/**
 * Project Name:designer
 * File Name:TestMain.java
 * Package Name:strategy_model
 * Date:2016年10月24日下午7:46:44
 *
*/

package strategy_model;

import strategy_model.parent.AbstractDuck;
import strategy_model.parent.IFlyAble;
import strategy_model.parent.IQuackAble;
import strategy_model.parent.impl.MachineFly;
import strategy_model.parent.impl.MachineQuack;
import strategy_model.parent.impl.MouthQuack;
import strategy_model.parent.impl.WingFly;

/**
 * ClassName:TestMain <br/>
 * Function: 策略模式. <br/>
 * Date:     2016年10月24日 下午7:46:44 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 策略模式：可变部分提出交由外部处理,不变部分公用
	 */
	public static void main(String[] args) {
		//本例中机器鸭和真实鸭的表现形式除了在游泳上表现一样,其它叫唤和飞行都不一样,将变化的部分单独实例
		IFlyAble wingFly = new WingFly();
		IFlyAble machineFly = new MachineFly();
		IQuackAble mouthQuack = new MouthQuack();
		IQuackAble machineQuack = new MachineQuack();
		
		AbstractDuck duck1 = new AbstractDuck(wingFly, mouthQuack);
		AbstractDuck duck2 = new AbstractDuck(machineFly, machineQuack);
		
		duck1.action();
		duck2.action();
		
		//如果出现其它组合的鸭子,比如 用机器翅膀飞行,用鸭鸣器叫,只要将变化的做参数传递进入即可
		AbstractDuck duck3 = new AbstractDuck(machineFly, mouthQuack);
		duck3.action();
		
		//同理,如果出现了其它的飞行以及叫唤方式,添加对应的实现,传入构造方法即可
		/**
		 * 缺点：不能动态的修改鸭子的所有表现行为,需要知道所有的表现形式
		 */
		
	}
}

