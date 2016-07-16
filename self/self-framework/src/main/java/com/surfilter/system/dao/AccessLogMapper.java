/**
 * Project Name:smcs<br>
 * File Name:AccessLogMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2014年11月19日  上午09:57:23<br>
 *
*/
package com.surfilter.system.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.system.model.AccessLog;

/**
 * ClassName:AccessLogMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年11月19日  上午09:57:23<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface AccessLogMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param accessLog 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(AccessLog accessLog);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author lenovo
	 * @param accessLog 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(AccessLog accessLog);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author lenovo
	 * @param accesslog 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(AccessLog accesslog);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param accesslog 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<AccessLog> list(AccessLog accesslog,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param accesslog 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<AccessLog> list(AccessLog accesslog);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public AccessLog getEntityById(long id);

	public void clearData(Date startTime);

}
