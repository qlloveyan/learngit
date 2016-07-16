/**
 * Project Name:smcs
 * File Name:FileHandle.java
 * Package Name:com.surfilter.framework.filehandle
 * Date:2013-11-11下午3:43:07
 *
*/

package com.surfilter.framework.filehandle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surfilter.framework.filehandle.model.DataTemplate;
import com.surfilter.framework.filehandle.model.FileHandle;

/**
 * 文件处理接口
 * ClassName:FileHandle <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-11-11 下午3:43:07 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IFileHandle {
	
	/**
	 * upload:(上传文件). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @param userid
	 * @param userName
	 * @return
	 * @since JDK 1.6
	 */
	String upload(HttpServletRequest request, HttpServletResponse response,String userid,String userName);
	 
	 /**
	 * deletFile:(删除文件). <br/>
	 *
	 * @author wangguohong
	 * @param file
	 * @return
	 * @since JDK 1.6
	 */
	boolean deletFile(FileHandle file);
	 
	/**
	 * download:(下载文件). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @since JDK 1.6
	 */
	boolean download(HttpServletRequest request, HttpServletResponse response,FileHandle file);
	
	/**
	 * 
	 * downTemplate:(下载数据模板). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @param template
	 * @return
	 * @since JDK 1.6
	 */
	boolean downTemplate(HttpServletRequest request, HttpServletResponse response,DataTemplate template);
	
	/**
	 * getTemplateByResourceCode:(根据资源模板获取导入数据模板列表). <br/>
	 *
	 * @author wangguohong
	 * @param resourceCode
	 * @return
	 * @since JDK 1.6
	 */
	List<DataTemplate> getTemplateByResourceCode(HttpServletRequest request,String resourceCode);
}

