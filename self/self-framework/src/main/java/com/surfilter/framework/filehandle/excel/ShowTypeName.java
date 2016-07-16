/**
 * Project Name:smcs
 * File Name:ShowTypeName.java
 * Package Name:com.surfilter.framework.excel
 * Date:2013-11-15上午10:29:36
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动定义注解，需要导出excel的实体在属性上注解翻译字段
 * 比如：
 * <pre>
 * @ShowTypeName(showName="useTypeName")
 * private Long useType;
 * 表示 导出excel时useType 显示为useTypeName 
 * 作用在于翻译字典数据
 * </pre>
 * ClassName:ShowTypeName <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-11-15 上午10:29:36 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShowTypeName {
	/**
	 * showName:(需要在导出excel表格是显示的字段名称). <br/>
	 *
	 * @author wangguohong
	 * @return
	 * @since JDK 1.6
	 */
	String showName();
}

