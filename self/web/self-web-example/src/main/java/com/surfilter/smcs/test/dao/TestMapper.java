/**
 * Project Name:smcs<br>
 * File Name:TestMapper.java<br>
 * Package Name:com.surfilter.smcs.test.dao<br>
 * Date:2016年07月16日  下午07:45:34<br>
 *
*/
package com.surfilter.smcs.test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.smcs.test.model.Test;

/**
 * ClassName:TestMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2016年07月16日  下午07:45:34<br>
 * 
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface TestMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author quanli
	 * @param test 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Test test);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author quanli
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author quanli
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author quanli
	 * @param test 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Test test);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author quanli
	 * @param test 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Test test);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author quanli
	 * @param test 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Test> list(Test test,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author quanli
	 * @param test 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Test> list(Test test);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author quanli
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Test getEntityById(long id);

}
