/**
 * Project Name:smcs<br>
 * File Name:UserRelUnitPositionDepartmentService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2014年02月25日  下午07:43:23<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.surfilter.system.model.UserRelUnitPositionDepartment;
import com.surfilter.system.dao.UserRelUnitPositionDepartmentMapper;

/**
 * ClassName:UserRelUnitPositionDepartmentService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class UserRelUnitPositionDepartmentService {

	/**
	 *注入userRelUnitPositionDepartmentMapper
	 */
	@Autowired
	private UserRelUnitPositionDepartmentMapper userRelUnitPositionDepartmentMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<UserRelUnitPositionDepartment> getPageModel(UserRelUnitPositionDepartment entity,RowBounds rowBounds){
		Page<UserRelUnitPositionDepartment> page = new Page<UserRelUnitPositionDepartment>();
		long total = userRelUnitPositionDepartmentMapper.count(entity);
		List<UserRelUnitPositionDepartment> rows = userRelUnitPositionDepartmentMapper.list(entity,rowBounds);
		page = new Page<UserRelUnitPositionDepartment>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<UserRelUnitPositionDepartment> getPageList(UserRelUnitPositionDepartment entity){
		List<UserRelUnitPositionDepartment> rows = userRelUnitPositionDepartmentMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(UserRelUnitPositionDepartment entity){
		userRelUnitPositionDepartmentMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(UserRelUnitPositionDepartment entity){
		return userRelUnitPositionDepartmentMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public UserRelUnitPositionDepartment getEntityById(long id){
		return userRelUnitPositionDepartmentMapper.getEntityById(id);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public UserRelUnitPositionDepartment getEntityByUserId(long userId){
		return userRelUnitPositionDepartmentMapper.getEntityByUserId(userId);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return userRelUnitPositionDepartmentMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author wangguohong
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return userRelUnitPositionDepartmentMapper.delBatchEntity(ids);
    }
}
