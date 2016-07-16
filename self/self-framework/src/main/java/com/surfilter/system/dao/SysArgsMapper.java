/**
 * Project Name:smcs<br>
 * File Name:SysArgsMapper.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.dao<br>
 * Date:2014年01月02日  下午07:21:36<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.SysArgs;

/**
 * ClassName:SysArgsMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:36<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysArgsMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author dengqw
	 * @param sysArgs 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysArgs sysArgs);
	
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
	 * @param sysArgs 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(SysArgs sysArgs);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author dengqw
	 * @param sysargs 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(SysArgs sysargs);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author dengqw
	 * @param sysargs 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysArgs> list(SysArgs sysargs,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author dengqw
	 * @param sysargs 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysArgs> list(SysArgs sysargs);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysArgs getEntityById(long id);
	/**
     * 
     * getArgByType:通过类别获取系统参数
     * @author dengqw
     * @param typeId
     * @return
     * @since JDK 1.6
     */
	public List<SysArgs> getArgByType(long typeId);
	/**
	 * 
	 * updateValue:通过key和类型更新值
	 *
	 * @author dengqw
	 * @param sysArgs
	 * @since JDK 1.6
	 */
	public void updateValue(SysArgs sysArgs);
	
	/**
	 * isUnique:判断唯一性
	 * @author dengqw
	 * @param sysArgs
	 * @since JDK 1.6
	 */
	public List<SysArgs> isUnique(SysArgs sysArgs);
	
	public void deleteByType(Long typeId);
	/**
	 * 
	 * getArgByTypeKey:通过类型获取值
	 *
	 * @author dengqw
	 * @param typeEn
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysArgs> getArgByTypeKey(String typeEn);
	/**
	 * 
	 * getArgByKey:通过key获取对象
	 *
	 * @author dengqw
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysArgs> getArgByKey(String key);

}
