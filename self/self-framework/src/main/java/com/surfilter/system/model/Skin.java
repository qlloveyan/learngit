/**
 * Project Name:smcs<br>
 * File Name:Skin.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2013年12月25日  上午10:27:00<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:Skin.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月25日  上午10:27:00<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Skin extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键ID
	 */
	private Long id;
	/**
	 *皮肤编号
	 */
	private String skinCode;
	/**
	 *用户id
	 */
	private Long userId;
	


	/**	 
	 *设置 :主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键ID
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :皮肤编号
	 */
	public void setSkinCode(String skinCode) {
		this.skinCode = skinCode;
	}

	/**	 
	 *获取 :皮肤编号
	 */
	public String getSkinCode() {
		return this.skinCode;
	}

	/**	 
	 *设置 :用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**	 
	 *获取 :用户id
	 */
	public Long getUserId() {
		return this.userId;
	}

}
