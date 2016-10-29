/**
 * Project Name:designer
 * File Name:TestMain.java
 * Package Name:observer_method
 * Date:2016年10月24日下午8:41:41
 *
*/

package observer_method;

import java.util.Observable;
import java.util.Observer;

/**
 * ClassName:TestMain <br/>
 * Function: 测试观察者模式. <br/>
 * Date:     2016年10月24日 下午8:41:41 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		/**
		 * 首先创建消息接收者
		 * 沃尔玛和中百都在同一个地方获取肉类销售
		 */
		Observer walmart = new SuperMarket( "沃尔玛" );
		Observer zhongbai = new SuperMarket( "中百超市" );
		
		//创建一个肉类供应商
		Observable mealDealer = new MealDealer();
		
		//首先只有沃尔玛在此购买肉类
		mealDealer.addObserver( walmart );
		/**
		 * 目标：当有价格变动,通知沃尔玛提醒今日价格,但是目前有一个需要注意的,此时Observable是一个对象,不是接口,现在无法调用MealDealer的方法取动态的设置变化的参数
			mealDealer.setPriceOfMeal(21.00); //此时无法采用父类引用子类对象调用子类方法
		 */
		MealDealer mealDealer2 = new MealDealer();
		mealDealer2.addObserver( walmart );
		mealDealer2.setPriceOfMeal( 21.00 );
		mealDealer2.notifyAlls();//通知所有超市肉价发生了改变
		System.out.println("=========================================");
		
		//现在中百也在此订购肉类,需要加入消息响应队列
		mealDealer2.addObserver(zhongbai);
		mealDealer2.setPriceOfMeal( 20.00 );
		mealDealer2.notifyAlls();//通知所有超市肉价发生了改变
		System.out.println("=========================================");
		
		//沃尔玛觉得你比较贵,退出消息响应队列
		mealDealer2.deleteObserver(walmart);
		mealDealer2.notifyAlls();
		System.out.println("=========================================");
		
	}
}

