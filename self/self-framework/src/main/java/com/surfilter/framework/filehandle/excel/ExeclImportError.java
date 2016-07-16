/**
 * Project Name:lichen
 * File Name:ExeclImportResult.java
 * Package Name:com.surfilter.framework.filehandle.excel
 * Date:2014-1-22上午10:47:27
 *
*/

package com.surfilter.framework.filehandle.excel;
/**
 * 数据导入返错误
 * ClassName:ExeclImportError <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 上午10:47:27 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExeclImportError {
	/**
	 * column:TODO(列).
	 * @since JDK 1.6
	 */
	private int column;
	/**
	 * row:TODO(行).
	 * @since JDK 1.6
	 */
	private int row;
	/**
	 * msg:TODO(错误信息).
	 * @since JDK 1.6
	 */
	private String error;
	/**
	 * column.
	 *
	 * @return  the column
	 * @since   JDK 1.6
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * column.
	 *
	 * @param   column    the column to set
	 * @since   JDK 1.6
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * row.
	 *
	 * @return  the row
	 * @since   JDK 1.6
	 */
	public int getRow() {
		return row;
	}
	/**
	 * row.
	 *
	 * @param   row    the row to set
	 * @since   JDK 1.6
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * error.
	 *
	 * @return  the error
	 * @since   JDK 1.6
	 */
	public String getError() {
		return error;
	}
	/**
	 * error.
	 *
	 * @param   error    the error to set
	 * @since   JDK 1.6
	 */
	public void setError(String error) {
		this.error = error;
	}
	
}

