/**
 * Project Name:lichen
 * File Name:UkeyVo.java
 * Package Name:com.surfilter.system.ukey
 * Date:2014-8-12下午8:06:10
 *
*/

package com.surfilter.system.ukey;

import java.io.Serializable;

/**
 * ClassName:UkeyVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-8-12 下午8:06:10 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UkeyVo implements Serializable{
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 序列号
	 */
	private String ukeyNum;
	
	/**
	 * 加密值
	 */
	private String md5hash;
	
	/**
	 * ukey链接状态  1正常 0未链接
	 */
	private String status;

	/**
	 * status.
	 *
	 * @return  the status
	 * @since   JDK 1.6
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * status.
	 *
	 * @param   status    the status to set
	 * @since   JDK 1.6
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ukeyNum.
	 *
	 * @return  the ukeyNum
	 * @since   JDK 1.6
	 */
	public String getUkeyNum() {
		return ukeyNum;
	}

	/**
	 * ukeyNum.
	 *
	 * @param   ukeyNum    the ukeyNum to set
	 * @since   JDK 1.6
	 */
	public void setUkeyNum(String ukeyNum) {
		this.ukeyNum = ukeyNum;
	}

	/**
	 * md5hash.
	 *
	 * @return  the md5hash
	 * @since   JDK 1.6
	 */
	public String getMd5hash() {
		return md5hash;
	}

	/**
	 * md5hash.
	 *
	 * @param   md5hash    the md5hash to set
	 * @since   JDK 1.6
	 */
	public void setMd5hash(String md5hash) {
		this.md5hash = md5hash;
	}
	
	
}

