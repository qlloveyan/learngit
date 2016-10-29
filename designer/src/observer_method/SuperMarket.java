/**
 * Project Name:designer
 * File Name:Walmart.java
 * Package Name:observer_method
 * Date:2016年10月24日下午8:38:54
 *
*/

package observer_method;

import java.util.Observable;
import java.util.Observer;

/**
 * ClassName : SuperMarket <br/>
 * Function: 超市类,销售从经销商发送的肉制品. <br/>
 * Date:     2016年10月24日 下午8:38:54 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SuperMarket implements Observer {

	private String name;
	
	public SuperMarket(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable paramObservable, Object paramObject) {
		MealDealer mealDealer = (MealDealer) paramObservable;
		System.out.println(name + "今日肉价：" +mealDealer.getPriceOfMeal());
	}

}

