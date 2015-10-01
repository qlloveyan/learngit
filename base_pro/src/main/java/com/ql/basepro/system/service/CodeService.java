/**
 * Project Name:smcs<br>
 * File Name:CodeService.java<br>
 * Package Name:com.ql.basepro.system.service<br>
 * Date:2015年06月04日  下午03:28:50<br>
 *
*/
package com.ql.basepro.system.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.basepro.framework.Page;
import com.ql.basepro.system.dao.CodeMapper;
import com.ql.basepro.system.dao.UserMapper;
import com.ql.basepro.system.model.Code;

/**
 * ClassName:CodeService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月04日  下午03:28:50<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CodeService {

	/**
	 *注入codeMapper
	 */
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Code> getPageModel(Code entity,RowBounds rowBounds) throws Exception{
		Page<Code> page = new Page<Code>();
		long total = codeMapper.count(entity);
		List<Code> rows = codeMapper.list(entity,rowBounds);
		page = new Page<Code>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<Code> getPageList(Code entity) throws Exception{
		List<Code> rows = codeMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Code entity) throws Exception{
		codeMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Code entity) throws Exception{
		return codeMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Code getEntityById(long id) throws Exception{
		return codeMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id) throws Exception{
		
		return codeMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids) throws Exception{
    	return codeMapper.delBatchEntity(ids);
    }

	public List<Code> getDictionary(Code entity) throws Exception{
		return codeMapper.getDictionary(entity);
	}

	public List<Code> getParentCode() {
		return codeMapper.getParentCode();
	}
	
}
