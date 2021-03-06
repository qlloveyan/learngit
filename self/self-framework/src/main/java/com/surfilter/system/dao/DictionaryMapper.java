/**
 * Project Name:smcs<br>
 * File Name:DictionaryMapper.java<br>
 * Package Name:com.smcs.framework.system.dao<br>
 * Date:2013年09月22日  下午03:25:25<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.system.model.Dictionary;

/**
 * ClassName:DictionaryMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月22日  下午03:25:25<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface DictionaryMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param dictionary 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Dictionary dictionary);
	
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
	 * @param dictionary 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(Dictionary dictionary);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author hongcheng
	 * @param dictionary 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(Dictionary dictionary);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author hongcheng
	 * @param dictionary 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<Dictionary> list(@Param("dictionary")Dictionary dictionary,RowBounds rowBounds);
	
	
	   /**
		 * list:根据查询条件查询实体信息.
		 *
		 * @author zhangwei
		 * @param dictionary 实体信息
		 * @return 查询实体集合
		 * @since JDK 1.6
		 */
	public List<Dictionary> list(@Param("dictionary")Dictionary dictionary);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Dictionary getEntityById(long id);

	/**
	 * 
	 * getDictionaryByType:根据字典类型获取字典数据. <br/>
	 *
	 * @author wangguohong
	 * @param type
	 * @return
	 * @since JDK 1.6
	 */
	public List<Dictionary> getDictionaryByType(@Param("type")String type);
	
	/**
	 * 
	 * getDictionaryByKey:根据type ，key 获取字典数据. <br/>
	 * @author wangguohong
	 * @param type
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	public Dictionary getDictionaryByKey(@Param("type")String type,@Param("key")String key);
}
