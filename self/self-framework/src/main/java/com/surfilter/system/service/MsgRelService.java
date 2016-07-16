/**
 * Project Name:smcs<br>
 * File Name:MsgRelService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2014年01月25日  下午01:44:32<br>
 *
*/
package com.surfilter.system.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.MsgMapper;
import com.surfilter.system.dao.MsgRelMapper;
import com.surfilter.system.model.Msg;
import com.surfilter.system.model.MsgRel;

/**
 * ClassName:MsgRelService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月25日  下午01:44:32<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class MsgRelService {

	/**
	 *注入msgRelMapper
	 */
	@Autowired
	private MsgRelMapper msgRelMapper;
	
	/**
	 *注入msgMapper
	 */
	@Autowired
	private MsgMapper msgMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<MsgRel> getPageModel(MsgRel entity,RowBounds rowBounds){
		Page<MsgRel> page = new Page<MsgRel>();
		long total = msgRelMapper.count(entity);
		List<MsgRel> rows = msgRelMapper.list(entity,rowBounds);
		page = new Page<MsgRel>(total, rows);
		return page;
	}
	
	/**
	 * getMyMsg:(获取自己的消息). <br/>
	 *
	 * @author wangguohong
	 * @param entity
	 * @param rowBounds
	 * @return
	 * @since JDK 1.6
	 */
	public Page<MsgRel> getMyMsg(MsgRel entity,RowBounds rowBounds){
		Page<MsgRel> page = new Page<MsgRel>();
		long total = msgRelMapper.count(entity);
		List<MsgRel> rows = msgRelMapper.list(entity,rowBounds);
		for(MsgRel msgrel : rows){
				
				Msg msg = msgMapper.getEntityById(msgrel.getMsgId());
				msgrel.setMsg(msg);
    	}
		page = new Page<MsgRel>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<MsgRel> getPageList(MsgRel entity){
		List<MsgRel> rows = msgRelMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(MsgRel entity){
		msgRelMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(MsgRel entity){
		return msgRelMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public MsgRel getEntityById(long id){
		return msgRelMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return msgRelMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author wangguohong
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return msgRelMapper.delBatchEntity(ids);
    }
    
    /**
     * getMsgByUser:(根据用户获取消息集合). <br/>
     *
     * @author wangguohong
     * @param userId
     * @return
     * @since JDK 1.6
     */
    public List<MsgRel> getMsgByUser(String userId){
    	List<MsgRel> msgrellist = msgRelMapper.getMsgByUser(userId);
    	for(MsgRel msgrel : msgrellist){
    		Msg msg = msgMapper.getEntityById(msgrel.getMsgId());
    		msgrel.setMsg(msg);
    	}
    	return msgrellist;
    }
}
