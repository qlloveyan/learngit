/**
 * Project Name:smcs<br>
 * File Name:CtrlMethodMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2014年11月19日  上午09:57:23<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.CtrlMethod;

/**
 * ClassName:CtrlMethodMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年11月19日  上午09:57:23<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface CtrlMethodMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param ctrlMethod 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(CtrlMethod ctrlMethod);
	
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
	 * @param ctrlMethod 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(CtrlMethod ctrlMethod);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author lenovo
	 * @param ctrlmethod 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(CtrlMethod ctrlmethod);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param ctrlmethod 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<CtrlMethod> list(CtrlMethod ctrlmethod,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param ctrlmethod 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<CtrlMethod> list(CtrlMethod ctrlmethod);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public CtrlMethod getEntityById(long id);
	
	/**
	 * getEntity:根据模块、类名、方法名查找实体
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public List<CtrlMethod> getEntity(Map<String,String> map);

	/**
	 * 
	 * tjMethodType:统计控制类方法类型. <br/>
	 *
	 * @author lenovo
	 * @return
	 * @since JDK 1.6
	 */
	public List<String> tjMethodType();
}
