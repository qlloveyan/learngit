/**
 * Project Name:my_pro
 * File Name:AbstractCaculate.java
 * Package Name:com.surfilter.self.jse.designer.template
 * Date:2016年4月7日下午5:59:04
 *
*/

package com.surfilter.self.jse.designer.template;
/**
 * ClassName:AbstractCaculate <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午5:59:04 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class AbstractCaculate {
	
	public Integer dealNumber(String exp,String spiltStr){
		Object[] result = splitExp(exp,spiltStr);
		return caculate(Integer.parseInt(result[0].toString()),Integer.parseInt(result[1].toString()));
	}
	
	abstract Integer caculate(Integer param1,Integer param2);
	
	public Object[] splitExp(String exp,String spiltStr){
		return exp.split(spiltStr);
	}
	
}

