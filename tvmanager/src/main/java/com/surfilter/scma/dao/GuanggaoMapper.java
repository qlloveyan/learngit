/**
 * Project Name:smcs<br>
 * File Name:GuanggaoMapper.java<br>
 * Package Name:com.surfilter.scma.dao<br>
 * Date:2015年05月18日  下午07:07:55<br>
 *
*/
package com.surfilter.scma.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.scma.model.Guanggao;

/**
 * ClassName:GuanggaoMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:55<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface GuanggaoMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param guanggao 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Guanggao guanggao);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author lenovo
	 * @param guanggao 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Guanggao guanggao);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author lenovo
	 * @param guanggao 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Guanggao guanggao);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param guanggao 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Guanggao> list(Guanggao guanggao,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param guanggao 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Guanggao> list(Guanggao guanggao);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Guanggao getEntityById(long id);

}
