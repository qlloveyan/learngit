package com.surfilter.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * ClassName: Page <br/>
 * Function: 分页. <br/>
 * date: 2013年9月18日 下午1:52:02 <br/>
 *
 * @author hongcheng
 * @version 
 * @since JDK 1.6
 */
public class Page <T> implements Serializable {
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录数目
	 */
	private long total = 0;

	/**
	 * 查询结果集合
	 */
	private List<T> rows = null;
	
	/**
	 * 构造函数
	 * Creates a new instance of Page.
	 *
	 */
	public Page() {
		super();
	}

	/**
	 * 构造函数
	 * Creates a new instance of Page.
	 *
	 * @param total 记录数目
	 * @param rows 查询结果集合
	 */
	public Page(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	/**
	 * 
	 * getTotal:获得记录数目. <br/>
	 *
	 * @author hongcheng
	 * @return 记录数目
	 * @since JDK 1.6
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 
	 * setTotal:设置记录数目. <br/>
	 *
	 * @author hongcheng
	 * @param total 记录数目
	 * @since JDK 1.6
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * 
	 * getRows:获得记录集合. <br/>
	 *
	 * @author hongcheng
	 * @return 记录集合
	 * @since JDK 1.6
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 
	 * setRows:获得记录集合. <br/>
	 *
	 * @author hongcheng
	 * @param rows 记录集合
	 * @since JDK 1.6
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
