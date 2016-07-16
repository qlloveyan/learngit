/**
 * Project Name:smcs<br>
 * File Name:MsgService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2014年01月25日  下午01:11:24<br>
 *
*/
package com.surfilter.system.service;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.MsgMapper;
import com.surfilter.system.model.Msg;

/**
 * ClassName:MsgService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月25日  下午01:11:24<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class MsgService {

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
	public Page<Msg> getPageModel(Msg entity,RowBounds rowBounds){
		Page<Msg> page = new Page<Msg>();
		long total = msgMapper.count(entity);
		List<Msg> rows = msgMapper.list(entity,rowBounds);
		page = new Page<Msg>(total, rows);
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
	public List<Msg> getPageList(Msg entity){
		List<Msg> rows = msgMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Msg entity){
		msgMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Msg entity){
		return msgMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Msg getEntityById(long id){
		return msgMapper.getEntityById(id);
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
		
		return msgMapper.delEntity(id);
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
    	return msgMapper.delBatchEntity(ids);
    }
    
    
    /**
     * getMsgByUser:(根据用户获取消息). <br/>
     *
     * @author wangguohong
     * @param userid
     * @return
     * @since JDK 1.6
     */
    public List<Msg> getMsgByUser(String userId){
    	return msgMapper.getMsgByUser(userId);
    }
    
    /**
     * getMsgByTitle:(根据title获取最后一次插入的记录). <br/>
     * @author wangguohong
     * @param title
     * @return
     * @since JDK 1.6
     */
    public Msg getMsgByTitle(String title){
    	Msg msg = new Msg();
    	msg.setMsgTitle(title);
    	List<Msg> rows = msgMapper.list(msg);
    	if(rows==null || rows.size()==0){
    		return null;
    	}
    	Collections.sort(rows);
    	Msg msgtemp = rows.get(rows.size()-1);
    	return msgtemp;
    	
    }
}
