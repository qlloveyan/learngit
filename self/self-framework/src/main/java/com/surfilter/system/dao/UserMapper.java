/**
 * Project Name:smcs<br>
 * File Name:UserMapper.java<br>
 * Package Name:com.smcs.framework.system.dao<br>
 * Date:2013年09月22日  下午03:25:25<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.User;

/**
 * ClassName:UserMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月22日  下午03:25:25<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface UserMapper {
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param user 实体信息
	 * @since JDK 1.6
	 */
	void addEntity(User user);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param user 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    int editEntity(User user);
    
    /**
   	 * delUserRoleRef:删除用户角色信息.
   	 *
   	 * @author tangbiao
   	 * @param userId 用户Id
   	 * @return 删除用户角色信息
   	 * @since JDK 1.6
   	 */
    void delUserRoleRef(@Param("userId")long userId);
    
    /**
	 * delUserRoleByRoleId:根据元素ID删除元素角色表相关数据.
	 *
	 * @author hongcheng
	 * @param roleId 角色ID
	 * @since JDK 1.6
	 */
	public void delUserRoleByRoleId(long roleId);
    
    /**
   	 * delUserRoleRef:删除用户角色信息.
   	 *
   	 * @author tangbiao
   	 * @param userId 用户Id
   	 * @param roleId 角色Id
   	 * @return 删除用户角色信息
   	 * @since JDK 1.6
   	 */
    void authRole(@Param("userId")long userId, @Param("roleId")long roleId);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param user 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	long count(User user);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param user 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	List<User> list(User user,RowBounds rowBounds);
	
	public List<User> listAll(User user);
	
	

		
		/**
		 * list:根据查询条件查询实体信息.
		 *
		 * @author wangguohong
		 * @param user 实体信息
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
		public List<User> list(User user);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	User getEntityById(long id);
	
	/**
	 * getEntityByCode:通过实体userCode查询实体信息.
	 *
	 * @author hongcheng
	 * @param userCode 登录名称
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	List<User> getEntityByCode(@Param("userCode")String userCode,@Param("userName")String userName);
	
	List<User> caLogin(@Param("userCode")String userCode);
	
	/**
	 * getEntityByCodeAndPasswd: 根据用户名和密码查询用户. <br/>
	 *
	 * @author Tuyan
	 * @param userCode	用户名
	 * @param password	密码
	 * @return	用户信息
	 * @since JDK 1.6
	 */
	User getEntityByCodeAndPasswd(@Param("userCode")String userCode, @Param("userPassword")String password);
	
	/**
	 * getUserRolesByUserId: 根据用户ID查询用户所包含的角色ID. <br/>
	 *
	 * @author tangbiao
	 * @param userId	用户ID
	 * @return	用户信息
	 * @since JDK 1.6
	 */
	List<String> getUserRolesByUserId(@Param("userId")long userId);

	/**
	 * reUserState:改变用户状态.
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param userState 用户状态
	 * @return 
	 * @since JDK 1.6
	 */
	void reUserState(@Param("userId")long userId, @Param("userState")long userState);
	
	/**
	 * 
	 * @param userId 用户ID
	 * @return 角色名称
	 */
	Set<String> getRoleNameByUserId(@Param("userId")long userId);
	
	Set<String> getUserIdByUserRoles(@Param("roleId")long roleId);
	
}
 