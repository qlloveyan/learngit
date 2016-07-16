/**
 * Project Name:lichen
 * File Name:ExeclImport.java
 * Package Name:com.surfilter.framework.filehandle.excel
 * Date:2014-1-22下午3:42:45
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.util.List;

/**
 * 解析excel数据获取的对象
 * ClassName:ExeclImport <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 下午3:42:45 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExeclImport {
	/**
	 * listobj:TODO(解析excel获取的对象集合).
	 * @since JDK 1.6
	 */
	private List<Object> listobj;
	/**
	 * listError:TODO(验证excel获取的错误信息集合).
	 * @since JDK 1.6
	 */
	private List<ExeclImportError> listError;
	/**
	 * listobj.
	 *
	 * @return  the listobj
	 * @since   JDK 1.6
	 */
	public List<Object> getListobj() {
		return listobj;
	}
	/**
	 * listobj.
	 *
	 * @param   listobj    the listobj to set
	 * @since   JDK 1.6
	 */
	public void setListobj(List<Object> listobj) {
		this.listobj = listobj;
	}
	/**
	 * listError.
	 *
	 * @return  the listError
	 * @since   JDK 1.6
	 */
	public List<ExeclImportError> getListError() {
		return listError;
	}
	/**
	 * listError.
	 *
	 * @param   listError    the listError to set
	 * @since   JDK 1.6
	 */
	public void setListError(List<ExeclImportError> listError) {
		this.listError = listError;
	}
	
	
}

