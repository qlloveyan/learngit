/**
 * Project Name:smcs<br>
 * File Name:UserRelUnitPositionDepartmentMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2014年02月25日  下午07:43:23<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.system.model.UserRelUnitPositionDepartment;

/**
 * ClassName:UserRelUnitPositionDepartmentMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface UserRelUnitPositionDepartmentMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param userRelUnitPositionDepartment 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(UserRelUnitPositionDepartment userRelUnitPositionDepartment);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author wangguohong
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author wangguohong
	 * @param userRelUnitPositionDepartment 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(UserRelUnitPositionDepartment userRelUnitPositionDepartment);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author wangguohong
	 * @param userrelunitpositiondepartment 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(UserRelUnitPositionDepartment userrelunitpositiondepartment);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param userrelunitpositiondepartment 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<UserRelUnitPositionDepartment> list(UserRelUnitPositionDepartment userrelunitpositiondepartment,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param userrelunitpositiondepartment 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<UserRelUnitPositionDepartment> list(UserRelUnitPositionDepartment userrelunitpositiondepartment);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public UserRelUnitPositionDepartment getEntityById(long id);
	/**
	 * getEntityById:根据实体USERID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public UserRelUnitPositionDepartment getEntityByUserId(long userId);
	

	/**
	 * deleteByUserId:(根据用户id删除单位关系). <br/>
	 *
	 * @author wangguohong
	 * @param userId
	 * @since JDK 1.6
	 */
	public void deleteByUserId(long userId);

}
