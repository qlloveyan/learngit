/**
 * Project Name:my_pro
 * File Name:ObserverObj.java
 * Package Name:com.surfilter.self.jse.designer.observer
 * Date:2016年4月7日下午7:09:04
 *
*/

package com.surfilter.self.jse.designer.observer;
/**
 * ClassName:ObserverObj <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午7:09:04 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ObserverObj {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObserverObj(String name) {
		super();
		this.name = name;
	}

	public void update() {
		System.out.println(this.name+" update!");
	}
	
	
}

