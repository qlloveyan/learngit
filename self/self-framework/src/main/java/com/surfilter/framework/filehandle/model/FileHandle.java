/**
 * Project Name:smcs<br>
 * File Name:FileHandle.java<br>
 * Package Name:com.surfilter.framework.filehandle.model<br>
 * Date:2013年12月26日  下午02:16:05<br>
 *
*/
package com.surfilter.framework.filehandle.model;
import java.io.Serializable;
import java.util.Date;

import com.surfilter.framework.filehandle.excel.ExcelModel;

/**
 * ClassName:FileHandle.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月26日  下午02:16:05<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileHandle extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;
	/**
	 *资源编码
	 */
	private String resourceCode;
	/**
	 *资源相对路径
	 */
	private String path;
	/**
	 *文件名称
	 */
	private String fileName;
	/**
	 *上传时间
	 */
	private Date uploadTime;
	/**
	 *上传人名称
	 */
	private String uploadUsername;
	/**
	 *上传人id
	 */
	private String uploadUserid;
	/**
	 *文件转存到FTP服务器的状态 0,转存成功 1，转存失败
	 */
	private String fileMoveState;
	/**
	 *FTP地址
	 */
	private String ftpAddress;
	/**
	 *资源ID
	 */
	private String resourceId;
	/**
	 *文件扩展名
	 */
	private String suffixName;
	/**
	 *文件大小（单位k）
	 */
	private Long fileSize;
	/**
	 *查询起始时间
	 */
	private Date uploadTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date uploadTimeAfter;
	


	/**	 
	 *设置 :主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :资源编码
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**	 
	 *获取 :资源编码
	 */
	public String getResourceCode() {
		return this.resourceCode;
	}

	/**	 
	 *设置 :资源相对路径
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**	 
	 *获取 :资源相对路径
	 */
	public String getPath() {
		return this.path;
	}

	/**	 
	 *设置 :文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**	 
	 *获取 :文件名称
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**	 
	 *设置 :上传时间
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**	 
	 *获取 :上传时间
	 */
	public Date getUploadTime() {
		return this.uploadTime;
	}

	/**	 
	 *设置 :上传人名称
	 */
	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}

	/**	 
	 *获取 :上传人名称
	 */
	public String getUploadUsername() {
		return this.uploadUsername;
	}

	/**	 
	 *设置 :上传人id
	 */
	public void setUploadUserid(String uploadUserid) {
		this.uploadUserid = uploadUserid;
	}

	/**	 
	 *获取 :上传人id
	 */
	public String getUploadUserid() {
		return this.uploadUserid;
	}

	/**	 
	 *设置 :文件转存到FTP服务器的状态 0,转存成功 1，转存失败
	 */
	public void setFileMoveState(String fileMoveState) {
		this.fileMoveState = fileMoveState;
	}

	/**	 
	 *获取 :文件转存到FTP服务器的状态 0,转存成功 1，转存失败
	 */
	public String getFileMoveState() {
		return this.fileMoveState;
	}

	/**	 
	 *设置 :FTP地址
	 */
	public void setFtpAddress(String ftpAddress) {
		this.ftpAddress = ftpAddress;
	}

	/**	 
	 *获取 :FTP地址
	 */
	public String getFtpAddress() {
		return this.ftpAddress;
	}

	/**	 
	 *设置 :资源ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**	 
	 *获取 :资源ID
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**	 
	 *设置 :文件扩展名
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/**	 
	 *获取 :文件扩展名
	 */
	public String getSuffixName() {
		return this.suffixName;
	}

	/**	 
	 *设置 :文件大小（单位k）
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**	 
	 *获取 :文件大小（单位k）
	 */
	public Long getFileSize() {
		return this.fileSize;
	}

	/**	 
	 *获取 :uploadTimeBefore
	 */
    public Date getUploadTimeBefore() {
        return this.uploadTimeBefore;
    }

	/**	 
	 *设置 :uploadTimeBefore
	 */
    public void setUploadTimeBefore(Date uploadTimeBefore) {
        this.uploadTimeBefore = uploadTimeBefore;
    }


	/**	 
	 *获取 :uploadTimeAfter
	 */
    public Date getUploadTimeAfter() {
        return this.uploadTimeAfter;
    }

	/**	 
	 *设置 :uploadTimeAfter
	 */
    public void setUploadTimeAfter(Date uploadTimeAfter) {
        this.uploadTimeAfter = uploadTimeAfter;
    }

}
