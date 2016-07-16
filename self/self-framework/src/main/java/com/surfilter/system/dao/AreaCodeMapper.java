/**
 * Project Name:smcs<br>
 * File Name:AreaCodeMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2013年12月10日  下午07:36:02<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.surfilter.system.model.AreaCode;

/**
 * ClassName:AreaCodeMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 
 * Date:     2013年12月10日  下午07:36:02<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface AreaCodeMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param areaCode 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(AreaCode areaCode);
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delEntity(long id);
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(@Param("ids") long[] ids);
   /**
	 * edit:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param areaCode 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(AreaCode areaCode);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param areacode 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(AreaCode areaCode);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param areacode 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<AreaCode> list(AreaCode areaCode,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author dingzewen
	 * @param areacode 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<AreaCode> list(AreaCode areaCode);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author dingzewen
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public AreaCode getEntityById(long id);
	
	/**
	 * getEntityByCode:根据实体ID查询实体信息.
	 * @author dingzewen
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	
	public AreaCode getEntityByCode(long areaCode);
	
	/**
	 * 
	 * getListByProvinceAreaCode:根据省查询所属市的信息
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 */
	public List<AreaCode> getListByProvinceAreaCode(AreaCode areaCode);

}
