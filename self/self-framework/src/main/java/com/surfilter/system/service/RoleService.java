/**
 * Project Name:smcs<br>
 * File Name:RoleService.java<br>
 * Package Name:com.smcs.framework.system.service<br>
 * Date:2013年09月18日  上午10:50:16<br>
 *
*/
package com.surfilter.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.utils.StringUtils;
import com.surfilter.system.dao.ElementMapper;
import com.surfilter.system.dao.FuncModuleMapper;
import com.surfilter.system.dao.RoleMapper;
import com.surfilter.system.dao.UserMapper;
import com.surfilter.system.model.Role;
import com.surfilter.system.model.RoleRelation;

/**
 * ClassName:RoleService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月18日  上午10:50:16<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class RoleService {

	/**
	 *注入roleMapper
	 */
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 注入elementMapper
	 */
	@Autowired
	private ElementMapper elementMapper;
	
	/**
	 * 注入funcModuleMapper
	 */
	@Autowired
	private FuncModuleMapper funcModuleMapper;
	
	/**
	 * 注入userMapper
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Role> getPageModel(Role entity,RowBounds rowBounds){
		Page<Role> page = new Page<Role>();
		long total = roleMapper.count(entity);
		List<Role> rows = roleMapper.list(entity,rowBounds);
		page = new Page<Role>(total, rows);
		return page;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Role entity){
		roleMapper.addEntity(entity);
		if(!StringUtils.isEmpty(entity.getParentIds()) && entity.getId()!=null){
			String parentids = entity.getParentIds();
			String[] arrparentids = parentids.split(",");
			if(arrparentids!=null && arrparentids.length>0){
				for(String s : arrparentids){
					long roleParentId = Long.parseLong(s);
					RoleRelation rr = new RoleRelation();
					rr.setRoleId(entity.getId());
					rr.setParentRoleId(roleParentId);
					roleMapper.addRoleRelation(rr);
				}
			}
		}
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Role entity){
		if(StringUtils.isEmpty(entity.getParentIds())){
			entity.setParentIds("");
		}
		int i =  roleMapper.editEntity(entity);
		roleMapper.delRoleRelation(entity.getId());//先删除 后增加
		if(!StringUtils.isEmpty(entity.getParentIds()) && entity.getId()!=null){
			String parentids = entity.getParentIds();
			String[] arrparentids = parentids.split(",");
			if(arrparentids!=null && arrparentids.length>0){
				for(String s : arrparentids){
					if(!StringUtils.isEmpty(s)){
						
						long roleParentId = Long.parseLong(s);
						RoleRelation rr = new RoleRelation();
						rr.setRoleId(entity.getId());
						rr.setParentRoleId(roleParentId);
						roleMapper.addRoleRelation(rr);
					}
				}
			}
		}
		return i;
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Role getEntityById(long id){
		return roleMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		//删除元素角色对应关系
		elementMapper.delElementRoleByRoleId(id);
		//删除资源角色对应关系
		funcModuleMapper.delFuncModuleRoleByRoleId(id);
		//删除用户角色对应关系
		userMapper.delUserRoleByRoleId(id);
		roleMapper.delRoleRelation(id);
		return roleMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author hongcheng
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	
    	for (long id : ids) {
    		//删除元素角色对应关系
    		elementMapper.delElementRoleByRoleId(id);
    		//删除资源角色对应关系
    		funcModuleMapper.delFuncModuleRoleByRoleId(id);
    		//删除用户角色对应关系
    		userMapper.delUserRoleByRoleId(id);
		}
    	return roleMapper.delBatchEntity(ids);
    }

    /**
	 * addRoleModule:添加角色权限信息.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param moduleIds 模块ID列表
	 * @since JDK 1.6
	 */
	public void addRoleModule(Long roleId, Long[] moduleIds) {
		roleMapper.delModuleByRoleId(roleId);
		if(moduleIds != null){
			for (Long moduleId : moduleIds) {
				if(moduleId != null){
					roleMapper.addRoleModule(roleId, moduleId);
				}
			}
		}
	}
	
	/**
	 * addRoleModule:添加角色权限信息.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param elementIds 元素ID列表
	 * @param funcId 功能ID
	 * @since JDK 1.6
	 */
	public void addRoleElement(Long roleId,Long funcId,Long[] elementIds) {
		
		if(roleId==null || funcId==null){
			return;
		}
		List<String> idList =  elementMapper.getElementIdsByFuncIdAndRoleId(funcId, roleId);
		
		for (String elementId : idList) {
			//新增前先删除
			roleMapper.delElementByRoleIdAndElementId(roleId, Long.parseLong(elementId),funcId);
		}
		
		//获得该功能下所有的元素ID,进行遍历。如果不存在于elementIds，则说明未被选中。
		List<Long> elementIdList = elementMapper.getElementIdsByFuncId(funcId);
		
		List<Long> checkedIds = null;
		if(elementIds==null){
			//全部未选中
			checkedIds = new ArrayList<Long>();
		}else{
			checkedIds = Arrays.asList(elementIds);
		}
		
		for (Long elementId : elementIdList) {
			
			if(checkedIds !=null && checkedIds.contains(elementId)){
				roleMapper.addRoleElement(roleId, elementId,funcId);
			}
		}
		
	}

	/**
	 * isUsed:角色是否正在被使用(用户是都与相应的角色相关联).
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 正在被使用就返回true，没有被使用就返回false
	 * @since JDK 1.6
	 */
	public boolean isUsed(long roleId) {
		return roleMapper.countRoleIsUsed(roleId) > 0;
	}

	/**
	 * isUsed:角色是否正在被使用(用户是都与相应的角色相关联).
	 *
	 * @author tangbiao
	 * @param roleIds 角色ID
	 * @return 正在被使用就返回true，没有被使用就返回false
	 * @since JDK 1.6
	 */
	public boolean isUsed(long[] roleIds) {
		boolean flag = false;
		for (long roleId : roleIds) {
			if(roleMapper.countRoleIsUsed(roleId) > 0){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/** 
	* @Title: checkUserRole 
	* @Description: TODO(检查用户是否分配了角色) 
	* @param userId
	* @return boolean     
	* @throws 
	* @author wangguohong
	*/
	public boolean checkUserRole(Long userId){
		long count = roleMapper.countRoleByUserid(userId);
		return count>0?true:false;
	}
	
}
