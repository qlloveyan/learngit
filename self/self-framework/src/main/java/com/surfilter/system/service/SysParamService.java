/**
 * Project Name:smcs<br>
 * File Name:SysParamService.java<br>
 * Package Name:com.smcs.framework.system.service<br>
 * Date:2013年09月18日  上午10:50:16<br>
 *
*/
package com.surfilter.system.service;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.SysParamMapper;
import com.surfilter.system.model.SysParam;

/**
 * ClassName:SysParamService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月18日  上午10:50:16<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SysParamService {

	/**
	 *注入sysParamMapper
	 */
	@Autowired
	private SysParamMapper sysParamMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysParam> getPageModel(SysParam entity,RowBounds rowBounds){
		Page<SysParam> page = new Page<SysParam>();
		long total = sysParamMapper.count(entity);
		List<SysParam> rows = sysParamMapper.list(entity,rowBounds);
		page = new Page<SysParam>(total, rows);
		return page;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysParam entity){
		sysParamMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysParam entity){
		return sysParamMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysParam getEntityById(long id){
		return sysParamMapper.getEntityById(id);
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
		
		return sysParamMapper.delEntity(id);
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
    	return sysParamMapper.delBatchEntity(ids);
    }
}
