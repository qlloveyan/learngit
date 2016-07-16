/**
 * Project Name:cannikin
 * File Name:BaseEntity.java
 * Package Name:com.smcs.core.vo
 * Date:2013-5-15上午9:23:37
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.surfilter.framework.model;

import java.io.Serializable;


/**
 * ClassName:BaseEntity <br/>
 * Function: 非冗余业务使用. <br/>
 * Date:     2013-5-15 上午9:23:37 <br/>
 * @author   Tkiyer
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {
	
	
	//默认地市编码
	private String defaultCityCode;
	
	//省编码
	private String shengCode;
	
	//省编码前缀
	private String shengCodePrefix;
	
	// 本省简称（备案号前缀）
	private String shengPrefix;
	
	//是否直辖市， 0->非直辖市， 1->直辖市
	private Long isDirectCity;
	
	/**
	 * Creates a new instance of BaseEntity.
	 *
	 */
	public BaseEntity(){}
	
	public BaseEntity(String defaultCityCode,String shengCode,String shengCodePrefix){
		this.defaultCityCode = defaultCityCode;
		this.shengCode = shengCode;
		this.shengCodePrefix = shengCodePrefix;
	}
	
	public BaseEntity(String defaultCityCode,String shengCode,String shengCodePrefix, String shengPrefix){
		this.defaultCityCode = defaultCityCode;
		this.shengCode = shengCode;
		this.shengCodePrefix = shengCodePrefix;
		this.shengPrefix = shengPrefix;
	}
	
	public BaseEntity(String defaultCityCode,String shengCode,String shengCodePrefix, String shengPrefix, Long isDirectCity){
        this.defaultCityCode = defaultCityCode;
        this.shengCode = shengCode;
        this.shengCodePrefix = shengCodePrefix;
        this.shengPrefix = shengPrefix;
        this.isDirectCity = isDirectCity;
    }

	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * 顺序
	 */
	private String order;

	/**
	 * 
	 * getSort:获得排序. <br/>
	 *
	 * @author hongcheng
	 * @return 排序
	 * @since JDK 1.6
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * 
	 * setSort:设置排序. <br/>
	 *
	 * @author hongcheng
	 * @param sort
	 * @since JDK 1.6
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 
	 * getOrder:获得排序方式. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * setOrder:设置排序方式. <br/>
	 *
	 * @author hongcheng
	 * @param order
	 * @since JDK 1.6
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	public String getDefaultCityCode() {
		return defaultCityCode;
	}

	public void setDefaultCityCode(String defaultCityCode) {
		this.defaultCityCode = defaultCityCode;
	}

	public String getShengCode() {
		return shengCode;
	}

	public void setShengCode(String shengCode) {
		this.shengCode = shengCode;
	}

	public String getShengCodePrefix() {
		return shengCodePrefix;
	}

	public void setShengCodePrefix(String shengCodePrefix) {
		this.shengCodePrefix = shengCodePrefix;
	}

	public String getShengPrefix() {
		return shengPrefix;
	}

	public void setShengPrefix(String shengPrefix) {
		this.shengPrefix = shengPrefix;
	}

    public Long getIsDirectCity() {
        return isDirectCity;
    }

    public void setIsDirectCity(Long isDirectCity) {
        this.isDirectCity = isDirectCity;
    }
}

