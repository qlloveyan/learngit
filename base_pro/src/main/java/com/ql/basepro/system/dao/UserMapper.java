package com.ql.basepro.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.ql.basepro.system.model.User;

public interface UserMapper {

	/**
	 * addEntity:新增实体.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(User user);
	
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
	 * @param user 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(User user);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(User user);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<User> list(User user,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<User> list(User user);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public User getEntityById(long id);
	
	/**
	 * 根据用户名获取实体
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public User getEntityByCodeAndPas(@Param("phone")String phone,@Param("password")String password,@Param("status")String status);

	//修改用户状态
	public void reUserState(@Param("userId")long userId,@Param("userState") long userState);
	
	public void changePassword(User user);

	public User getEntityByPhone(String phone);
}
