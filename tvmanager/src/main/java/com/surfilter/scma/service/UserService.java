/**
 * Project Name:smcs<br>
 * File Name:UserService.java<br>
 * Package Name:com.surfilter.scma.service<br>
 * Date:2015年05月18日  下午07:06:32<br>
 *
*/
package com.surfilter.scma.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.scma.dao.UserMapper;
import com.surfilter.scma.framework.Page;
import com.surfilter.scma.model.User;

/**
 * ClassName:UserService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:06:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class UserService {

	/**
	 *注入userMapper
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<User> getPageModel(User entity,RowBounds rowBounds){
		Page<User> page = new Page<User>();
		long total = userMapper.count(entity);
		List<User> rows = userMapper.list(entity,rowBounds);
		page = new Page<User>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<User> getPageList(User entity){
		List<User> rows = userMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(User entity){
		userMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(User entity){
		return userMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public User getEntityById(long id){
		return userMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return userMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return userMapper.delBatchEntity(ids);
    }
    
    /**
	 * 根据用户名获取实体
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public User getEntityByCodeAndPas(String username,String password){
		return userMapper.getEntityByCodeAndPas(username, password);
	}

}
