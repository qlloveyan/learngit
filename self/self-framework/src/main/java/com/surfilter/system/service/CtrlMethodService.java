/**
 * Project Name:smcs<br>
 * File Name:CtrlMethodService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2014年11月19日  上午09:57:23<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import com.surfilter.system.model.CtrlMethod;
import com.surfilter.system.dao.CtrlMethodMapper;

/**
 * ClassName:CtrlMethodService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年11月19日  上午09:57:23<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class CtrlMethodService {

	/**
	 *注入ctrlMethodMapper
	 */
	@Autowired
	private CtrlMethodMapper ctrlMethodMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<CtrlMethod> getPageModel(CtrlMethod entity,RowBounds rowBounds){
		Page<CtrlMethod> page = new Page<CtrlMethod>();
		long total = ctrlMethodMapper.count(entity);
		List<CtrlMethod> rows = ctrlMethodMapper.list(entity,rowBounds);
		page = new Page<CtrlMethod>(total, rows);
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
	public List<CtrlMethod> getPageList(CtrlMethod entity){
		List<CtrlMethod> rows = ctrlMethodMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(CtrlMethod entity){
		ctrlMethodMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(CtrlMethod entity){
		return ctrlMethodMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public CtrlMethod getEntityById(long id){
		return ctrlMethodMapper.getEntityById(id);
	}
	
	/**
	 * getEntity:根据模块、类名、方法名查找实体
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public List<CtrlMethod> getEntity(Map<String,String> map){
		return ctrlMethodMapper.getEntity(map);
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
		
		return ctrlMethodMapper.delEntity(id);
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
    	return ctrlMethodMapper.delBatchEntity(ids);
    }
}
