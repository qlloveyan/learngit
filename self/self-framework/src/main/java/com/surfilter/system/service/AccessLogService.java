/**
 * Project Name:smcs<br>
 * File Name:AccessLogService.java<br>
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.surfilter.system.model.AccessLog;
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.dao.AccessLogMapper;
import com.surfilter.system.dao.CtrlMethodMapper;
import com.surfilter.system.dao.FuncModuleMapper;

/**
 * ClassName:AccessLogService.java<br>
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
public class AccessLogService {

	/**
	 *注入accessLogMapper
	 */
	@Autowired
	private AccessLogMapper accessLogMapper;
	
	@Autowired
	private FuncModuleMapper funcModuleMapper;
	
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
	public Page<AccessLog> getPageModel(AccessLog entity,RowBounds rowBounds){
		Page<AccessLog> page = new Page<AccessLog>();
		long total = accessLogMapper.count(entity);
		List<AccessLog> rows = accessLogMapper.list(entity,rowBounds);
		page = new Page<AccessLog>(total, rows);
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
	public List<AccessLog> getPageList(AccessLog entity){
		List<AccessLog> rows = accessLogMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(AccessLog entity){
		accessLogMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(AccessLog entity){
		return accessLogMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public AccessLog getEntityById(long id){
		return accessLogMapper.getEntityById(id);
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
		
		return accessLogMapper.delEntity(id);
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
    	return accessLogMapper.delBatchEntity(ids);
    }
    
    public void clearData(Date startTime){
    	accessLogMapper.clearData(startTime);
    }

	public List<FuncModule> getPlateFormList(String[] ids) {
		List<FuncModule> result = new ArrayList<FuncModule>();
		for(String id:ids){
			result.add( funcModuleMapper.getEntityById( Long.parseLong(id) ) );
		}
		return result;
	}

	public List<FuncModule> getFuncMenuList(long parentId) {
		return funcModuleMapper.listChildModuleByParentId(parentId);
	}

	public List<Map<String, String>> getMethodType() {
		List<Map<String, String>> result  = new ArrayList<Map<String,String>>();
		List<String> temp = ctrlMethodMapper.tjMethodType();
		for(String str : temp){
			Map<String,String> nMap = new HashMap<String, String>();
			nMap.put("id", str);
			nMap.put("value", str);
			
			result.add(nMap);
		}
		return result;
	}
}
