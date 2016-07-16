/**
 * Project Name:smcs<br>
 * File Name:FuncModuleMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2013年11月06日  下午01:41:40<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.FuncModule;

/**
 * ClassName:FuncModuleMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:40<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface FuncModuleMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param funcmodule 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(FuncModule funcmodule);
	
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
	 * 
	 * getSortCount:根据序号查询是否存在. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @param sort 序号
	 * @return
	 * @since JDK 1.6
	 */
	public Long getSortCount(@Param("parentId")long parentId,@Param("funcSort")long funcSort);
	
	/**
	 * 
	 * getSortCountEdit:根据序号查询是否存在. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @param sort 序号
	 * @param id id
	 * @return
	 * @since JDK 1.6
	 */
	public Long getSortCountEdit(@Param("parentId")long parentId,@Param("funcSort")long funcSort,@Param("id")long id);
	
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
	public List<FuncModule> listChildModuleByUserAndType(@Param("userId")Long userId, @Param("funcType")String funcType, @Param("parentId")Long parentId);
	

 	/**
	 * listChildModuleByUserAndType: 获取没有禁用的菜单. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @param moduleType	模块类型（菜单或者按钮）
	 * @param parentModuleId	父模块ID
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<FuncModule> listNoChildModuleByUserAndType(@Param("userId")Long userId, @Param("funcType")String funcType, @Param("parentId")Long parentId);
	
	
	
	/**
	 * 
	 * listChildModuleByParentModuleId:根据父id获取列表. <br/>
	 *
	 * @author wangguohong
	 * @param parentModuleId 父id
	 * @return
	 * @since JDK 1.6
	 */
	public List<FuncModule> listChildModuleByParentId(@Param("parentId")Long parentId);
	
	/**
	 * listFuncModuleByCode: 根据模块编码查询模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param funcCode	模块编码
	 * @return
	 * @since JDK 1.6
	 */
	public List<FuncModule> listFuncModuleByCode(@Param("funcCode")String funcCode);
		
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
	 * delFuncModuleRoleByElementId:根据功能ID删除功能角色表相关数据.
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @since JDK 1.6
	 */
	public void delFuncModuleRoleByFuncId(long funcId);
	
	/**
	 * delFuncModuleRoleByRoleId:根据元素ID删除元素角色表相关数据.
	 *
	 * @author hongcheng
	 * @param roleId 角色ID
	 * @since JDK 1.6
	 */
	public void delFuncModuleRoleByRoleId(long roleId);
	
   /**
	 * edit:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param funcmodule 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(FuncModule funcmodule);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param funcmodule 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(FuncModule funcmodule);
	
	/**
	 * 
	 * getMenuCountByParentId:通过父ID查询菜单数目. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @return 菜单数目
	 * @since JDK 1.6
	 */
	public long getMenuCountByParentId(Long parentId);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param funcmodule 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<FuncModule> list(FuncModule funcmodule,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param funcmodule 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<FuncModule> list(FuncModule funcmodule);
	
	/**
	 * 
	 * listNotLike:(通过条件查询所有，与like区别的的是此方法不适用like查询，都是 = 的精确查找). <br/>
	 * @author tangbiao
	 * @param funcmodule
	 * @return
	 * @since JDK 1.6
	 */
	public List<FuncModule> listNotLike(FuncModule funcmodule);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public FuncModule getEntityById(long id);
	
	 /**
	 * listAllModuleByRoleId: 获取对应的RoleId的模块权限. <br/>
	 *
	 * @author tangbiao
	 * @return	模块信息id列表
	 * @since JDK 1.6
	 */
	public List<String> getAllModuleByRoleId(@Param("roleId")long roleId);
	
	/**
	 * 
	 * getFuncUrlByUserId:通过用户ID查询该用户拥有的URL权限. <br/>
	 *
	 * @author hongcheng
	 * @param userId 用户ID
	 * @return URL集合
	 * @since JDK 1.6
	 */
	public List<FuncModule> getFuncModuleByUserId(long userId);
	
	/**
	 * 
	 * getfunCode:通过用户ID查询该用户拥有的URL权限. <br/>
	 *
	 * @author qifan
	 * @param 无
	 * @return 对象集合
	 * @since JDK 1.6
	 */
	public List<FuncModule> getFunCode();

}
