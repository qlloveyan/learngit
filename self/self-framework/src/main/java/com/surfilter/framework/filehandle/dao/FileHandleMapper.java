/**
 * Project Name:smcs<br>
 * File Name:FileHandleMapper.java<br>
 * Package Name:com.surfilter.framework.filehandle.dao<br>
 * Date:2013年11月12日  下午05:22:29<br>
 *
*/
package com.surfilter.framework.filehandle.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.framework.filehandle.model.FileHandle;

/**
 * ClassName:FileHandleMapper.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月12日  下午05:22:29<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface FileHandleMapper {
    
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param fileHandle 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(FileHandle fileHandle);
	
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
	 * @param fileHandle 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
    public int editEntity(FileHandle fileHandle);
    
   /**
	 * count:根据查询条件查询符合条件的数目.
	 *
	 * @author wangguohong
	 * @param filehandle 实体信息
	 * @return 数目
	 * @since JDK 1.6
	 */
	public long count(FileHandle filehandle);
	
   /**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param filehandle 实体信息
	 * @param rowBounds 分页信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<FileHandle> list(FileHandle filehandle,RowBounds rowBounds);
	public List<FileHandle> getImglist(FileHandle filehandle);
	
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author wangguohong
	 * @param filehandle 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<FileHandle> list(FileHandle filehandle);
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public FileHandle getEntityById(long id);

	public FileHandle getEntityByRid(@Param("resourceId") String resourceId,@Param("resourceCode") String resourceCode);

}