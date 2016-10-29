/**
 * Project Name:designer
 * File Name:AbstractDuck.java
 * Package Name:strategy_model.parent
 * Date:2016年10月24日下午7:38:07
 *
*/

package strategy_model.parent;
/**
 * ClassName:AbstractDuck <br/>
 * Function: 父类不变部分封装. <br/>
 * Date:     2016年10月24日 下午7:38:07 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AbstractDuck {

	//可变部分
	private IFlyAble flyAble;
	private IQuackAble quackAble;
	
	public AbstractDuck(IFlyAble flyAble, IQuackAble quackAble) {
		super();
		this.flyAble = flyAble;
		this.quackAble = quackAble;
	}
	
	public IFlyAble getFlyAble() {
		return flyAble;
	}
	public void setFlyAble(IFlyAble flyAble) {
		this.flyAble = flyAble;
	}

	public IQuackAble getQuackAble() {
		return quackAble;
	}
	public void setQuackAble(IQuackAble quackAble) {
		this.quackAble = quackAble;
	}

	//不可变部分
	public void swim(){
		System.out.println("我能游泳!");
	}
	
	//行为
	public void action(){
		swim();
		if( flyAble != null ){
			flyAble.fly();
		}
		
		if( quackAble != null ){
			quackAble.quack();
		}
	}
}

