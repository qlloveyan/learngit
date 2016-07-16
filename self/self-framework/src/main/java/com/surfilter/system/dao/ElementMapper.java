/**
 * Project Name:smcs<br>
 * File Name:ElementMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2013年11月06日  下午01:41:41<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.Element;

/**
 * ClassName:ElementMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:41<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ElementMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param element 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Element element);
	
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
	 * delElementRoleByElementId:根据元素ID删除元素角色表相关数据.
	 *
	 * @author hongcheng
	 * @param elementId 元素ID
	 * @since JDK 1.6
	 */
	public void delElementRoleByElementId(long elementId);
	
	/**
	 * delElementRoleByRoleId:根据元素ID删除元素角色表相关数据.
	 *
	 * @author hongcheng
	 * @param roleId 角色ID
	 * @since JDK 1.6
	 */
	public void delElementRoleByRoleId(long roleId);
    
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
	 * @param element 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Element element);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param element 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Element element);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param element 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Element> list(Element element,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param element 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Element> list(Element element);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Element getEntityById(long id);
	
	/**
	 * 
	 * getElementByFuncId:通过功能ID查询页面元素信息. <br/>
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<Element> getElementByFuncId(long funcId);
	
	/**
	 * 
	 * getElementByFuncId:通过功能ID查询页面元素信息. <br/>
	 *
	 * @author hongcheng
	 * @param elementCode 元素编码
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<Element> getElementByElementCode(String  elementCode);
	
	/**
	 * 
	 * getElementByFuncId:通过功能ID查询页面元素信息. <br/>
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<Long> getElementIdsByFuncId(long funcId);
	
	
	/**
	 * 
	 * getAllElementIds:查询所有页面元素ID. <br/>
	 *
	 * @author hongcheng
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<String> getAllElementCode();
	
	/**
	 * 
	 * getElementCodeByUserId:根据用户ID查询元素编码. <br/>
	 *
	 * @author hongcheng
	 * @param userId 用户ID
	 * @return 元素编码
	 * @since JDK 1.6
	 */
	public List<String> getElementCodeByUserId(Long userId);
	
	/**
	 * 
	 * getElementByFuncId:通过功能ID查询页面元素信息ID. <br/>
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<String> getElementIdsByFuncIdAndRoleId(@Param("funcId") long funcId,@Param("roleId") long roleId);
	

}
