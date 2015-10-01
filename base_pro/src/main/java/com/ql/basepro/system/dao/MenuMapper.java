/**
 * Project Name:smcs<br>
 * File Name:MenuMapper.java<br>
 * Package Name:com.ql.basepro.system.dao<br>
 * Date:2015年06月09日  下午02:47:35<br>
 *
*/
package com.ql.basepro.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.ql.basepro.system.model.Menu;

/**
 * ClassName:MenuMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月09日  下午02:47:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface MenuMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param menu 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Menu menu);
	
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
	 * @param menu 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Menu menu);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author lenovo
	 * @param menu 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Menu menu);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param menu 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Menu> list(Menu menu,RowBounds rowBounds);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author lenovo
	 * @param menu 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Menu> list(Menu menu);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Menu getEntityById(long id);
	
	public List<Menu> getChildByParent(@Param("parentMenu")Long parentMenu);

}
