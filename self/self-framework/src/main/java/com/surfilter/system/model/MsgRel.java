/**
 * Project Name:smcs<br>
 * File Name:MsgRel.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2014年01月25日  下午01:44:31<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;

import java.util.Date;

/**
 * ClassName:MsgRel.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月25日  下午01:44:31<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MsgRel extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *消息id
	 */
	private Long msgId;
	/**
	 *接收者
	 */
	private Long recevieUserId;
	/**
	 *发送者
	 */
	private Long sendUserId;
	/**
	 *消息状态0未读，1已读
	 */
	private Long state;
	/**
	 *消息操作时间
	 */
	private Date operateTime;
	/**
	 *查询起始时间
	 */
	private Date operateTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date operateTimeAfter;
	
	/**
	 * msg:TODO(消息实体).
	 * @since JDK 1.6
	 */
	public Msg msg;
	


	/**
	 * msg.
	 *
	 * @return  the msg
	 * @since   JDK 1.6
	 */
	public Msg getMsg() {
		return msg;
	}

	/**
	 * msg.
	 *
	 * @param   msg    the msg to set
	 * @since   JDK 1.6
	 */
	public void setMsg(Msg msg) {
		this.msg = msg;
	}

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
	 *设置 :消息id
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	/**	 
	 *获取 :消息id
	 */
	public Long getMsgId() {
		return this.msgId;
	}

	/**	 
	 *设置 :接收者
	 */
	public void setRecevieUserId(Long recevieUserId) {
		this.recevieUserId = recevieUserId;
	}

	/**	 
	 *获取 :接收者
	 */
	public Long getRecevieUserId() {
		return this.recevieUserId;
	}

	/**	 
	 *设置 :发送者
	 */
	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}

	/**	 
	 *获取 :发送者
	 */
	public Long getSendUserId() {
		return this.sendUserId;
	}

	/**	 
	 *设置 :消息状态0未读，1已读
	 */
	public void setState(Long state) {
		this.state = state;
	}

	/**	 
	 *获取 :消息状态0未读，1已读
	 */
	public Long getState() {
		return this.state;
	}

	/**	 
	 *设置 :消息操作时间
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**	 
	 *获取 :消息操作时间
	 */
	public Date getOperateTime() {
		return this.operateTime;
	}

	/**	 
	 *获取 :operateTimeBefore
	 */
    public Date getOperateTimeBefore() {
        return this.operateTimeBefore;
    }

	/**	 
	 *设置 :operateTimeBefore
	 */
    public void setOperateTimeBefore(Date operateTimeBefore) {
        this.operateTimeBefore = operateTimeBefore;
    }


	/**	 
	 *获取 :operateTimeAfter
	 */
    public Date getOperateTimeAfter() {
        return this.operateTimeAfter;
    }

	/**	 
	 *设置 :operateTimeAfter
	 */
    public void setOperateTimeAfter(Date operateTimeAfter) {
        this.operateTimeAfter = operateTimeAfter;
    }

}
