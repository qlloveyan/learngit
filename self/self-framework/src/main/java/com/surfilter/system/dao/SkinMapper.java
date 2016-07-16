/**
 * Project Name:smcs<br>
 * File Name:SkinMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2013年12月25日  上午10:27:00<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.system.model.Skin;

/**
 * ClassName:SkinMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月25日  上午10:27:00<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SkinMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param skin 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Skin skin);
	
	
	
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
	 * @param skin 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Skin skin);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author wangguohong
	 * @param skin 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Skin skin);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param skin 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Skin> list(Skin skin,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param skin 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Skin> list(Skin skin);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Skin getEntityById(long id);
	
	
	/**
	 * getEntityByUserId:(根据用户名id获取皮肤). <br/>
	 *
	 * @author wangguohong
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Skin getEntityByUserId(long userId);

}
