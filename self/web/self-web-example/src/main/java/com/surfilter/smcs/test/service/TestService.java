/**
 * Project Name:smcs<br>
 * File Name:TestService.java<br>
 * Package Name:com.surfilter.smcs.test.service<br>
 * Date:2016年07月16日  下午07:45:34<br>
 *
*/
package com.surfilter.smcs.test.service;
import com.surfilter.framework.page.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.surfilter.smcs.test.model.Test;
import com.surfilter.smcs.test.dao.TestMapper;

/**
 * ClassName:TestService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2016年07月16日  下午07:45:34<br>
 * 
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class TestService {

	/**
	 *注入testMapper
	 */
	@Autowired
	private TestMapper testMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author quanli
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Test> getPageModel(Test entity,RowBounds rowBounds){
		Page<Test> page = new Page<Test>();
		long total = testMapper.count(entity);
		List<Test> rows = testMapper.list(entity,rowBounds);
		page = new Page<Test>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author quanli
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<Test> getPageList(Test entity){
		List<Test> rows = testMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author quanli
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Test entity){
		testMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author quanli
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Test entity){
		return testMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author quanli
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Test getEntityById(long id){
		return testMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author quanli
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return testMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author quanli
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return testMapper.delBatchEntity(ids);
    }
}
