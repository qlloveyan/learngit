/**
 * Project Name:smcs<br>
 * File Name:ManagerService.java<br>
 * Package Name:com.surfilter.scma.service<br>
 * Date:2015年05月18日  下午07:07:04<br>
 *
*/
package com.surfilter.scma.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.scma.dao.ManagerMapper;
import com.surfilter.scma.framework.Page;
import com.surfilter.scma.model.Manager;

/**
 * ClassName:ManagerService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:04<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class ManagerService {

	/**
	 *注入managerMapper
	 */
	@Autowired
	private ManagerMapper managerMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Manager> getPageModel(Manager entity,RowBounds rowBounds){
		Page<Manager> page = new Page<Manager>();
		long total = managerMapper.count(entity);
		List<Manager> rows = managerMapper.list(entity,rowBounds);
		page = new Page<Manager>(total, rows);
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
	public List<Manager> getPageList(Manager entity){
		List<Manager> rows = managerMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Manager entity){
		managerMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Manager entity){
		return managerMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Manager getEntityById(long id){
		return managerMapper.getEntityById(id);
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
		
		return managerMapper.delEntity(id);
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
    	return managerMapper.delBatchEntity(ids);
    }
}
