/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.designer.observer
 * Date:2016年4月7日下午7:05:32
 *
*/

package com.surfilter.self.jse.designer.observer;
/**
 * ClassName:TestMain <br/>
 * Function: 观察者模式. <br/>
 * Date:     2016年4月7日 下午7:05:32 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 观察者模式：实现的要点就是被修改类持有被通知的对象
	 */
	public static void main(String[] args) {
		//新建消息改变源头
		ChangeSource cs = new ChangeSource();
		
		//持有需要通知修改的对象
		ObserverObj obj1 = new ObserverObj("1");
		cs.addObserver(obj1);
		ObserverObj obj2 = new ObserverObj("2");
		cs.addObserver(obj2);
		ObserverObj obj3 = new ObserverObj("3");
		cs.addObserver(obj3);
		
		cs.notifyObserver();//更新
		cs.removeObserver(obj2);
		cs.notifyObserver();
	}
}

