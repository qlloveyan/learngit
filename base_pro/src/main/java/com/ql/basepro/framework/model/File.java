/**
 * Project Name:smcs<br>
 * File Name:File.java<br>
 * Package Name:com.ql.basepro.model<br>
 * Date:2015年08月20日  下午03:15:31<br>
 *
*/
package com.ql.basepro.framework.model;
import java.io.Serializable;
import java.util.Date;


/**
 * ClassName:File.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年08月20日  下午03:15:31<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class File implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *文件id
	 */
	private String id;
	/**
	 *文件类型 1 audio 音频 2 video 3 image 4 其它
	 */
	private Long type;
	/**
	 *上传文件url
	 */
	private String url;
	/**
	 *文件名称
	 */
	private String filename;
	/**
	 *文件扩展名
	 */
	private String fileext;
	/**
	 *文件mime类型
	 */
	private String mimetype;
	/**
	 *文件大小
	 */
	private String filesize;
	/**
	 *
	 */
	private String filecontent;
	/**
	 *用户id
	 */
	private Long userid;
	/**
	 *上传时间
	 */
	private Date uploadtime;
	/**
	 *关联订单id
	 */
	private Long orderid;
	


	/**	 
	 *设置 :文件id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :文件id
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :文件类型 1 audio 音频 2 video 3 image 4 其它
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**	 
	 *获取 :文件类型 1 audio 音频 2 video 3 image 4 其它
	 */
	public Long getType() {
		return this.type;
	}

	/**	 
	 *设置 :上传文件url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**	 
	 *获取 :上传文件url
	 */
	public String getUrl() {
		return this.url;
	}

	/**	 
	 *设置 :文件名称
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**	 
	 *获取 :文件名称
	 */
	public String getFilename() {
		return this.filename;
	}

	/**	 
	 *设置 :文件扩展名
	 */
	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	/**	 
	 *获取 :文件扩展名
	 */
	public String getFileext() {
		return this.fileext;
	}

	/**	 
	 *设置 :文件mime类型
	 */
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	/**	 
	 *获取 :文件mime类型
	 */
	public String getMimetype() {
		return this.mimetype;
	}

	/**	 
	 *设置 :文件大小
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	/**	 
	 *获取 :文件大小
	 */
	public String getFilesize() {
		return this.filesize;
	}

	/**	 
	 *设置 :
	 */
	public void setFilecontent(String filecontent) {
		this.filecontent = filecontent;
	}

	/**	 
	 *获取 :
	 */
	public String getFilecontent() {
		return this.filecontent;
	}

	/**	 
	 *设置 :用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**	 
	 *获取 :用户id
	 */
	public Long getUserid() {
		return this.userid;
	}

	/**	 
	 *设置 :上传时间
	 */
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	/**	 
	 *获取 :上传时间
	 */
	public Date getUploadtime() {
		return this.uploadtime;
	}

	/**	 
	 *设置 :关联订单id
	 */
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	/**	 
	 *获取 :关联订单id
	 */
	public Long getOrderid() {
		return this.orderid;
	}

}
