/**
 * Project Name:smcs<br>
 * File Name:SysArgsService.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.service<br>
 * Date:2014年01月02日  下午07:21:37<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.SysArgsMapper;
import com.surfilter.system.model.SysArgs;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassName:SysArgsService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:37<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SysArgsService {

	/**
	 *注入sysArgsMapper
	 */
	@Autowired
	private SysArgsMapper sysArgsMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author dengqw
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysArgs> getPageModel(SysArgs entity,RowBounds rowBounds){
		Page<SysArgs> page = new Page<SysArgs>();
		long total = sysArgsMapper.count(entity);
		List<SysArgs> rows = sysArgsMapper.list(entity,rowBounds);
		page = new Page<SysArgs>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author dengqw
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<SysArgs> getPageList(SysArgs entity){
		List<SysArgs> rows = sysArgsMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysArgs entity){
		sysArgsMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysArgs entity){
		return sysArgsMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author dengqw
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysArgs getEntityById(long id){
		return sysArgsMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return sysArgsMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author dengqw
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return sysArgsMapper.delBatchEntity(ids);
    }
    /**
     * 
     * getArgByType:通过类别获取系统参数
     * @author dengqw
     * @param typeId
     * @return
     * @since JDK 1.6
     */
	public List<SysArgs> getArgByType(long typeId) {
		return sysArgsMapper.getArgByType(typeId);
	}
	/**
	 * 
	 * updateValue:通过key和类型更新值
	 *
	 * @author dengqw
	 * @param map
	 * @since JDK 1.6
	 */
	public void saveAll(Long typeId, Map<String, String> map) {
		if(typeId!=null){
			Iterator<String> it=map.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				String value=map.get(key);
				SysArgs entity=new SysArgs();
				entity.setValue(value);
				entity.setKey(key);
				entity.setTypeId(typeId);
				sysArgsMapper.updateValue(entity);
			}
		}
		
	}

	public List<SysArgs> isUnique(SysArgs args) {
		return sysArgsMapper.isUnique(args);
	}

	public void deleteByType(Long typeId) {
		sysArgsMapper.deleteByType(typeId);
	}
	
	public Map<String,SysArgs> getArgByTypeKey(String typeEn){
		Map<String,SysArgs> map=new HashMap<String,SysArgs>();
		List<SysArgs> lst= sysArgsMapper.getArgByTypeKey(typeEn);
		if(lst!=null && lst.size()>0){
			for(SysArgs args:lst){
				map.put(args.getKey(),args);
			}
		}
		return map;
	}
	
	public SysArgs getArgByKey(String key){
		SysArgs args=new SysArgs();
		List<SysArgs> lst= sysArgsMapper.getArgByKey(key);
		if(lst!=null && lst.size()>0){
			args=lst.get(0);
		}
		return args;
	}
	
}
