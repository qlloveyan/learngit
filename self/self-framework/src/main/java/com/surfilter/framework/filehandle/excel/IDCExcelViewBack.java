/**
 * Project Name:smcs
 * File Name:ExcelView.java
 * Package Name:com.smcs.core.excel
 * Date:2013-9-27下午1:47:58
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import com.surfilter.framework.FrameworkGlobal;

/**
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 
 * 	 
 */
public class IDCExcelViewBack<T> implements View{

	@Override
	public String getContentType() {
		
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO 重新render
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws Exception
	 * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void render(Map<String, ?> arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
		arg2.setContentType("text/html;charset=utf-8");
		arg2.setCharacterEncoding("utf-8");
		arg2.getWriter().write(arg0.get(FrameworkGlobal.EXCEL_NAME).toString());
		arg2.getWriter().flush();
		arg2.getWriter().close();
	}

	

}

