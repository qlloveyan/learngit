/**
 * Project Name:smcs<br>
 * File Name:Menu.java<br>
 * Package Name:com.ql.basepro.system.model<br>
 * Date:2015年06月09日  下午02:47:35<br>
 *
*/
package com.ql.basepro.system.model;
import java.io.Serializable;

/**
 * ClassName:Menu.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月09日  下午02:47:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Menu implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Long id;
	/**
	 *
	 */
	private String iconCls;
	/**
	 *
	 */
	private String name;
	/**
	 *
	 */
	private String pageCmpUrl;
	/**
	 *
	 */
	private Integer renderLevel;
	/**
	 *
	 */
	private Integer showIndex;
	/**
	 *
	 */
	private String parentMenu;
	
	private String parentMenuName;


	/**	 
	 *设置 :
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**	 
	 *获取 :
	 */
	public String getIconCls() {
		return this.iconCls;
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
	public void setPageCmpUrl(String pageCmpUrl) {
		this.pageCmpUrl = pageCmpUrl;
	}

	/**	 
	 *获取 :
	 */
	public String getPageCmpUrl() {
		return this.pageCmpUrl;
	}

	/**	 
	 *设置 :
	 */
	public void setRenderLevel(Integer renderLevel) {
		this.renderLevel = renderLevel;
	}

	/**	 
	 *获取 :
	 */
	public Integer getRenderLevel() {
		return this.renderLevel;
	}

	/**	 
	 *设置 :
	 */
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	/**	 
	 *获取 :
	 */
	public Integer getShowIndex() {
		return this.showIndex;
	}

	/**	 
	 *设置 :
	 */
	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**	 
	 *获取 :
	 */
	public String getParentMenu() {
		return this.parentMenu;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

}
