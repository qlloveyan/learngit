/**
 * Project Name:smcs
 * File Name:FileModel.java
 * Package Name:com.surfilter.framework.filehandle
 * Date:2013-11-11上午11:08:44
 *
*/

package com.surfilter.framework.filehandle;
/**
 * 资源文件上传实体
 * ClassName:FileModel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-11-11 上午11:08:44 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileModel {
	/**
	 * resourceCode:TODO(资源编码).
	 * @since JDK 1.6
	 */
	private String resourceCode;
	
	
	/**
	 * path:TODO(资源相对路径).
	 * @since JDK 1.6
	 */
	private String path;
	
	/**
	 * maxSize:TODO(资源文件上传最大值).
	 * @since JDK 1.6
	 */
	private String maxSize;
	
	/**
	 * batchCount:TODO(允许批量上传个数).
	 * @since JDK 1.6
	 */
	private String batchCount;
	
	/**
	 * fileTypes:TODO(允许上传文件类型集合).
	 * @since JDK 1.6
	 */
	private String[] fileTypes;
	
	/**
	 * fileName:TODO(上传文件名称).
	 * @since JDK 1.6
	 */
	private String fileName;
	
	/**
	 * chunk:TODO(是否分块上传).
	 * @since JDK 1.6
	 */
	private boolean chunk;
	
	

	/**
	 * Creates a new instance of FileModel.
	 *
	 */
	
	public FileModel() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * resourceCode.
	 *
	 * @return  the resourceCode
	 * @since   JDK 1.6
	 */
	public String getResourceCode() {
		return resourceCode;
	}

	/**
	 * resourceCode.
	 *
	 * @param   resourceCode    the resourceCode to set
	 * @since   JDK 1.6
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	
	
	/**
	 * path.
	 *
	 * @return  the path
	 * @since   JDK 1.6
	 */
	public String getPath() {
		return path;
	}

	/**
	 * path.
	 *
	 * @param   path    the path to set
	 * @since   JDK 1.6
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * maxSize.
	 *
	 * @return  the maxSize
	 * @since   JDK 1.6
	 */
	public String getMaxSize() {
		return maxSize;
	}

	/**
	 * maxSize.
	 *
	 * @param   maxSize    the maxSize to set
	 * @since   JDK 1.6
	 */
	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * batchCount.
	 *
	 * @return  the batchCount
	 * @since   JDK 1.6
	 */
	public String getBatchCount() {
		return batchCount;
	}

	/**
	 * batchCount.
	 *
	 * @param   batchCount    the batchCount to set
	 * @since   JDK 1.6
	 */
	public void setBatchCount(String batchCount) {
		this.batchCount = batchCount;
	}

	/**
	 * fileTypes.
	 *
	 * @return  the fileTypes
	 * @since   JDK 1.6
	 */
	public String[] getFileTypes() {
		return fileTypes;
	}

	/**
	 * fileTypes.
	 *
	 * @param   fileTypes    the fileTypes to set
	 * @since   JDK 1.6
	 */
	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

	/**
	 * fileName.
	 *
	 * @return  the fileName
	 * @since   JDK 1.6
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * fileName.
	 *
	 * @param   fileName    the fileName to set
	 * @since   JDK 1.6
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * chunk.
	 *
	 * @return  the chunk
	 * @since   JDK 1.6
	 */
	public boolean isChunk() {
		return chunk;
	}

	/**
	 * chunk.
	 *
	 * @param   chunk    the chunk to set
	 * @since   JDK 1.6
	 */
	public void setChunk(boolean chunk) {
		this.chunk = chunk;
	}
	
	
}

