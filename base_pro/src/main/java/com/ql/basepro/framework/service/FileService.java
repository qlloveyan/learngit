/**
 * Project Name:smcs<br>
 * File Name:FileService.java<br>
 * Package Name:com.ql.basepro.service<br>
 * Date:2015年08月20日  下午03:15:32<br>
 *
*/
package com.ql.basepro.framework.service;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.ql.basepro.framework.Page;
import com.ql.basepro.framework.dao.FileMapper;
import com.ql.basepro.framework.model.File;

import java.util.List;

/**
 * ClassName:FileService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年08月20日  下午03:15:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FileService {

	/**
	 *注入fileMapper
	 */
	@Autowired
	private FileMapper fileMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<File> getPageModel(File entity,RowBounds rowBounds){
		Page<File> page = new Page<File>();
		long total = fileMapper.count(entity);
		List<File> rows = fileMapper.list(entity,rowBounds);
		page = new Page<File>(total, rows);
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
	public List<File> getPageList(File entity){
		List<File> rows = fileMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(File entity){
		fileMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(File entity){
		return fileMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public File getEntityById(long id){
		return fileMapper.getEntityById(id);
	}
	
	public File getEntityByOrderId(long orderId){
		return fileMapper.getEntityByOrderId(orderId);
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
		
		return fileMapper.delEntity(id);
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
    	return fileMapper.delBatchEntity(ids);
    }
}
