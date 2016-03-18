/**
 * Project Name:shutdown
 * File Name:Page.java
 * Package Name:com.surfilter.self.angularjs.model
 * Date:2016年2月29日上午10:56:41
 *
*/

package com.surfilter.self.angularjs.model;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:Page <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月29日 上午10:56:41 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Page<E> implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	private Integer pageNum;
	
	private Integer pageSize;
	
	private Integer total;
	
	private List<E> data;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}
	
}

