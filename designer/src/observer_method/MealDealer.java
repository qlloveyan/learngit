/**
 * Project Name:designer
 * File Name:Test.java
 * Package Name:observer_method
 * Date:2016年10月24日下午8:25:18
 *
*/

package observer_method;

import java.util.Observable;

/**
 * ClassName:Test <br/>
 * Function: 肉类经销商对象,提供对所有在此购买肉类的大型超市的肉类价格消息推送服务. <br/>
 * Date:     2016年10月24日 下午8:25:18 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MealDealer extends Observable {

	private double priceOfMeal;

	public double getPriceOfMeal() {
		return priceOfMeal;
	}

	public void setPriceOfMeal(double priceOfMeal) {
		this.priceOfMeal = priceOfMeal;
	}
	
	public void notifyAlls(){
		setChanged();
		notifyObservers();
	}
}

