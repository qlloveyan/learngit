/**
 * Project Name:smcs<br>
 * File Name:SysModuleService.java<br>
 * Package Name:com.smcs.framework.system.service<br>
 * Date:2013年09月18日  上午10:50:16<br>
 *
*/
package com.surfilter.system.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.CheckTreeNodeBean;
import com.surfilter.framework.web.bind.JsPath;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.system.SystemConstants;
import com.surfilter.system.dao.SysModuleMapper;
import com.surfilter.system.model.SysModule;

/**
 * ClassName:SysModuleService.java<br>
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
public class SysModuleService {

	/**
	 *注入sysModuleMapper.
	 */
	@Autowired
	private SysModuleMapper sysModuleMapper;
	
	/**
	 * 注入dictionaryService
	 */
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysModule> getPageModel(SysModule entity,RowBounds rowBounds,String type){
		Page<SysModule> page = new Page<SysModule>();
		long total = sysModuleMapper.count(entity);
		//如果是导出 则查询全部数据
		if(null == rowBounds || type.equals(SystemConstants.GET_PAGE_TYPE_EXPORT)){
			rowBounds = PageUtil.get(1,Integer.parseInt(String.valueOf(total)));
		}
		
		List<SysModule> rows = sysModuleMapper.list(entity,rowBounds);
		//设置系统模块类型名称
		for(SysModule s: rows){
			s.setSysModuleTypeName(dictionaryService.getDictionaryByKey(SystemConstants.DICTIONARY_TYPE_SYSMODULE, s.getModuleType()).getDescription());
		}
		page = new Page<SysModule>(total, rows);
		return page;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysModule entity){
		long parentId = Long.valueOf(entity.getParentId());
		Long maxSort = sysModuleMapper.getMaxSort(parentId);
		if(maxSort == null){
			entity.setModuleSort(0L);
			
			SysModule parentModule = sysModuleMapper.getEntityById(parentId);
			parentModule.setIsLeaf("0");
			sysModuleMapper.editEntity(parentModule);
		}else{
			entity.setModuleSort(maxSort+1);
		}
		sysModuleMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysModule entity){
		return sysModuleMapper.editEntity(entity);
	}
	
	/**
	 * moveModule:移动节点.
	 *
	 * @author tangbiao
	 * @param id 实体ID
	 * @param newParentId 移动后父节点的ID
	 * @param oldParentId 移动前父节点的ID
	 * @param index 移动后的位置
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	public void moveModule(int id, int newParentId, int oldParentId, int index){
		SysModule sysModule = sysModuleMapper.getEntityById(Long.valueOf(id));
		int minIndex = Integer.valueOf(sysModule.getModuleSort().toString());
		int maxIndex = index;
		if(newParentId-oldParentId == 0){
			// 在同一个父节点下发生移动
			if(minIndex < maxIndex){
				// 当要移动的节点的序号小于要移动到的目标序号，则下移 
				sysModuleMapper.downNode(oldParentId,minIndex,maxIndex);
			}else if(minIndex > maxIndex){
				// 当要移动的节点的序号大于要移动到的目标序号，则上移
				maxIndex = minIndex;
				minIndex = index;
				sysModuleMapper.upNode(oldParentId,minIndex,maxIndex);
			}
			sysModule.setModuleSort(Long.valueOf(index));
			sysModuleMapper.editEntity(sysModule);
		}else{
			// 在不同父节点下发生移动
			//1、相当于要移动的节点在原父节点下下移到最后再删除掉，因此要指定移动发生时节点所在的位置
			sysModuleMapper.downNode(oldParentId, minIndex, -1);
			//2、相当于要移动的节点在新父节点下上移到指定的位置，因此需要指定要移动到的位置
			sysModuleMapper.upNode(newParentId, maxIndex, -1);
			// 节点本身的序号设置成要移动到的目标序号
			sysModule.setParentId(String.valueOf(newParentId));
			sysModule.setModuleSort(Long.valueOf(index));
			sysModuleMapper.editEntity(sysModule);
		}
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysModule getEntityById(long id){
		return sysModuleMapper.getEntityById(id);
	}
	
	/**
	 * listModuleByUserAndType: 根据用户ID和模块类型查询模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @param moduleType	模块类型（菜单或者按钮）
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<SysModule> listModuleByUserAndType(long userId, String moduleType) {
		return null;
	}
	
	/**
	 * listModuleByUserAndType: 根据用户ID查询用户所有的功能列表. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<SysModule> listModuleByUser(long userId) {
		return null;
	}
	
	/**
	 * listRootModuleByUserAndType: 根据用户ID和模块类型查询根模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @param moduleType	模块类型（菜单或者按钮）
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<SysModule> listRootModuleByUserAndType(long userId, String moduleType) {
		return null;
	}
	
	/**
	 * listChildModuleByUserAndType: 根据用户ID，父模块ID和模块类型查询子模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param userId	用户ID
	 * @param moduleType	模块类型（菜单或者按钮）
	 * @param parentModuleId	父模块ID
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<UnCheckTreeNodeBean> listChildModuleByUserAndType(Long userId, String moduleType, String parentModuleId) {
		List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
		List<SysModule> sysModuleList = sysModuleMapper.listChildModuleByUserAndType(null, null, parentModuleId);
		for (SysModule sysModule : sysModuleList) {
			UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
			treeNodeBean.setId(sysModule.getId().toString());
			treeNodeBean.setText(sysModule.getModuleName());
			treeNodeBean.setLeaf("1".equals(sysModule.getIsLeaf()));
			treeNodeBeanList.add(treeNodeBean);
		}
		return treeNodeBeanList;
	}
	
	/**
	 * 
	 * listAllModuleByUserAndType:根据用户和模块类型和父节点查询模块并封装成非多选框树节点. <br/>
	 *
	 * @author tangbiao
	 * @param node
	 * @param userId
	 * @param moduleType
	 * @param parentModuleId
	 * @return
	 * @since JDK 1.6
	 */
	public UnCheckTreeNodeBean listAllModuleByUserAndType(UnCheckTreeNodeBean node, Long userId, String moduleType, String parentModuleId, boolean isId){
		List<SysModule> sysModuleList = sysModuleMapper.listChildModuleByUserAndType(userId, moduleType, parentModuleId);
		if(sysModuleList != null && sysModuleList.size() > 0){
			List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
			for (SysModule sysModule : sysModuleList) {
				// 将model转换为json中需要的实体
				UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
				if(isId){
					treeNodeBean.setId(sysModule.getId().toString());
				}else{
					treeNodeBean.setId(sysModule.getModuleCode());
				}
				treeNodeBean.setText(sysModule.getModuleName());
				
				List<SysModule> childModuleList = sysModuleMapper.listChildModuleByUserAndType(userId, moduleType, String.valueOf(sysModule.getId()));
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByUserAndType(treeNodeBean, userId, moduleType, String.valueOf(sysModule.getId()), isId);
					treeNodeBean.setLeaf(false);
				}else{
					treeNodeBean.setLeaf(true);
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("path", sysModule.getModuleFilePath()); //给树形菜单添加附加属性
					m.put("isLeaf", "true");
					m.put("moduleCode", sysModule.getModuleCode());
					treeNodeBean.setAttributes(m);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(treeNodeBeanList);
		}
		return node;
	}
	
	/**
	 * 
	 * listAllModuleByRoleId:查询模块并封装成多选框树节点. <br/>
	 *
	 * @author tangbiao
	 * @param node
	 * @param moduleIdList
	 * @param userId
	 * @param moduleType
	 * @param parentModuleId
	 * @return
	 * @since JDK 1.6
	 */
	public CheckTreeNodeBean listAllModuleByRoleId(CheckTreeNodeBean node, List<String> moduleIdList, Long userId, String moduleType, String parentModuleId){
		List<SysModule> sysModuleList = sysModuleMapper.listChildModuleByUserAndType(userId, moduleType, parentModuleId);
		if(sysModuleList != null && sysModuleList.size() > 0){
			List<CheckTreeNodeBean> treeNodeBeanList = new ArrayList<CheckTreeNodeBean>();
			for (SysModule sysModule : sysModuleList) {
				// 将model转换为json中需要的实体
				CheckTreeNodeBean treeNodeBean = new CheckTreeNodeBean();
				treeNodeBean.setId(sysModule.getId().toString());
				treeNodeBean.setText(sysModule.getModuleName());
				
				if(moduleIdList != null && moduleIdList.size()>0 && moduleIdList.contains(sysModule.getId().toString())){
					treeNodeBean.setChecked(true);
				}else{
					treeNodeBean.setChecked(false);
				}
				
				List<SysModule> childModuleList = sysModuleMapper.listChildModuleByUserAndType(userId, moduleType, String.valueOf(sysModule.getId()));
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByRoleId(treeNodeBean, moduleIdList, userId, moduleType, String.valueOf(sysModule.getId()));
					treeNodeBean.setLeaf(false);
				}else{
					treeNodeBean.setLeaf(true);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(treeNodeBeanList);
		}
		return node;
	}
	
	/**
	 * getRoleModuleByRoleKey获取对应的RoleId的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	public CheckTreeNodeBean getRoleModuleByRoleKey(CheckTreeNodeBean checkTreeNodeBean, long roleId) {
		List<String> moduleIdList = sysModuleMapper.getAllModuleByRoleId(roleId);
		return listAllModuleByRoleId(checkTreeNodeBean, moduleIdList, null, null, "0");
	}
	
	/**
	 * listAllJsPath: 查询所有模块js路径. <br/>
	 *
	 * @author tangbiao
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	public List<JsPath> listAllJsPath(){
		List<JsPath> jsPathList = new ArrayList<JsPath>();
		SysModule entity = new SysModule();
		Integer total = (int) sysModuleMapper.count(entity);
		// 查询出所有菜单
		List<SysModule> sysModuleList = sysModuleMapper.list(entity, PageUtil.get(0, total));
		if(sysModuleList != null && sysModuleList.size() > 0){
			for (SysModule sysModule : sysModuleList) {
				JsPath jsPath = new JsPath();
				jsPath.setId(sysModule.getModuleCode());
				jsPath.setJsPath(sysModule.getModuleFilePath());
				jsPathList.add(jsPath);
			}
		}
		return jsPathList;
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
		SysModule module = sysModuleMapper.getEntityById(id);
		if(module != null && module.getParentId() != null){
			// 删除节点后判断父节点是否还有子节点，如果没有子节点则将该父节点的isLeaf由0修改为1
			List<SysModule> list = sysModuleMapper.listChildModuleByUserAndType(null, null, module.getParentId());
			if(list != null && list.size() == 1){
				SysModule parentModule = sysModuleMapper.getEntityById(Long.valueOf(module.getParentId()));
				if(list.get(0).getId() - id == 0){
					parentModule.setIsLeaf("1");
					sysModuleMapper.editEntity(parentModule);
				}
			}
		}
		return sysModuleMapper.delEntity(id);
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
    	return sysModuleMapper.delBatchEntity(ids);
    }

    /**
	 * isHasModuleCode:判断模块代号是否重复.
	 *
	 * @author tangbiao
	 * @param moduleCode 模块代号
	 * @return 存在就返回true，不存在就返回false
	 * @since JDK 1.6
	 */
	public boolean isHasModuleCode(String moduleCode) {
		SysModule sysModule = new SysModule();
		sysModule.setModuleCode(moduleCode);
		return sysModuleMapper.count(sysModule) > 0;
	}
	
	/**
	 * 
	 * moveModuleEasyUI:(拖拽树菜单). <br/>
	 *
	 * @author wangguohong
	 * @param targetid 目标节点ID
	 * @param sourceid 源节点ID
	 * @param point 操作动作  "append","top","bottom"
	 * @since JDK 1.6
	 */
	public void moveModuleEasyUI(String targetid,String sourceid,String point){
		//目标模块
		SysModule sysModuleTarget = sysModuleMapper.getEntityById(Long.valueOf(targetid));
		//获取移动模块
		SysModule sysModuleSource = sysModuleMapper.getEntityById(Long.valueOf(sourceid));
		//目标统计模块
		List<SysModule> sysModuleTargetCompanionlist = sysModuleMapper.listChildModuleByParentModuleId(sysModuleTarget.getParentId());
		List<SysModule> temp = this.sortModule(sysModuleTargetCompanionlist, sysModuleSource, sysModuleTarget, point);
		for(SysModule s:temp){
			sysModuleMapper.editEntity(s);
		}
		
	}
	
	/**
	 * 
	 * sortModule:(排序模块)
	 *
	 * @author wangguohong
	 * @param listSysModule
	 * @param sourceSysModule
	 * @param targetSysModule
	 * @param op
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysModule> sortModule(List<SysModule> listSysModule,SysModule sourceSysModule,SysModule targetSysModule,String op){
		//如果是插入动作
		if(op.equals(SystemConstants.TREE_POINT_APPEND)){
			sourceSysModule.setParentId(targetSysModule.getId().toString());
			sourceSysModule.setModuleSort(0l);
			List<SysModule> listSysModuletemp = new ArrayList<SysModule>();
			listSysModuletemp.add(sourceSysModule);
			listSysModule = listSysModuletemp;
		}else{ //非插入动作
			sourceSysModule.setParentId(targetSysModule.getParentId());
			if(op.equals(SystemConstants.TREE_POINT_TOP)){
				sourceSysModule.setModuleSort(targetSysModule.getModuleSort());
				for(SysModule s:listSysModule){
					if(s.getModuleSort()>=targetSysModule.getModuleSort()){
						s.setModuleSort(s.getModuleSort()+1);
					}
				}
				
			}
			if(op.equals(SystemConstants.TREE_POINT_BOTTOM)){
				sourceSysModule.setModuleSort(targetSysModule.getModuleSort()+1);
				for(SysModule s:listSysModule){
					if(s.getModuleSort()>targetSysModule.getModuleSort()){
						s.setModuleSort(s.getModuleSort()+1);
					}
				}
			}
			//如果同级菜单不存在源菜单的话添加到同级菜单
			if(!listSysModule.contains(sourceSysModule)){
				listSysModule.add(sourceSysModule);
			}
		}
		
		
		return listSysModule;
	}
}
