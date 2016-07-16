/**
\ * Project Name:smcs<br>
 * File Name:RoleMapper.java<br>
 * Package Name:com.smcs.framework.system.dao<br>
 * Date:2013年09月22日  下午03:25:26<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.Role;
import com.surfilter.system.model.RoleRelation;

/**
 * ClassName:RoleMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月22日  下午03:25:26<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface RoleMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param role 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Role role);
	
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
    * @Title: delRoleRelation 
    * @Description: TODO(删除角色的继承关系) 
    * @param roleId
    * @return int     
    * @throws 
    * @author wangguohong
    */
    public int delRoleRelation(long roleId);
    
    
    /** 
    * @Title: addRoleRelation 
    * @Description: TODO(添加角色继承关系) 
    * @return void     
    * @throws 
    * @author wangguohong
    */
    public void addRoleRelation(RoleRelation role);
    
    
    /** 
    * @Title: list 
    * @Description: TODO(获取角色继承关系) 
    * @param role
    * @return List<RoleRelation>     
    * @throws 
    * @author wangguohong
    */
    public List<RoleRelation> list(RoleRelation role);
    
    
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
	 * @param role 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Role role);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param role 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Role role);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param role 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Role> list(Role role,RowBounds rowBounds);
	
	 /**
	 * listNoPage:根据查询条件不分页查询实体信息.
	 *
	 * @author tangbiao
	 * @param role 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Role> listNoPage(@Param("role")Role role);
	
	
	public List<Role> listjcjs(@Param("role")Role role);
	
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Role getEntityById(long id);

	/**
	 * delModuleByRoleId:清空对应角色Id的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @since JDK 1.6
	 */
	public void delModuleByRoleId(@Param("roleId")long roleId);
	
	/**
	 * delElementByRoleIdAndElementId:清空对应角色Id的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @since JDK 1.6
	 */
	public void delElementByRoleIdAndElementId(@Param("roleId")long roleId,@Param("elementId")long elementId,@Param("funcId")long funcId);

	/**
	 * addRoleModule:新增一条角色模块对应关系.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param roleId 模块ID
	 * @since JDK 1.6
	 */
	public void addRoleModule(@Param("roleId")long roleId, @Param("funcId")long funcId);
	
	/**
	 * addRoleElement:新增一条角色元素对应关系.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param roleId 模块ID
	 * @since JDK 1.6
	 */
	public void addRoleElement(@Param("roleId")long roleId, @Param("elementId")long elementId,@Param("funcId")long funcId);

	/**
	 * 
	 * countRoleIsUsed:(对应的角色ID正在被多少用户使用). <br/>
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 使用该角色的用户数
	 * @since JDK 1.6
	 */
	public long countRoleIsUsed(@Param("roleId")long roleId);
	
	/**
	 * 
	* @Title: countRoleByUserid 
	* @Description: TODO(查询该用户对于的角色个数) 
	* @param userId
	* @return long    该用户分配的角色个数 
	* @throws 
	* @author wangguohong
	 */
	public long countRoleByUserid(@Param("userId")long userId);
	
}
