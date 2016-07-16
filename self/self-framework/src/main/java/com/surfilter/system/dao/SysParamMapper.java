/**
 * Project Name:smcs<br>
 * File Name:SysParamMapper.java<br>
 * Package Name:com.smcs.framework.system.dao<br>
 * Date:2013年09月22日  下午03:25:25<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.SysParam;

/**
 * ClassName:SysParamMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月22日  下午03:25:25<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysParamMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param sysparam 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysParam sysparam);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param sysparam 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(SysParam sysparam);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param sysparam 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(SysParam sysparam);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param sysparam 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysParam> list(SysParam sysparam,RowBounds rowBounds);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysParam getEntityById(long id);

}
