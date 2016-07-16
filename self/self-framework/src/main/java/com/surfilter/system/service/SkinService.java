/**
 * Project Name:smcs<br>
 * File Name:SkinService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年12月25日  上午10:27:00<br>
 *
*/
package com.surfilter.system.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.SkinMapper;
import com.surfilter.system.model.Skin;

/**
 * ClassName:SkinService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月25日  上午10:27:00<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SkinService {

	/**
	 *注入skinMapper
	 */
	@Autowired
	private SkinMapper skinMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Skin> getPageModel(Skin entity,RowBounds rowBounds){
		Page<Skin> page = new Page<Skin>();
		long total = skinMapper.count(entity);
		List<Skin> rows = skinMapper.list(entity,rowBounds);
		page = new Page<Skin>(total, rows);
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
	public List<Skin> getPageList(Skin entity){
		List<Skin> rows = skinMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Skin entity){
		skinMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Skin entity){
		return skinMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Skin getEntityById(long id){
		return skinMapper.getEntityById(id);
	}
	
	
	/**
	 * getEntityByUserId:(根据用户id获取皮肤). <br/>
	 *
	 * @author wangguohong
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Skin getEntityByUserId(long userId){
		String defaultSkinCode = FileUtil.getResouseValue("defaultSkin");
		Skin s = skinMapper.getEntityByUserId(userId);
		//如果没有找到对应的皮肤 则加载默认的皮肤
		if(null == s){
			s = new Skin();
			s.setSkinCode(defaultSkinCode);
		}
		return s;
	}
	
	
	/**
	 * setEntityByUserId:(设置用户皮肤). <br/>
	 *
	 * @author wangguohong
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.6
	 */
	public Skin setEntityByUserId(long userid,HttpServletRequest request,HttpServletResponse response){
		Skin s = skinMapper.getEntityByUserId(userid);
		String skinCode = request.getParameter("skinCode");
		if(null == s){
			s = new Skin();
			s.setUserId(userid);
			s.setSkinCode(skinCode);
			skinMapper.addEntity(s);
		}else{
			s.setSkinCode(skinCode);
			skinMapper.editEntity(s);
		}
		
		//设置皮肤到当前session中
		request.getSession().setAttribute("skinCode", s.getSkinCode());
		return s;
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
		
		return skinMapper.delEntity(id);
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
    	return skinMapper.delBatchEntity(ids);
    }
}
