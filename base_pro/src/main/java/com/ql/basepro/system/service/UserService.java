package com.ql.basepro.system.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.basepro.framework.Page;
import com.ql.basepro.system.dao.UserLoginMapper;
import com.ql.basepro.system.dao.UserMapper;
import com.ql.basepro.system.model.User;
import com.ql.basepro.system.model.UserLogin;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserService {
	
	/**
	 * 注入用户操作dao类
	 */
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	/**
	 * getPageModel:分页展示用户信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @since JDK 1.6
	 */
	public Page<User> getPageModel(User user, RowBounds rowBounds)  throws Exception{
		
		Page<User> pageModel = new Page<User>();
		List<User> rows = userMapper.list(user, rowBounds);
		long total = userMapper.count(user);
		
		pageModel.setRows(rows);
		pageModel.setTotal(total);
		
		return pageModel;
	}

	/**
	 * addEntity:新增实体.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(User user) throws Exception{
		userMapper.addEntity(user);
	}
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id) throws Exception{
    	return userMapper.delEntity(id);
    }
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author ql
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids) throws Exception{
    	return userMapper.delBatchEntity(ids);
    }
   /**
	 * edit:编辑实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(User user) throws Exception{
    	return userMapper.editEntity(user);
    }
    
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<User> list(User user) throws Exception{
		return userMapper.list(user);
	}
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public User getEntityById(long id) throws Exception{
		return userMapper.getEntityById(id);
	}
	
	/**
	 * 根据用户名获取实体
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public User getEntityByCodeAndPas(String phone,String password,String status) throws Exception{
		return userMapper.getEntityByCodeAndPas(phone, password,status);
	}
	
	public User getEntityByPhone(String phone) throws Exception{
		return userMapper.getEntityByPhone(phone);
	}

	//修改用户状态
	public void reUserState(long userId, long userState) throws Exception{
		userMapper.reUserState(userId,userState);
	}
	
	//修改用户密码
	public void changePassword(User user) throws Exception{
		userMapper.changePassword(user);
	}

	//添加用户登录记录
	public void addUserLogin( User user , boolean isSuccess){
		UserLogin userLogin = new UserLogin();
		userLogin.setUserid(user.getName());
		userLogin.setLogintime( new Date() );
		userLogin.setRemark( "用户["+user.getName()+"]登录系统,登录 "+ (isSuccess?"成功":"失败"));
		
		userLoginMapper.addEntity(userLogin);
	}
}
