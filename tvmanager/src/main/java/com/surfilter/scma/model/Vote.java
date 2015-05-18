/**
 * Project Name:smcs<br>
 * File Name:Vote.java<br>
 * Package Name:com.surfilter.scma.model<br>
 * Date:2015年05月18日  下午07:07:45<br>
 *
*/
package com.surfilter.scma.model;
import java.io.Serializable;

/**
 * ClassName:Vote.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:45<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Vote implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *后选内容唯一编号
	 */
	private String id;
	/**
	 *后选内容的名称
	 */
	private String vname;
	/**
	 *后选内容的类别
	 */
	private String leibie;
	/**
	 *所获票数（初始值为零）
	 */
	private String cNum;
	


	/**	 
	 *设置 :后选内容唯一编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :后选内容唯一编号
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :后选内容的名称
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}

	/**	 
	 *获取 :后选内容的名称
	 */
	public String getVname() {
		return this.vname;
	}

	/**	 
	 *设置 :后选内容的类别
	 */
	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	/**	 
	 *获取 :后选内容的类别
	 */
	public String getLeibie() {
		return this.leibie;
	}

	/**	 
	 *设置 :所获票数（初始值为零）
	 */
	public void setCNum(String cNum) {
		this.cNum = cNum;
	}

	/**	 
	 *获取 :所获票数（初始值为零）
	 */
	public String getCNum() {
		return this.cNum;
	}

}
