/**
 * Project Name:my_pro
 * File Name:AddCaculate.java
 * Package Name:com.surfilter.self.jse.designer.strategy
 * Date:2016年4月7日下午5:10:15
 *
*/

package com.surfilter.self.jse.designer.strategy;

import com.surfilter.self.jse.designer.strategy.inter.AbstractSpilt;
import com.surfilter.self.jse.designer.strategy.inter.ICaculate;

/**
 * ClassName:AddCaculate <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午5:10:15 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AddCaculate extends AbstractSpilt implements ICaculate<Integer> {

	private final String SPILT_STR = "\\+";
	
	@Override
	public Integer caculate(String exp) {
		Object[] result = split(exp, SPILT_STR);
		return Integer.parseInt(result[0].toString())+Integer.parseInt(result[1].toString());
	}

}

