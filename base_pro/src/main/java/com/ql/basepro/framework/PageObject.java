package com.ql.basepro.framework;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;
/**
 * 
 * ClassName: PageObject <br/>
 * Function:分页. <br/>
 * date: 2013年9月18日 下午2:30:13 <br/>
 *
 * @author hongcheng
 * @version 
 * @since JDK 1.6
 */
public class PageObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 页面字符串
	 */
	private String pageString;

	/**
	 * 分页信息
	 */
	private RowBounds rowBounds;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 每一页行数
	 */
	private int perPage;
	
	/**
	 * 总行数
	 */
	private int totalRow;
	
	/**
	 * 
	 * getCurrentPage:获取当前页. <br/>
	 *
	 * @author hongcheng
	 * @return 当前页吗
	 * @since JDK 1.6
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * 
	 * setCurrentPage:设置当前页. <br/>
	 *
	 * @author hongcheng
	 * @param currentPage 当前页
	 * @since JDK 1.6
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 
	 * getTotalPage:获得总页数. <br/>
	 *
	 * @author hongcheng
	 * @return 总页数
	 * @since JDK 1.6
	 */
	public int getTotalPage() {
		return totalPage;
	}
	
	/**
	 * 
	 * setTotalPage:设置总页数. <br/>
	 *
	 * @author hongcheng
	 * @param totalPage 总页数
	 * @since JDK 1.6
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * 
	 * getPerPage:获得每一页行数. <br/>
	 *
	 * @author hongcheng
	 * @return 每页行数
	 * @since JDK 1.6
	 */
	public int getPerPage() {
		return perPage;
	}
	
	/**
	 * 
	 * setPerPage:设置每页行数. <br/>
	 *
	 * @author hongcheng
	 * @param perPage 每页行数
	 * @since JDK 1.6
	 */
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	/**
	 * 
	 * getPageString:获得页面字符串. <br/>
	 *
	 * @author hongcheng
	 * @return 页面字符串
	 * @since JDK 1.6
	 */
	public String getPageString() {
		return pageString;
	}
	
	/**
	 * 
	 * setPageString:设置页面字符串. <br/>
	 *
	 * @author hongcheng
	 * @param pageString 设置页面字符串
	 * @since JDK 1.6
	 */
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	
	/**
	 * 
	 * getRowBounds:获取分页信息. <br/>
	 *
	 * @author hongcheng
	 * @return 分页信息
	 * @since JDK 1.6
	 */
	public RowBounds getRowBounds() {
		return rowBounds;
	}
	
	/**
	 * 
	 * setRowBounds:设置分页信息. <br/>
	 *
	 * @author hongcheng
	 * @param rowBounds 分页信息
	 * @since JDK 1.6
	 */
	public void setRowBounds(RowBounds rowBounds) {
		this.rowBounds = rowBounds;
	}
	
	/**
	 * 
	 * getTotalRow:获取总行数. <br/>
	 *
	 * @author hongcheng
	 * @return 总行数
	 * @since JDK 1.6
	 */
	public int getTotalRow() {
		return totalRow;
	}
	
	/**
	 * 
	 * setTotalRow:设置总行数. <br/>
	 *
	 * @author hongcheng
	 * @param totalRow 总行数
	 * @since JDK 1.6
	 */
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
    
	
}
