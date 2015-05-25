/**
 * Project Name:smcs<br>
 * File Name:Program.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:07:17<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:Program.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:17<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Program implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *节目的唯一编号
	 */
	private Long id;
	/**
	 *节目名称
	 */
	private String pname;
	/**
	 *节目类别
	 */
	private String ptyp;
	/**
	 *节目首播时间
	 */
	private Date psttime;
	/**
	 *节目截止时间
	 */
	private Date pdetime;
	/**
	 *节目简介
	 */
	private String ptxt;
	/**
	 *主要演员
	 */
	private String actor;
	/**
	 *节目导演
	 */
	private String director;
	/**
	 *节目链接地址
	 */
	private String dizhi;
	/**
	 *节目中海报的存储路径
	 */
	private String fileurl;
	/**
	 *节目发布时间
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
	 *查询起始时间
	 */
	private Date pdetimeBefore;
	/**
	 *查询截止时间
	 */
	private Date pdetimeAfter;
	/**
	 *查询起始时间
	 */
	private Date psttimeBefore;
	/**
	 *查询截止时间
	 */
	private Date psttimeAfter;
	


	/**	 
	 *设置 :节目的唯一编号
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :节目的唯一编号
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :节目名称
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**	 
	 *获取 :节目名称
	 */
	public String getPname() {
		return this.pname;
	}

	/**	 
	 *设置 :节目类别
	 */
	public void setPtyp(String ptyp) {
		this.ptyp = ptyp;
	}

	/**	 
	 *获取 :节目类别
	 */
	public String getPtyp() {
		return this.ptyp;
	}

	/**	 
	 *设置 :节目首播时间
	 */
	public void setPsttime(Date psttime) {
		this.psttime = psttime;
	}

	/**	 
	 *获取 :节目首播时间
	 */
	public Date getPsttime() {
		return this.psttime;
	}

	/**	 
	 *设置 :节目截止时间
	 */
	public void setPdetime(Date pdetime) {
		this.pdetime = pdetime;
	}

	/**	 
	 *获取 :节目截止时间
	 */
	public Date getPdetime() {
		return this.pdetime;
	}

	/**	 
	 *设置 :节目简介
	 */
	public void setPtxt(String ptxt) {
		this.ptxt = ptxt;
	}

	/**	 
	 *获取 :节目简介
	 */
	public String getPtxt() {
		return this.ptxt;
	}

	/**	 
	 *设置 :主要演员
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}

	/**	 
	 *获取 :主要演员
	 */
	public String getActor() {
		return this.actor;
	}

	/**	 
	 *设置 :节目导演
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**	 
	 *获取 :节目导演
	 */
	public String getDirector() {
		return this.director;
	}

	/**	 
	 *设置 :节目链接地址
	 */
	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	/**	 
	 *获取 :节目链接地址
	 */
	public String getDizhi() {
		return this.dizhi;
	}

	/**	 
	 *设置 :节目中海报的存储路径
	 */
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	/**	 
	 *获取 :节目中海报的存储路径
	 */
	public String getFileurl() {
		return this.fileurl;
	}

	/**	 
	 *设置 :节目发布时间
	 */
	public void setFabuTime(Date fabuTime) {
		this.fabuTime = fabuTime;
	}

	/**	 
	 *获取 :节目发布时间
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

	/**	 
	 *获取 :pdetimeBefore
	 */
    public Date getPdetimeBefore() {
        return this.pdetimeBefore;
    }

	/**	 
	 *设置 :pdetimeBefore
	 */
    public void setPdetimeBefore(Date pdetimeBefore) {
        this.pdetimeBefore = pdetimeBefore;
    }


	/**	 
	 *获取 :pdetimeAfter
	 */
    public Date getPdetimeAfter() {
        return this.pdetimeAfter;
    }

	/**	 
	 *设置 :pdetimeAfter
	 */
    public void setPdetimeAfter(Date pdetimeAfter) {
        this.pdetimeAfter = pdetimeAfter;
    }

	/**	 
	 *获取 :psttimeBefore
	 */
    public Date getPsttimeBefore() {
        return this.psttimeBefore;
    }

	/**	 
	 *设置 :psttimeBefore
	 */
    public void setPsttimeBefore(Date psttimeBefore) {
        this.psttimeBefore = psttimeBefore;
    }


	/**	 
	 *获取 :psttimeAfter
	 */
    public Date getPsttimeAfter() {
        return this.psttimeAfter;
    }

	/**	 
	 *设置 :psttimeAfter
	 */
    public void setPsttimeAfter(Date psttimeAfter) {
        this.psttimeAfter = psttimeAfter;
    }

}
