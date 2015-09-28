/**
 * Project Name:smcs<br>
 * File Name:CommonRegionService.java<br>
 * Package Name:com.etribe.service<br>
 * Date:2015年06月07日  下午12:18:31<br>
 *
*/
package com.ql.basepro.system.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.basepro.framework.Page;
import com.ql.basepro.system.dao.CommonRegionMapper;
import com.ql.basepro.system.model.CommonRegion;

/**
 * ClassName:CommonRegionService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月07日  下午12:18:31<br>
 * 
 * @author   ql
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CommonRegionService {

	/**
	 *注入commonRegionMapper
	 */
	@Autowired
	private CommonRegionMapper commonRegionMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author ql
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<CommonRegion> getPageModel(CommonRegion entity,RowBounds rowBounds) throws Exception{
		Page<CommonRegion> page = new Page<CommonRegion>();
		long total = commonRegionMapper.count(entity);
		List<CommonRegion> rows = commonRegionMapper.list(entity,rowBounds);
		page = new Page<CommonRegion>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author ql
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<CommonRegion> getPageList(CommonRegion entity) throws Exception{
		List<CommonRegion> rows = commonRegionMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author ql
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(CommonRegion entity) throws Exception{
		commonRegionMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author ql
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(CommonRegion entity) throws Exception{
		return commonRegionMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author ql
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public CommonRegion getEntityById(long id) throws Exception{
		return commonRegionMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id) throws Exception{
		
		return commonRegionMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author ql
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids) throws Exception{
    	return commonRegionMapper.delBatchEntity(ids);
    }
}
