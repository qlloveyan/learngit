/**
 * Project Name:smcs<br>
 * File Name:Guanggao.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:07:55<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;

import java.util.Date;

/**
 * ClassName:Guanggao.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:55<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Guanggao implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *广告的唯一编号
	 */
	private String id;
	/**
	 *广告标题
	 */
	private String title;
	/**
	 *广告作者
	 */
	private String zuozhe;
	/**
	 *广告内容
	 */
	private String content;
	/**
	 *广告发布时间
	 */
	private Date fabuTime;
	/**
	 *查询起始时间
	 */
	private Date fabuTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date fabuTimeAfter;
	


	/**	 
	 *设置 :广告的唯一编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :广告的唯一编号
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :广告标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**	 
	 *获取 :广告标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**	 
	 *设置 :广告作者
	 */
	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	/**	 
	 *获取 :广告作者
	 */
	public String getZuozhe() {
		return this.zuozhe;
	}

	/**	 
	 *设置 :广告内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**	 
	 *获取 :广告内容
	 */
	public String getContent() {
		return this.content;
	}

	/**	 
	 *设置 :广告发布时间
	 */
	public void setFabuTime(Date fabuTime) {
		this.fabuTime = fabuTime;
	}

	/**	 
	 *获取 :广告发布时间
	 */
	public Date getFabuTime() {
		return this.fabuTime;
	}

	/**	 
	 *获取 :fabuTimeBefore
	 */
    public Date getFabuTimeBefore() {
        return this.fabuTimeBefore;
    }

	/**	 
	 *设置 :fabuTimeBefore
	 */
    public void setFabuTimeBefore(Date fabuTimeBefore) {
        this.fabuTimeBefore = fabuTimeBefore;
    }


	/**	 
	 *获取 :fabuTimeAfter
	 */
    public Date getFabuTimeAfter() {
        return this.fabuTimeAfter;
    }

	/**	 
	 *设置 :fabuTimeAfter
	 */
    public void setFabuTimeAfter(Date fabuTimeAfter) {
        this.fabuTimeAfter = fabuTimeAfter;
    }

}
