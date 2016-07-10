/**
 * Project Name:my_pro
 * File Name:MyAnnoation.java
 * Package Name:com.surfilter.self.jse.base.anno
 * Date:2016年7月4日下午9:32:52
 *
*/

package com.surfilter.self.jse.base.anno;
/**
 * ClassName:MyAnnoation <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月4日 下午9:32:52 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnoation {

}

