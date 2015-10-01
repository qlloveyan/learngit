/**
 * Project Name:smcs<br>
 * File Name:UserLoginService.java<br>
 * Package Name:com.ql.basepro.system.service<br>
 * Date:2015年06月03日  下午08:12:32<br>
 *
*/
package com.ql.basepro.system.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.basepro.framework.Page;
import com.ql.basepro.system.dao.UserLoginMapper;
import com.ql.basepro.system.model.UserLogin;

/**
 * ClassName:UserLoginService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月03日  下午08:12:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserLoginService {

	/**
	 *注入userLoginMapper
	 */
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<UserLogin> getPageModel(UserLogin entity,RowBounds rowBounds) throws Exception{
		Page<UserLogin> page = new Page<UserLogin>();
		long total = userLoginMapper.count(entity);
		List<UserLogin> rows = userLoginMapper.list(entity,rowBounds);
		page = new Page<UserLogin>(total, rows);
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
	public List<UserLogin> getPageList(UserLogin entity) throws Exception{
		List<UserLogin> rows = userLoginMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(UserLogin entity) throws Exception{
		userLoginMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(UserLogin entity) throws Exception{
		return userLoginMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public UserLogin getEntityById(long id) throws Exception{
		return userLoginMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id) throws Exception{
		
		return userLoginMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids) throws Exception{
    	return userLoginMapper.delBatchEntity(ids);
    }
}
