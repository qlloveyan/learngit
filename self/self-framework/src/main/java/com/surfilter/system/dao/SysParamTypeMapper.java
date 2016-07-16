/**
 * Project Name:smcs<br>
 * File Name:SysParamTypeMapper.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.dao<br>
 * Date:2014年01月02日  下午07:21:08<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.SysParamType;

/**
 * ClassName:SysParamTypeMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:08<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysParamTypeMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author dengqw
	 * @param sysParamType 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysParamType sysParamType);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author dengqw
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author dengqw
	 * @param sysParamType 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(SysParamType sysParamType);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author dengqw
	 * @param sysparamtype 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(SysParamType sysparamtype);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author dengqw
	 * @param sysparamtype 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysParamType> list(SysParamType sysparamtype,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author dengqw
	 * @param sysparamtype 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysParamType> list(SysParamType sysparamtype);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysParamType getEntityById(long id);
	/**
	 * getParent:获取父类型
	 *
	 * @author dengqw
	 * @since JDK 1.6
	 */
	public List<SysParamType> getParent();
	/**
     * getEntityByParent:通过父类型查找子类型
     * @author dengqw
     * @return
     * @since JDK 1.6
     */
	public List<SysParamType> getEntityByParent(Long parentId);
	/**
	 * getId:获取主键
	 * @author dengqw
	 * @return
	 * @since JDK 1.6
	 */
	public Long getId();
	/**
	 * isUnique:判断唯一性
	 * @author dengqw
	 * @param entity
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysParamType> isUnique(SysParamType entity);

}
