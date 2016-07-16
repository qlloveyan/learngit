/**
 * Project Name:smcs
 * File Name:ExcelModel.java
 * Package Name:com.surfilter.framework.excel
 * Date:2013-10-26下午4:33:42
 *
*/

package com.surfilter.framework.filehandle.excel;

import com.surfilter.framework.model.BaseEntity;

/**
 * excel 模型
 * ClassName:ExcelModel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-10-26 下午4:33:42 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExcelModel extends BaseEntity{
	
	/**
	 * batchsize:TODO(每次批量导出数量).
	 * @since JDK 1.6
	 */
	private String batchsize;
	
	/**
	 * batchnum:TODO(批量导出批次号).
	 * @since JDK 1.6
	 */
	private String batchnum;
	
	/**
	 * recordsum:TODO(导出记录总数).
	 * @since JDK 1.6
	 */
	private String recordsum;
	/**
	 * batchEnd:TODO(批量导出结束标记).
	 * @since JDK 1.6
	 */
	private String batchEnd = "false";
	/**
	 * dataKind:TODO(数据种类).
	 * 比如一个页面有两个导出，不同的数据集 可以用这个字段判断
	 * @since JDK 1.6
	 */
	private String dataKind;
	
	/**
	 * exportType:TODO(导出方式，1为excel，2为csv).
	 * @since JDK 1.6
	 */
	private int exportType=1;
	
	
	/**
	 * isback:TODO(下载是否在后台处理).
	 * @since JDK 1.6
	 */
	private boolean isback = false;
	/**
	 * 标示是否导出
	 */
	private String isExport;
	
	/**
	 * 选中导出元素的id集合
	 */
	private String[] ids;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 对象属性集合
	 */
	private String[] properties;
	/**
	 * 对象属性名称集合
	 */
	private String[] titles;
	public String[] getProperties() {
		return properties;
	}
	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	public String[] getTitles() {
		return titles;
	}
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * ids.
	 *
	 * @return  the ids
	 * @since   JDK 1.6
	 */
	public String[] getIds() {
		if(null == getIsExport() || getIsExport().equals("false")){
			ids = null;
		}
		return ids;
	}
	/**
	 * ids.
	 *
	 * @param   ids    the ids to set
	 * @since   JDK 1.6
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * isExport.
	 *
	 * @return  the isExport
	 * @since   JDK 1.6
	 */
	public String getIsExport() {
		return isExport;
	}
	/**
	 * isExport.
	 *
	 * @param   isExport    the isExport to set
	 * @since   JDK 1.6
	 */
	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
	/**
	 * isback.
	 *
	 * @return  the isback
	 * @since   JDK 1.6
	 */
	public boolean isIsback() {
		return isback;
	}
	/**
	 * isback.
	 *
	 * @param   isback    the isback to set
	 * @since   JDK 1.6
	 */
	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	/**
	 * dataKind.
	 *
	 * @return  the dataKind
	 * @since   JDK 1.6
	 */
	public String getDataKind() {
		return dataKind;
	}
	/**
	 * dataKind.
	 *
	 * @param   dataKind    the dataKind to set
	 * @since   JDK 1.6
	 */
	public void setDataKind(String dataKind) {
		this.dataKind = dataKind;
	}
	/**
	 * exportType.
	 *
	 * @return  the exportType
	 * @since   JDK 1.6
	 */
	public int getExportType() {
		return exportType;
	}
	/**
	 * exportType.
	 *
	 * @param   exportType    the exportType to set
	 * @since   JDK 1.6
	 */
	public void setExportType(int exportType) {
		this.exportType = exportType;
	}
	/**
	 * batchsize.
	 *
	 * @return  the batchsize
	 * @since   JDK 1.6
	 */
	public String getBatchsize() {
		return batchsize;
	}
	/**
	 * batchsize.
	 *
	 * @param   batchsize    the batchsize to set
	 * @since   JDK 1.6
	 */
	public void setBatchsize(String batchsize) {
		this.batchsize = batchsize;
	}
	/**
	 * batchnum.
	 *
	 * @return  the batchnum
	 * @since   JDK 1.6
	 */
	public String getBatchnum() {
		return batchnum;
	}
	/**
	 * batchnum.
	 *
	 * @param   batchnum    the batchnum to set
	 * @since   JDK 1.6
	 */
	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}
	/**
	 * recordsum.
	 *
	 * @return  the recordsum
	 * @since   JDK 1.6
	 */
	public String getRecordsum() {
		return recordsum;
	}
	/**
	 * recordsum.
	 *
	 * @param   recordsum    the recordsum to set
	 * @since   JDK 1.6
	 */
	public void setRecordsum(String recordsum) {
		this.recordsum = recordsum;
	}
	/**
	 * batchEnd.
	 *
	 * @return  the batchEnd
	 * @since   JDK 1.6
	 */
	public String getBatchEnd() {
		return batchEnd;
	}
	/**
	 * batchEnd.
	 *
	 * @param   batchEnd    the batchEnd to set
	 * @since   JDK 1.6
	 */
	public void setBatchEnd(String batchEnd) {
		this.batchEnd = batchEnd;
	}
	
	
}

