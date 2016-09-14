/**
 * Project Name:my_pro
 * File Name:IOperationEnum.java
 * Package Name:com.surfilter.self.jse.enumerate.inter
 * Date:2016年8月16日下午8:44:49
 *
*/

package com.surfilter.self.jse.enumerate.inter;
/**
 * ClassName:IOperationEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年8月16日 下午8:44:49 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public enum IOperationEnum implements IOperation {
	
	PLUS{
		public int deal(int x , int y){
			return x + y;
		}
	},
	JIAN{
		public int deal(int x , int y){
			return x - y;
		}
	},
	CHU{
		public int deal(int x , int y){
			return x % y;
		}
	};
}

