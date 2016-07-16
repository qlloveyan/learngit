/**
 * Project Name:smcs
 * File Name:ExcelOperate.java
 * Package Name:com.surfilter.framework.excel
 * Date:2013-10-26下午4:00:27
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

/**
 * excel操作接口 要实现导入功能必须实现这个接口
 * ClassName:ExcelOperate <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-10-26 下午4:00:27 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ExcelOperate<T> {
	/**
	 * 
	 * exportExcel:导出. <br/>
	 *
	 * @author wangguohong
	 * @param entity
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView exportExcel(T entity,HttpServletRequest request);
	
}

