/**
 * Project Name:smcs<br>
 * File Name:AccessLog.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2015年05月20日  上午09:14:37<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;

import java.util.Date;

/**
 * ClassName:AccessLog.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月20日  上午09:14:37<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AccessLog extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *日志信息
	 */
	private String logMsg;
	/**
	 *访问端IP
	 */
	private String ip;
	/**
	 *访问用户
	 */
	private String userName;
	/**
	 *时间
	 */
	private Date time;
	/**
	 *功能模块
	 */
	private String funcModule;
	/**
	 *一级菜单
	 */
	private String fiMenu;
	/**
	 *二级菜单
	 */
	private String seMenu;
	/**
	 *方法类型
	 */
	private String methodType;
	/**
	 *查询起始时间
	 */
	private Date timeBefore;
	/**
	 *查询截止时间
	 */
	private Date timeAfter;
	


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
	 *设置 :日志信息
	 */
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	/**	 
	 *获取 :日志信息
	 */
	public String getLogMsg() {
		return this.logMsg;
	}

	/**	 
	 *设置 :访问端IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**	 
	 *获取 :访问端IP
	 */
	public String getIp() {
		return this.ip;
	}

	/**	 
	 *设置 :访问用户
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**	 
	 *获取 :访问用户
	 */
	public String getUserName() {
		return this.userName;
	}

	/**	 
	 *设置 :时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**	 
	 *获取 :时间
	 */
	public Date getTime() {
		return this.time;
	}

	/**	 
	 *设置 :功能模块
	 */
	public void setFuncModule(String funcModule) {
		this.funcModule = funcModule;
	}

	/**	 
	 *获取 :功能模块
	 */
	public String getFuncModule() {
		return this.funcModule;
	}

	public String getFiMenu() {
		return fiMenu;
	}

	public void setFiMenu(String fiMenu) {
		this.fiMenu = fiMenu;
	}

	public String getSeMenu() {
		return seMenu;
	}

	public void setSeMenu(String seMenu) {
		this.seMenu = seMenu;
	}

	/**	 
	 *设置 :方法类型
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	/**	 
	 *获取 :方法类型
	 */
	public String getMethodType() {
		return this.methodType;
	}

	/**	 
	 *获取 :timeBefore
	 */
    public Date getTimeBefore() {
        return this.timeBefore;
    }

	/**	 
	 *设置 :timeBefore
	 */
    public void setTimeBefore(Date timeBefore) {
        this.timeBefore = timeBefore;
    }


	/**	 
	 *获取 :timeAfter
	 */
    public Date getTimeAfter() {
        return this.timeAfter;
    }

	/**	 
	 *设置 :timeAfter
	 */
    public void setTimeAfter(Date timeAfter) {
        this.timeAfter = timeAfter;
    }

}
