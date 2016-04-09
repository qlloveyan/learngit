/**
 * Project Name:my_pro
 * File Name:ChangeSource.java
 * Package Name:com.surfilter.self.jse.designer.observer
 * Date:2016年4月7日下午7:08:53
 *
*/

package com.surfilter.self.jse.designer.observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName:ChangeSource <br/>
 * Function: 最初修改的对象. <br/>
 * Date:     2016年4月7日 下午7:08:53 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ChangeSource {
	//持有被通知修改的对象
	private Set<ObserverObj> observers = new HashSet<ObserverObj>();
	
	public void addObserver(ObserverObj obj){
		observers.add(obj);
	}
	
	public void removeObserver(ObserverObj obj){
		observers.remove(obj);
	}
	
	public void notifyObserver(){
		Iterator<ObserverObj> temp = observers.iterator();
		while(temp.hasNext()){
			temp.next().update();
		}
	}
	
}

