/**
 * Project Name:smcs<br>
 * File Name:Msg.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年01月25日  下午01:11:23<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import com.surfilter.framework.filehandle.excel.ExcelModel;

/**
 * ClassName:Msg.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月25日  下午01:11:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Msg extends ExcelModel implements Serializable,Comparable<Msg>{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *消息类型，关联字典表
	 */
	private String msgType;
	/**
	 *业务类别 关联字典表
	 */
	private String businessType;
	/**
	 *消息标题
	 */
	private String msgTitle;
	/**
	 *消息内容
	 */
	private String msgContent;
	/**
	 *消息地址，如果是下载信息，体统下载地址
	 */
	private String msgUrl;
	/**
	 *消息级别
	 */
	private Long msgLevel;
	/**
	 *消息创建人
	 */
	private String createUser;
	/**
	 *消息创建时间
	 */
	private Date createTime;
	/**
	 *查询起始时间
	 */
	private Date createTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date createTimeAfter;
	


	/**	 
	 *设置 :主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :消息类型，关联字典表
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**	 
	 *获取 :消息类型，关联字典表
	 */
	public String getMsgType() {
		return this.msgType;
	}

	/**	 
	 *设置 :业务类别 关联字典表
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**	 
	 *获取 :业务类别 关联字典表
	 */
	public String getBusinessType() {
		return this.businessType;
	}

	/**	 
	 *设置 :消息标题
	 */
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	/**	 
	 *获取 :消息标题
	 */
	public String getMsgTitle() {
		return this.msgTitle;
	}

	/**	 
	 *设置 :消息内容
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	/**	 
	 *获取 :消息内容
	 */
	public String getMsgContent() {
		return this.msgContent;
	}

	/**	 
	 *设置 :消息地址，如果是下载信息，体统下载地址
	 */
	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	/**	 
	 *获取 :消息地址，如果是下载信息，体统下载地址
	 */
	public String getMsgUrl() {
		return this.msgUrl;
	}

	/**	 
	 *设置 :消息级别
	 */
	public void setMsgLevel(Long msgLevel) {
		this.msgLevel = msgLevel;
	}

	/**	 
	 *获取 :消息级别
	 */
	public Long getMsgLevel() {
		return this.msgLevel;
	}

	/**	 
	 *设置 :消息创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**	 
	 *获取 :消息创建人
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**	 
	 *设置 :消息创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**	 
	 *获取 :消息创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**	 
	 *获取 :createTimeBefore
	 */
    public Date getCreateTimeBefore() {
        return this.createTimeBefore;
    }

	/**	 
	 *设置 :createTimeBefore
	 */
    public void setCreateTimeBefore(Date createTimeBefore) {
        this.createTimeBefore = createTimeBefore;
    }


	/**	 
	 *获取 :createTimeAfter
	 */
    public Date getCreateTimeAfter() {
        return this.createTimeAfter;
    }

	/**	 
	 *设置 :createTimeAfter
	 */
    public void setCreateTimeAfter(Date createTimeAfter) {
        this.createTimeAfter = createTimeAfter;
    }

	@Override
	public int compareTo(Msg o1) {
		
		// TODO Auto-generated method stub
		return this.getId().compareTo(o1.getId());
	}

}
