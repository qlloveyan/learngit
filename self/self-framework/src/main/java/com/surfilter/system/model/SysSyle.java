/**
 * Project Name:smcs<br>
 * File Name:SysSyle.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2015年09月24日  上午10:44:06<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:SysSyle.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年09月24日  上午10:44:06<br>
 * 
 * @author   zhangwei
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SysSyle extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *元素id,如果是菜单则关联菜单的id
	 */
	private String elementId;
	/**
	 *元素类型，1菜单，2,logo,3,背景图片，4,皮肤,5，背景文件夹
	 */
	private String elementType;
	/**
	 *UI元素设置值
	 */
	private String value;
	/**
	 *用户id
	 */
	private Long userId;
	
	/**
	 *用户选择样式方案
	 */
	private String syleType;
	


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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**	 
	 *设置 :元素类型，1菜单，2,logo,3,背景图片，4,皮肤,5，背景文件夹
	 */
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	/**	 
	 *获取 :元素类型，1菜单，2,logo,3,背景图片，4,皮肤,5，背景文件夹
	 */
	public String getElementType() {
		return this.elementType;
	}

	/**	 
	 *设置 :UI元素设置值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**	 
	 *获取 :UI元素设置值
	 */
	public String getValue() {
		return this.value;
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

	public String getSyleType() {
		return syleType;
	}

	public void setSyleType(String syleType) {
		this.syleType = syleType;
	}
	
	

}
