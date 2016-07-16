/**
 * Project Name:smcs<br>
 * File Name:SysLog.java<br>
 * Package Name:com.smcs.framework.system.model<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import java.util.Date;

import com.surfilter.framework.model.BaseEntity;

/**
 * ClassName:SysLog.java<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysLog extends BaseEntity implements Serializable{
	
	public enum OperType {
		GET("get"),
		LIST("list"),
		IS("is"),
		ADD("add"),
		EDIT("edit"),
		DEL("del")
		;
		
		private String suffix;
		
		OperType(String suffix) {
			this.suffix = suffix;
		}

		/**
		 * suffix.
		 *
		 * @return  the suffix
		 * @since   JDK 1.6
		 */
		public final String getSuffix() {
			return suffix;
		}
		
		public static OperType forSuffix(String suffix) {
			OperType ot = null;
			for (OperType t : values()) {
				if (suffix.equalsIgnoreCase(t.getSuffix())) {
					ot = t;
					break;
				}
			}
			return ot;
		}
	}
	
	/**
	 * AUTH_LOG: 登录以及授权日志.
	 * @since JDK 1.6
	 */
	public final static String AUTH_LOG_TYPE = "AUTH_LOG_TYPE";
	
	/**
	 * OPER_LOG_TYPE: 行为日志.
	 * @since JDK 1.6
	 */
	public final static String BEHAVIOR_LOG_TYPE = "BEHAVIOR_LOG_TYPE";
	
	/**
	 * OPER_SUCCESS: 操作成功.
	 * @since JDK 1.6
	 */
	public final static long OPER_SUCCESS = 1L;
	
	/**
	 * OPER_EXECPTION: 操作失败.
	 * @since JDK 1.6
	 */
	public final static long OPER_EXECPTION = 0L;
	
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *ID
	 */
	private Long id;
	/**
	 *功能名称
	 */
	private String moduleName;
	/**
	 *操作员名称
	 */
	private String userName;
	/**
	 *操作时间
	 */
	private Date opreateTime;
	/**
	 *操作类型
	 */
	private String operateType;
	/**
	 *日志类型（登录日志，行为日志）
	 */
	private String logType;
	/**
	 *操作结果（1.成功。2.失败【异常】）
	 */
	private Long operateResult;
	/**
	 *操作描述
	 */
	private String description;
	/**
	 *操作人IP地址
	 */
	private String opreaterIp;
	/**
	 *查询起始时间
	 */
	private Date opreateTimeBefore;
	/**
	 *查询截止时间
	 */
	private Date opreateTimeAfter;
	


	/**	 
	 *设置 :ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :功能名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**	 
	 *获取 :功能名称
	 */
	public String getModuleName() {
		return this.moduleName;
	}

	/**	 
	 *设置 :操作员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**	 
	 *获取 :操作员名称
	 */
	public String getUserName() {
		return this.userName;
	}

	/**	 
	 *设置 :操作时间
	 */
	public void setOpreateTime(Date opreateTime) {
		this.opreateTime = opreateTime;
	}

	/**	 
	 *获取 :操作时间
	 */
	public Date getOpreateTime() {
		return this.opreateTime;
	}
	
	/**	 
	 *设置 :操作类型
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**	 
	 *获取 :操作类型
	 */
	public String getOperateType() {
		return this.operateType;
	}

	/**	 
	 *设置 :日志类型（登录日志，行为日志）
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**	 
	 *获取 :日志类型（登录日志，行为日志）
	 */
	public String getLogType() {
		return this.logType;
	}

	/**	 
	 *设置 :操作结果（1.成功。2.失败【异常】）
	 */
	public void setOperateResult(Long operateResult) {
		this.operateResult = operateResult;
	}

	/**	 
	 *获取 :操作结果（1.成功。2.失败【异常】）
	 */
	public Long getOperateResult() {
		return this.operateResult;
	}

	/**	 
	 *设置 :操作描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**	 
	 *获取 :操作描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**	 
	 *设置 :操作人IP地址
	 */
	public void setOpreaterIp(String opreaterIp) {
		this.opreaterIp = opreaterIp;
	}

	/**	 
	 *获取 :操作人IP地址
	 */
	public String getOpreaterIp() {
		return this.opreaterIp;
	}

	/**	 
	 *获取 :opreateTimeBefore
	 */
    public Date getOpreateTimeBefore() {
        return this.opreateTimeBefore;
    }

	/**	 
	 *设置 :opreateTimeBefore
	 */
    public void setOpreateTimeBefore(Date opreateTimeBefore) {
        this.opreateTimeBefore = opreateTimeBefore;
    }


	/**	 
	 *获取 :opreateTimeAfter
	 */
    public Date getOpreateTimeAfter() {
        return this.opreateTimeAfter;
    }

	/**	 
	 *设置 :opreateTimeAfter
	 */
    public void setOpreateTimeAfter(Date opreateTimeAfter) {
        this.opreateTimeAfter = opreateTimeAfter;
    }

}
