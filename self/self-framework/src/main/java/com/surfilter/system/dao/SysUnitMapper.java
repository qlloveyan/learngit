/**
 * Project Name:smcs<br>
 * File Name:SysUnitMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2013年10月25日  下午07:56:54<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.AreaCode;
import com.surfilter.system.model.SysUnit;

/**
 * ClassName:SysUnitMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年10月25日  下午07:56:54<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface SysUnitMapper {
    
		/**
		 * addEntity:新增实体.
		 *
		 * @author hongcheng
		 * @param sysunit 实体信息
		 * @since JDK 1.6
		 */
		public void addEntity(SysUnit sysunit);
		
		
		/**
		 * listByNativeId:(根据nativeId获取集合). <br/>
		 * @author wangguohong
		 * @param nativeId
		 * @return 集合
		 * @since JDK 1.6
		 */
		public List<SysUnit> listByNativeId(@Param("nativeId")String nativeId,@Param("prentId")String prentId);
		
		
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
		 * @param sysunit 实体信息
		 * @return 编辑实体结果
		 * @since JDK 1.6
		 */
	    public int editEntity(SysUnit sysunit);
	    
	   /**
		 * count:根据查询条件查询符合条件的数目.
		 *
		 * @author hongcheng
		 * @param sysunit 实体信息
		 * @return 数目
		 * @since JDK 1.6
		 */
		public long count(SysUnit sysunit);
		
	   /**
		 * list:根据查询条件查询实体信息.
		 *
		 * @author hongcheng
		 * @param sysunit 实体信息
		 * @param rowBounds 分页信息
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
		public List<SysUnit> list(SysUnit sysunit,RowBounds rowBounds);
	
		/**
		 * getEntityById:根据实体ID查询实体信息.
		 *
		 * @author hongcheng
		 * @param id 实体ID
		 * @return 实体信息
		 * @since JDK 1.6
		 */
		public SysUnit getEntityById(long id);
	
	 /**
		 * listChildSysUnitByCondition:根据查询条件查询实体信息.
		 *
		 * @author hongcheng
		 * @param sysunit 实体信息
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
		public List<SysUnit> listChildSysUnitByLevelAndParentId(@Param("unitLevel") String unitLevel,@Param("parentId") String  parentId);
		
		
		/**
		 * 通过父ID查询单位信息
		 * getEntityByParentid:(这里用一句话描述这个方法的作用). <br/>
		 *
		 * @author hongcheng
		 * @param parentId 父ID
		 * @return
		 * @since JDK 1.6
		 */
		public List<SysUnit> getEntityByParentId(String parentId);
		
		/**
		 * list:根据单位类型条件查询实体信息.
		 *
		 * @author zhangjw
		 * @param unitType 单位类型
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
		public List<SysUnit> getListByUnitType(@Param("unitType")String unitType);
		
		/**
		 * getAreaByType:查询所有市的信息
		 *
		 * @author ql
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
		public List<AreaCode> getAreaByType(String provinceCode);
		
		public List<SysUnit> checkNAndC(@Param("nativeId")String nativeId, @Param("city")String city);

}
