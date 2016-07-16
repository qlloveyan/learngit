/**
 * Project Name:smcs<br>
 * File Name:SysSyleMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2015年09月24日  上午10:44:06<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.system.model.SysSyle;

/**
 * ClassName:SysSyleMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年09月24日  上午10:44:06<br>
 * 
 * @author   zhangwei
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysSyleMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author zhangwei
	 * @param sysSyle 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysSyle sysSyle);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author zhangwei
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author zhangwei
	 * @param sysSyle 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(SysSyle sysSyle);
    
    /**
	 * edit:编辑实体信息.
	 *
	 * @author zhangwei
	 * @param sysSyle 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntityLogo(SysSyle sysSyle);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author zhangwei
	 * @param syssyle 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(SysSyle syssyle);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author zhangwei
	 * @param syssyle 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysSyle> list(SysSyle syssyle,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author zhangwei
	 * @param syssyle 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysSyle> list(SysSyle syssyle);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysSyle getEntityById(long id);

}
