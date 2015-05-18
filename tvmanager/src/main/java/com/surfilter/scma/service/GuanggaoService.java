/**
 * Project Name:smcs<br>
 * File Name:GuanggaoService.java<br>
 * Package Name:com.surfilter.scma.service<br>
 * Date:2015年05月18日  下午07:07:55<br>
 *
*/
package com.surfilter.scma.service;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.surfilter.scma.model.Guanggao;
import com.surfilter.scma.dao.GuanggaoMapper;
import com.surfilter.scma.framework.Page;

/**
 * ClassName:GuanggaoService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:55<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class GuanggaoService {

	/**
	 *注入guanggaoMapper
	 */
	@Autowired
	private GuanggaoMapper guanggaoMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Guanggao> getPageModel(Guanggao entity,RowBounds rowBounds){
		Page<Guanggao> page = new Page<Guanggao>();
		long total = guanggaoMapper.count(entity);
		List<Guanggao> rows = guanggaoMapper.list(entity,rowBounds);
		page = new Page<Guanggao>(total, rows);
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
	public List<Guanggao> getPageList(Guanggao entity){
		List<Guanggao> rows = guanggaoMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Guanggao entity){
		guanggaoMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Guanggao entity){
		return guanggaoMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Guanggao getEntityById(long id){
		return guanggaoMapper.getEntityById(id);
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
		
		return guanggaoMapper.delEntity(id);
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
    	return guanggaoMapper.delBatchEntity(ids);
    }
}
