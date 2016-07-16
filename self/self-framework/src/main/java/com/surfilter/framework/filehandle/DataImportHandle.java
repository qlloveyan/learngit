/**
 * Project Name:lichen
 * File Name:DateImportHandle.java
 * Package Name:com.surfilter.framework.filehandle
 * Date:2014-1-21下午7:21:48
 *
*/

package com.surfilter.framework.filehandle;

import javax.servlet.http.HttpServletRequest;

import com.surfilter.framework.web.bind.ExtJsObject;

/**
 * 数据导入接口
 * ClassName:DateImportHandle <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-21 下午7:21:48 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface DataImportHandle  {
	ExtJsObject importData(HttpServletRequest request);
}

