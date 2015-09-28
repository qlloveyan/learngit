/**
 * Project Name:smcs<br>
 * File Name:CommonRegion.java<br>
 * Package Name:com.etribe.model<br>
 * Date:2015年06月07日  下午12:18:31<br>
 *
*/
package com.ql.basepro.system.model;
import java.io.Serializable;

//import com.etribe.framework.BaseEntity;

/**
 * ClassName:CommonRegion.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月07日  下午12:18:31<br>
 * 
 * @author   ql
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
//public class CommonRegion extends BaseEntity implements Serializable{
public class CommonRegion implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private String id;
	/**
	 *
	 */
	private String name;
	/**
	 *
	 */
	private String pid;
	/**
	 *是否可见
	 */
	private String visable;
	


	/**	 
	 *设置 :
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**	 
	 *获取 :
	 */
	public String getName() {
		return this.name;
	}

	/**	 
	 *设置 :
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**	 
	 *获取 :
	 */
	public String getPid() {
		return this.pid;
	}

	/**	 
	 *设置 :是否可见
	 */
	public void setVisable(String visable) {
		this.visable = visable;
	}

	/**	 
	 *获取 :是否可见
	 */
	public String getVisable() {
		return this.visable;
	}

}
