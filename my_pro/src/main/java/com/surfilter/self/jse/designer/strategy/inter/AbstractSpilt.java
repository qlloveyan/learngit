/**
 * Project Name:my_pro
 * File Name:AbstractSpilt.java
 * Package Name:com.surfilter.self.jse.designer.strategy.inter
 * Date:2016年4月7日下午4:57:14
 *
*/

package com.surfilter.self.jse.designer.strategy.inter;
/**
 * ClassName:AbstractSpilt <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午4:57:14 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class AbstractSpilt {
	
	public Object[] split(String exp,String splitStr) throws ClassCastException{
		Object[] result = null;
		String[] temp = exp.split(splitStr);
		result =  new Object[temp.length];
		System.arraycopy(temp, 0, result , 0, temp.length);
		return result;
	}
}

