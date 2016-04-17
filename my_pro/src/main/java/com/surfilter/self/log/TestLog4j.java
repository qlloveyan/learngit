/**
 * Project Name:shutdown
 * File Name:TestLog4j.java
 * Package Name:com.surfilter.self.log
 * Date:2016年2月16日下午4:43:24
 *
*/

package com.surfilter.self.log;

import org.apache.log4j.Logger;

/**
 * ClassName:TestLog4j <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月16日 下午4:43:24 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestLog4j {

	private static Logger logger = Logger.getLogger(TestLog4j.class);
	
	public static void main(String[] args) {
		
		logger.debug("debug 消息!");
		logger.info("info 消息!");
		logger.error("error 消息!");
	}
}

