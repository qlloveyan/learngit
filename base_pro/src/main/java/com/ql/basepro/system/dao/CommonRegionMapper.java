/**
 * Project Name:smcs<br>
 * File Name:CommonRegionMapper.java<br>
 * Package Name:com.etribe.dao<br>
 * Date:2015年06月07日  下午12:18:31<br>
 *
*/
package com.ql.basepro.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.ql.basepro.system.model.CommonRegion;

/**
 * ClassName:CommonRegionMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月07日  下午12:18:31<br>
 * 
 * @author   ql
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface CommonRegionMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author ql
	 * @param commonRegion 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(CommonRegion commonRegion);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author ql
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author ql
	 * @param commonRegion 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(CommonRegion commonRegion);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author ql
	 * @param commonregion 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(CommonRegion commonregion);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param commonregion 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<CommonRegion> list(CommonRegion commonregion,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param commonregion 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<CommonRegion> list(CommonRegion commonregion);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public CommonRegion getEntityById(long id);

}
