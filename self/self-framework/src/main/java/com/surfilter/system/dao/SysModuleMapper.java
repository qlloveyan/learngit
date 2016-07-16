/**
 * Project Name:smcs<br>
 * File Name:SysModuleMapper.java<br>
 * Package Name:com.smcs.framework.system.dao<br>
 * Date:2013年09月22日  下午03:25:26<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.SysModule;

/**
 * ClassName:SysModuleMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月22日  下午03:25:26<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysModuleMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param sysmodule 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysModule sysmodule);
	
	/**
	 * upNode:指定的节点上移. <br/>
	 *
	 * @author tangbiao
     * @param parentId  指定范围内要移动的节点的父节点  
     * @param minIndex  指定节点要移动到的目标位置  
     * @param maxIndex  指定节点移动发生时所在的位置  
	 * @since JDK 1.6
	 */
	public void upNode(@Param("parentId")int parentId, @Param("minIndex")int minIndex, @Param("maxIndex")int maxIndex);
	
	/**
	 * downNode:指定的节点下移. <br/>
	 *
	 * @author tangbiao
     * @param parentId  指定范围内要移动的节点的父节点  
     * @param minIndex  指定节点移动发生时所在的位置  
     * @param maxIndex  指定节点要移动到的目标位置  
	 * @since JDK 1.6
	 */
	public void downNode(@Param("parentId")int parentId, @Param("minIndex")int minIndex, @Param("maxIndex")int maxIndex);
	
	/**
	 * 获得当前ID下子节点最大的排序号
	 * getMaxSort:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author tangbiao
	 * @param parentId 新增节点的父ID
	 * @return
	 * @since JDK 1.6
	 */
	public Long getMaxSort(@Param("parentId")long parentId);
	
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
	 * listChildModuleByUserAndType: 根据用户ID，父模块ID和模块类型查询子模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @param moduleType	模块类型（菜单或者按钮）
	 * @param parentModuleId	父模块ID
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<SysModule> listChildModuleByUserAndType(@Param("userId")Long userId, @Param("moduleType")String moduleType, @Param("parentModuleId")String parentModuleId);
	
	/**
	 * 
	 * listChildModuleByParentModuleId:根据父id获取列表. <br/>
	 *
	 * @author wangguohong
	 * @param parentModuleId 父id
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysModule> listChildModuleByParentModuleId(@Param("parentModuleId")String parentModuleId);
    
   /**
	 * edit:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param sysmodule 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(SysModule sysmodule);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param sysmodule 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(SysModule sysmodule);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param sysmodule 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysModule> list(@Param("sysmodule")SysModule sysmodule,RowBounds rowBounds);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysModule getEntityById(long id);
	
    /**
	 * listAllModuleByRoleId: 获取对应的RoleId的模块权限. <br/>
	 *
	 * @author tangbiao
	 * @return	模块信息id列表
	 * @since JDK 1.6
	 */
	public List<String> getAllModuleByRoleId(@Param("roleId")long roleId);

}
