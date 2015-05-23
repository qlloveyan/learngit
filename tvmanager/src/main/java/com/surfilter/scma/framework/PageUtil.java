package com.surfilter.scma.framework;

import org.apache.ibatis.session.RowBounds;

import com.surfilter.scma.framework.ConfigUtil;
import com.surfilter.scma.framework.StringUtils;

public class PageUtil {
	
	/**
	 * 第一页.
	 */
	public static final int FIRST_PAGE = 1;
	
	/**
	 * 默认每页行数.
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	/**
	 * 
	 * get:通过起始行、每页行数得到分页信息. <br/>
	 *
	 * @author hongcheng
	 * @param pageNo 页数
	 * @param pageSize 每页行数
	 * @return 分页信息
	 * @since JDK 1.6
	 */
	public static RowBounds get(Integer pageNo, Integer pageSize) {
		if(pageNo == null || pageNo < 0) {
			pageNo = 1;
		}
		if(pageSize == null || pageSize <=0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		return getRowBounds(pageNo, pageSize);
	}
	
	/**
	 * getFirst:获得起始数据行数.
	 *
	 * @author hongcheng
	 * @param pageNo 页码
	 * @param pageSize 每页行数
	 * @return 起始数据行数
	 * @since JDK 1.6
	 */
	private static int getFirst(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 
	 * getLast:获取最后一条数据的行数. <br/>
	 *
	 * @author hongcheng
	 * @param first 起始行
	 * @param pageSize 每页行数
	 * @return 最后一条数据的行数
	 * @since JDK 1.6
	 */
	private static int getLast(int first, int pageSize) {
		return first + pageSize;
	}
	
	/**
	 * 
	 * begin:根据页码、每页行数获取起始行数. <br/>
	 *
	 * @author hongcheng
	 * @param pageNo 页码
	 * @param pageSize 每页行数
	 * @return 起始行数
	 * @since JDK 1.6
	 */
	public static int begin(int pageNo, int pageSize){
		return getFirst(pageNo,pageSize);
	}
	
	/**
	 * 
	 * end:通过页码、每页行数获取最后 一条数据的行数. <br/>
	 *
	 * @author hongcheng
	 * @param pageNo 页码
	 * @param pageSize 每页行数
	 * @return 最后一条数据的行数
	 * @since JDK 1.6
	 */
	public static int end(int pageNo, int pageSize){
		return getLast(getFirst(pageNo,pageSize),pageSize);
	}
	
	
	/**
	 * 
	 * getTotalPage:通过总行数、每页行数计算总页数. <br/>
	 *
	 * @author hongcheng
	 * @param totalRow 总行数
	 * @param pageSize 每页行数
	 * @return 总页数
	 * @since JDK 1.6
	 */
	private static int getTotalPage(int totalRow, int pageSize) {
		if (pageSize != 0) {
			if (totalRow % pageSize != 0) {
				return totalRow / pageSize + 1;
			} else {
				return totalRow / pageSize;
			}

		} else {
			return 0;
		}
	}
	
	/**
	 * 
	 * validateRequestPage:验证请求是否符合要求. <br/>
	 *
	 * @author hongcheng
	 * @param requestPage 请求的页数
	 * @return 当前页数
	 * @since JDK 1.6
	 */
	public static int validateRequestPage(String requestPage) {
		int currentPage = 1;
		if (!StringUtils.isEmpty(requestPage)) {
			try {
				currentPage = Integer.parseInt(requestPage);
			} catch (Exception e) {
				currentPage = 1;
			}
		}
		return currentPage;
	}

	/**
	 * 
	 * validateTotalPage:验证当前页数是否合法. <br/>
	 *
	 * @author hongcheng
	 * @param currentPage 当前页数
	 * @param totalPage 总页数
	 * @return 当前页数
	 * @since JDK 1.6
	 */
	private static int validateTotalPage(int currentPage, int totalPage) {
		
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		return currentPage;
	}
	
	/**
	 * 
	 * getRowBounds:通过起始行数、每页行数得到分页信息. <br/>
	 *
	 * @author hongcheng
	 * @param pageNo 页数
	 * @param pageSize 每页行数
	 * @return 分页信息
	 * @since JDK 1.6
	 */
	private static RowBounds getRowBounds(Integer pageNo, Integer pageSize){
		
		String dataBase = ConfigUtil.getConfig("db.database", "mysql");
		
		int first = 0;
		int last = 0;
		
		if("mysql".equals(dataBase)){
			//mysql数据库
			first = getFirst(pageNo,pageSize);
			last = pageSize;
			
		}else if("oracle".equals(dataBase)){
			//oracle数据库
			first = getFirst(pageNo,pageSize);
			last = getLast(first, pageSize);
		}
		return new RowBounds(first, last);
	}
	

	/**
	 * 
	 * makePageObject:得到分页信息. <br/>
	 *
	 * @author hongcheng
	 * @param myCurrentPage 当前页
	 * @param totalRow 总行数
	 * @param pageSize 每页行数 
	 * @return 分页信息
	 * @since JDK 1.6
	 */
	public static PageObject makePageObject(String myCurrentPage, int totalRow,
			int pageSize) {
		// 验证下请求过来的当前页数是否符合要求
		int currentPage = validateRequestPage(myCurrentPage);
		// 通过总行数和每页页数得到总页数
		int totalPage = PageUtil.getTotalPage(totalRow, pageSize);
		// 验证当前页数是否在总页数区间范围内
		currentPage = PageUtil.validateTotalPage(currentPage, totalPage);
		PageObject pageObject = new PageObject();
		pageObject.setCurrentPage(currentPage);
		pageObject.setTotalPage(totalPage);
		pageObject.setPerPage(pageSize);
		pageObject.setTotalRow(totalRow);
		return pageObject;
	}
	
	

}
