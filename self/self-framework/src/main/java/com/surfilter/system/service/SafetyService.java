/**
 * Project Name:smcs<br>
 * File Name:SafetyService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2014年03月11日  下午02:05:48<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.surfilter.system.model.Safety;
import com.surfilter.system.dao.SafetyMapper;

/**
 * ClassName:SafetyService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年03月11日  下午02:05:48<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SafetyService {

	/**
	 *注入safetyMapper
	 */
	@Autowired
	private SafetyMapper safetyMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Safety> getPageModel(Safety entity,RowBounds rowBounds){
		Page<Safety> page = new Page<Safety>();
		long total = safetyMapper.count(entity);
		List<Safety> rows = safetyMapper.list(entity,rowBounds);
		page = new Page<Safety>(total, rows);
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
	public List<Safety> getPageList(Safety entity){
		List<Safety> rows = safetyMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Safety entity){
		safetyMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public synchronized int editEntity(Safety entity){
		
		return safetyMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Safety getEntityById(long id){
		return safetyMapper.getEntityById(id);
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
		
		return safetyMapper.delEntity(id);
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
    	return safetyMapper.delBatchEntity(ids);
    }
    
    /**
	 * getEntityById:根据用户ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Safety getEntityByUserId(long userId){
		return safetyMapper.getEntityByUserId(userId);
	}
	
	//将系统用户登陆状态初始化
	public void initLoginState(){
		safetyMapper.initLoginState();
	}
}
