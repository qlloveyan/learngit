/**
 * Project Name:smcs<br>
 * File Name:AreaCodeService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年12月10日  下午07:36:02<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.surfilter.system.model.AreaCode;
import com.surfilter.system.dao.AreaCodeMapper;

/**
 * ClassName:AreaCodeService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月10日  下午07:36:02<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class AreaCodeService {

	/**
	 *注入areaCodeMapper
	 */
	@Autowired
	private AreaCodeMapper areaCodeMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<AreaCode> getPageModel(AreaCode entity,RowBounds rowBounds){
		Page<AreaCode> page = new Page<AreaCode>();
		long total = areaCodeMapper.count(entity);
		List<AreaCode> rows = areaCodeMapper.list(entity,rowBounds);
		page = new Page<AreaCode>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<AreaCode> getPageList(AreaCode entity){
		List<AreaCode> rows = areaCodeMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(AreaCode entity){
		areaCodeMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(AreaCode entity){
		return areaCodeMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public AreaCode getEntityById(long id){
		return areaCodeMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return areaCodeMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return areaCodeMapper.delBatchEntity(ids);
    }
    
    
    /**
	 * getEntityByCode:根据区域编号获取区域信息
	 *
	 * @author dingzewen
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public AreaCode getEntityByCode(long areaCode) {
		return areaCodeMapper.getEntityByCode(areaCode);
	}
    
    /**
	 * 
	 * getListByProvinceAreaCode:根据省查询所属市的信息
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 */
	public List<AreaCode> getListByProvinceAreaCode(AreaCode entity){
		List<AreaCode> rows = areaCodeMapper.getListByProvinceAreaCode(entity);
		return rows;
	}
}
