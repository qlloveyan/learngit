/**
 * Project Name:smcs<br>
 * File Name:Review.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:07:35<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:Review.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Review implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *评论的唯一编号
	 */
	private Long id;
	/**
	 *发表评论人的用户的IP地址
	 */
	private String ip;
	/**
	 *评论发布的时间
	 */
	private Date sj;
	/**
	 *发表评论的人的用户名
	 */
	private String username;
	/**
	 *评论人的个人主页
	 */
	private String homepage;
	/**
	 *评论的主题图案
	 */
	private String face;
	/**
	 *评论的主题
	 */
	private String title;
	/**
	 *评论的内容
	 */
	private String content;
	/**
	 *查询起始时间
	 */
	private Date sjBefore;
	/**
	 *查询截止时间
	 */
	private Date sjAfter;
	


	/**	 
	 *设置 :评论的唯一编号
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :评论的唯一编号
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :发表评论人的用户的IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**	 
	 *获取 :发表评论人的用户的IP地址
	 */
	public String getIp() {
		return this.ip;
	}

	/**	 
	 *设置 :评论发布的时间
	 */
	public void setSj(Date sj) {
		this.sj = sj;
	}

	/**	 
	 *获取 :评论发布的时间
	 */
	public Date getSj() {
		return this.sj;
	}

	/**	 
	 *设置 :发表评论的人的用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**	 
	 *获取 :发表评论的人的用户名
	 */
	public String getUsername() {
		return this.username;
	}

	/**	 
	 *设置 :评论人的个人主页
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/**	 
	 *获取 :评论人的个人主页
	 */
	public String getHomepage() {
		return this.homepage;
	}

	/**	 
	 *设置 :评论的主题图案
	 */
	public void setFace(String face) {
		this.face = face;
	}

	/**	 
	 *获取 :评论的主题图案
	 */
	public String getFace() {
		return this.face;
	}

	/**	 
	 *设置 :评论的主题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**	 
	 *获取 :评论的主题
	 */
	public String getTitle() {
		return this.title;
	}

	/**	 
	 *设置 :评论的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**	 
	 *获取 :评论的内容
	 */
	public String getContent() {
		return this.content;
	}

	/**	 
	 *获取 :sjBefore
	 */
    public Date getSjBefore() {
        return this.sjBefore;
    }

	/**	 
	 *设置 :sjBefore
	 */
    public void setSjBefore(Date sjBefore) {
        this.sjBefore = sjBefore;
    }


	/**	 
	 *获取 :sjAfter
	 */
    public Date getSjAfter() {
        return this.sjAfter;
    }

	/**	 
	 *设置 :sjAfter
	 */
    public void setSjAfter(Date sjAfter) {
        this.sjAfter = sjAfter;
    }

}
