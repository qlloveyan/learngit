/**
 * Project Name:smcs<br>
 * File Name:FuncModuleService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年11月06日  下午01:41:40<br>
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
import com.surfilter.system.dao.FuncModuleMapper;
import com.surfilter.system.model.FuncModule;

/**
 * ClassName:FuncModuleService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:40<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class FuncModuleService {

	/**
	 *注入funcModuleMapper
	 */
	@Autowired
	private FuncModuleMapper funcModuleMapper;
	
	@Autowired
	private SysSyleService sysSyleService;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<FuncModule> getPageModel(FuncModule entity,RowBounds rowBounds){
		Page<FuncModule> page = new Page<FuncModule>();
		long total = funcModuleMapper.count(entity);
		List<FuncModule> rows = funcModuleMapper.list(entity,rowBounds);
		for (FuncModule funcModule : rows) {
			FuncModule parentModule = funcModuleMapper.getEntityById(funcModule.getParentId());
			if(parentModule !=null){
				funcModule.setParentModuleName(parentModule.getFuncName());
			}
		}
		page = new Page<FuncModule>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<FuncModule> getPageList(FuncModule entity){
		List<FuncModule> rows = funcModuleMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(FuncModule entity){
		long parentId = Long.valueOf(entity.getParentId());
		
		Long maxSort = funcModuleMapper.getMaxSort(parentId);
		if(maxSort == null){
			entity.setFuncSort(0L);
			
			FuncModule parentModule = funcModuleMapper.getEntityById(parentId);
			parentModule.setIsLeaf("0");
			funcModuleMapper.editEntity(parentModule);
		}else{
			if(SystemConstants.FUNC_TYPE_URL.equals(entity.getFuncType())){
				entity.setFuncSort(maxSort+1);
			}
		}
		funcModuleMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(FuncModule entity){
		return funcModuleMapper.editEntity(entity);
	}
	
	/**
	 * moveModule:移动节点.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @param newParentId 移动后父节点的ID
	 * @param oldParentId 移动前父节点的ID
	 * @param index 移动后的位置
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	public void moveModule(int id, int newParentId, int oldParentId, int index){
		FuncModule funcModule = funcModuleMapper.getEntityById(Long.valueOf(id));
		int minIndex = Integer.valueOf(funcModule.getFuncSort().toString());
		int maxIndex = index;
		if(newParentId-oldParentId == 0){
			// 在同一个父节点下发生移动
			if(minIndex < maxIndex){
				// 当要移动的节点的序号小于要移动到的目标序号，则下移 
				funcModuleMapper.downNode(oldParentId,minIndex,maxIndex);
			}else if(minIndex > maxIndex){
				// 当要移动的节点的序号大于要移动到的目标序号，则上移
				maxIndex = minIndex;
				minIndex = index;
				funcModuleMapper.upNode(oldParentId,minIndex,maxIndex);
			}
			funcModule.setFuncSort(Long.valueOf(index));
			funcModuleMapper.editEntity(funcModule);
		}else{
			// 在不同父节点下发生移动
			//1、相当于要移动的节点在原父节点下下移到最后再删除掉，因此要指定移动发生时节点所在的位置
			funcModuleMapper.downNode(oldParentId, minIndex, -1);
			//2、相当于要移动的节点在新父节点下上移到指定的位置，因此需要指定要移动到的位置
			funcModuleMapper.upNode(newParentId, maxIndex, -1);
			// 节点本身的序号设置成要移动到的目标序号
			funcModule.setParentId(Long.valueOf(newParentId));
			funcModule.setFuncSort(Long.valueOf(index));
			funcModuleMapper.editEntity(funcModule);
		}
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
	public List<UnCheckTreeNodeBean> listChildModuleByUserAndType(Long userId, String moduleType, Long parentModuleId) {
		List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
		List<FuncModule> funcModuleList = funcModuleMapper.listChildModuleByUserAndType(null, null, parentModuleId);
		for (FuncModule funcModule : funcModuleList) {
			UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
			treeNodeBean.setId(funcModule.getId().toString());
			treeNodeBean.setText(funcModule.getFuncName());
			treeNodeBean.setLeaf("1".equals(funcModule.getIsLeaf()));
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
	public UnCheckTreeNodeBean listAllModuleByUserAndType(UnCheckTreeNodeBean node, Long userId, String moduleType, Long parentModuleId, boolean isId){
//		List<FuncModule> funcModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, moduleType, parentModuleId);
		List<FuncModule> funcModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, moduleType, parentModuleId); //wgh
		if(funcModuleList != null && funcModuleList.size() > 0){
			List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
			for (FuncModule funcModule : funcModuleList) {
				// 将model转换为json中需要的实体
				UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
				if(isId){
					treeNodeBean.setId(funcModule.getId().toString());
				}else{
					treeNodeBean.setId(funcModule.getFuncCode());
				}
				treeNodeBean.setText(funcModule.getFuncName());
//				List<FuncModule> childModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, moduleType, funcModule.getId());
                List<FuncModule> childModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, moduleType, funcModule.getId());                                                   
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByUserAndType(treeNodeBean, userId, moduleType, funcModule.getId(), isId);
					treeNodeBean.setLeaf(false);
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("path", funcModule.getUrl()); //给树形菜单添加附加属性
					m.put("isLeaf", "false");
					m.put("funcCode", funcModule.getFuncCode());
					m.put("parentId", funcModule.getParentId());
					if(userId!=null){
						m.put("pic", sysSyleService.getPicbyUserid(userId, funcModule.getFuncCode()));
					}
					treeNodeBean.setAttributes(m);
				}else{
					treeNodeBean.setLeaf(true);
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("path", funcModule.getUrl()); //给树形菜单添加附加属性
					m.put("isLeaf", "true");
					m.put("funcCode", funcModule.getFuncCode());
					m.put("parentId", funcModule.getParentId());
					if(userId!=null){
						m.put("pic", sysSyleService.getPicbyUserid(userId, funcModule.getFuncCode()));
					}
					treeNodeBean.setAttributes(m);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(treeNodeBeanList);
		}
		return node;
	}
	
	public UnCheckTreeNodeBean listAllRootModuleByUserAndType(UnCheckTreeNodeBean node, Long userId){
		List<FuncModule> funcModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, "2", null);
		if(funcModuleList != null && funcModuleList.size() > 0){
			List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
			for (FuncModule funcModule : funcModuleList) {
				// 将model转换为json中需要的实体
				UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
				treeNodeBean.setId(funcModule.getId().toString());
				treeNodeBean.setText(funcModule.getFuncName());
				
				List<FuncModule> childModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, "0", funcModule.getId());
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByUserAndType(treeNodeBean, userId, "0", funcModule.getId(), true);
					treeNodeBean.setLeaf(false);
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("path", funcModule.getUrl()); //给树形菜单添加附加属性
					m.put("isLeaf", "false");
					m.put("funcCode", funcModule.getFuncCode());
					m.put("parentId", funcModule.getParentId());
					treeNodeBean.setAttributes(m);
				}else{
					treeNodeBean.setLeaf(true);
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("path", funcModule.getUrl()); //给树形菜单添加附加属性
					m.put("isLeaf", "true");
					m.put("funcCode", funcModule.getFuncCode());
					m.put("parentId", funcModule.getParentId());
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
	public CheckTreeNodeBean listAllModuleByRoleId(CheckTreeNodeBean node, List<String> moduleIdList, Long userId, String moduleType, Long parentModuleId){
//		List<FuncModule> funcModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, moduleType, parentModuleId);
		List<FuncModule> funcModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, moduleType, parentModuleId);
		
		if(funcModuleList != null && funcModuleList.size() > 0){
			List<CheckTreeNodeBean> treeNodeBeanList = new ArrayList<CheckTreeNodeBean>();
			
			for (FuncModule funcModule : funcModuleList) {
				// 将model转换为json中需要的实体
				CheckTreeNodeBean treeNodeBean = new CheckTreeNodeBean();
				treeNodeBean.setId(funcModule.getId().toString());
				treeNodeBean.setText(funcModule.getFuncName());
				
				if(moduleIdList != null && moduleIdList.size()>0 && moduleIdList.contains(funcModule.getId().toString())){
					treeNodeBean.setChecked(true);
				}else{
					treeNodeBean.setChecked(false);
				}
				
//				List<FuncModule> childModuleList = funcModuleMapper.listChildModuleByUserAndType(userId, moduleType, funcModule.getId());
				List<FuncModule> childModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, moduleType, funcModule.getId());
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByRoleId(treeNodeBean, moduleIdList, userId, moduleType, funcModule.getId());
					treeNodeBean.setLeaf(false);
				}else{
					treeNodeBean.setLeaf(true);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(operaCheckTreeNodeBean(treeNodeBeanList));
		}
		return node;
	}
	
	public CheckTreeNodeBean listAllRootModuleByRoleId(CheckTreeNodeBean node, List<String> moduleIdList, Long userId){
//		List<FuncModule> funcModuleList = funcModuleMapper.listChildModuleByUserAndType(null, "2", null);
		List<FuncModule> funcModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, "2", null);
		
		if(funcModuleList != null && funcModuleList.size() > 0){
			List<CheckTreeNodeBean> treeNodeBeanList = new ArrayList<CheckTreeNodeBean>();
			
			for (FuncModule funcModule : funcModuleList) {
				// 将model转换为json中需要的实体
				CheckTreeNodeBean treeNodeBean = new CheckTreeNodeBean();
				treeNodeBean.setId(funcModule.getId().toString());
				treeNodeBean.setText(funcModule.getFuncName());
				
				if(moduleIdList != null && moduleIdList.size()>0 && moduleIdList.contains(funcModule.getId().toString())){
					treeNodeBean.setChecked(true);
				}else{
					treeNodeBean.setChecked(false);
				}
				
//				List<FuncModule> childModuleList = funcModuleMapper.listChildModuleByUserAndType(null, null, funcModule.getId());
				List<FuncModule> childModuleList = funcModuleMapper.listNoChildModuleByUserAndType(userId, null, funcModule.getId());
				
				if(childModuleList != null && childModuleList.size() > 0){
					listAllModuleByRoleId(treeNodeBean, moduleIdList, userId, null, funcModule.getId());
					treeNodeBean.setLeaf(false);
				}else{
					treeNodeBean.setLeaf(true);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(operaCheckTreeNodeBean(treeNodeBeanList));
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
	public CheckTreeNodeBean getRoleModuleByRoleKey(CheckTreeNodeBean checkTreeNodeBean, long roleId,Long userid) {
		List<String> moduleIdList = funcModuleMapper.getAllModuleByRoleId(roleId);
		return listAllRootModuleByRoleId(checkTreeNodeBean, moduleIdList, userid);
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
		FuncModule entity = new FuncModule();
		Integer total = (int) funcModuleMapper.count(entity);
		// 查询出所有菜单
		List<FuncModule> sysModuleList = funcModuleMapper.list(entity, PageUtil.get(0, total));
		if(sysModuleList != null && sysModuleList.size() > 0){
			for (FuncModule funcModule : sysModuleList) {
				JsPath jsPath = new JsPath();
				jsPath.setId(funcModule.getFuncCode());
				jsPath.setJsPath(funcModule.getFuncFilePath());
				jsPathList.add(jsPath);
			}
		}
		return jsPathList;
	}
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public FuncModule getEntityById(long id){
		return funcModuleMapper.getEntityById(id);
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
		FuncModule module = funcModuleMapper.getEntityById(id);
		if(module != null && module.getParentId() != null){
			// 删除节点后判断父节点是否还有子节点，如果没有子节点则将该父节点的isLeaf由0修改为1
			List<FuncModule> list = funcModuleMapper.listChildModuleByUserAndType(null, null, module.getParentId());
			if(list != null && list.size() == 1){
				FuncModule parentModule = funcModuleMapper.getEntityById(Long.valueOf(module.getParentId()));
				if(list.get(0).getId() - id == 0){
					parentModule.setIsLeaf("1");
					funcModuleMapper.editEntity(parentModule);
				}
			}
		}
		//首先删除功能角色关系表的相关数据
		funcModuleMapper.delFuncModuleRoleByFuncId(id);
		return funcModuleMapper.delEntity(id);
	}
	
	 /**
		 * isHasModuleCode:判断模块代号是否重复.
		 *
		 * @author tangbiao
		 * @param moduleCode 模块代号
		 * @return 存在就返回true，不存在就返回false
		 * @since JDK 1.6
		 */
		public boolean isHasFuncCode(String funcCode) {
			FuncModule funcModule = new FuncModule();
			funcModule.setFuncCode(funcCode);
			return funcModuleMapper.count(funcModule) > 0;
		}
		
	/**
	 * listFuncModuleByCode:根据模块编码查询模块列表. <br/>
	 *
	 * @author Tuyan
	 * @param funcCode	模块编码
	 * @return
	 * @since JDK 1.6
	 */
	public List<FuncModule> listFuncModuleByCode(String funcCode) {
		return funcModuleMapper.listFuncModuleByCode(funcCode);
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
			FuncModule funcTarget = funcModuleMapper.getEntityById(Long.valueOf(targetid));
			//获取移动模块
			FuncModule funcSource = funcModuleMapper.getEntityById(Long.valueOf(sourceid));
			//目标统计模块
			List<FuncModule> funcTargetCompanionlist = funcModuleMapper.listChildModuleByParentId(funcTarget.getParentId());
			List<FuncModule> temp = this.sortModule(funcTargetCompanionlist, funcSource, funcTarget, point);
			for(FuncModule s:temp){
				funcModuleMapper.editEntity(s);
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
		public List<FuncModule> sortModule(List<FuncModule> listFuncModule,FuncModule sourceFuncModule,FuncModule targetFuncModule,String op){
			//如果是插入动作
			if(op.equals(SystemConstants.TREE_POINT_APPEND)){
				sourceFuncModule.setParentId(targetFuncModule.getId());
				sourceFuncModule.setFuncSort(0l);
				List<FuncModule> listFuncModuletemp = new ArrayList<FuncModule>();
				listFuncModuletemp.add(sourceFuncModule);
				listFuncModule = listFuncModuletemp;
			}else{ //非插入动作
				sourceFuncModule.setParentId(targetFuncModule.getParentId());
				if(op.equals(SystemConstants.TREE_POINT_TOP)){
					sourceFuncModule.setFuncSort(targetFuncModule.getFuncSort());
					for(FuncModule s:listFuncModule){
						if(s.getFuncSort()>=targetFuncModule.getFuncSort()){
							s.setFuncSort(s.getFuncSort()+1);
						}
					}
					
				}
				if(op.equals(SystemConstants.TREE_POINT_BOTTOM)){
					sourceFuncModule.setFuncSort(targetFuncModule.getFuncSort()+1);
					for(FuncModule s:listFuncModule){
						if(s.getFuncSort()>targetFuncModule.getFuncSort()){
							s.setFuncSort(s.getFuncSort()+1);
						}
					}
				}
				//如果同级菜单不存在源菜单的话添加到同级菜单
				if(!listFuncModule.contains(sourceFuncModule)){
					listFuncModule.add(sourceFuncModule);
				}
			}
			
			
			return listFuncModule;
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
    	
    	for (long funcId : ids) {
    		FuncModule module = funcModuleMapper.getEntityById(funcId);
    		if(module != null && module.getParentId() != null){
    			// 删除节点后判断父节点是否还有子节点，如果没有子节点则将该父节点的isLeaf由0修改为1
    			List<FuncModule> list = funcModuleMapper.listChildModuleByUserAndType(null, null, module.getParentId());
    			if(list != null && list.size() == 1){
    				FuncModule parentModule = funcModuleMapper.getEntityById(Long.valueOf(module.getParentId()));
    				if(list.get(0).getId() - funcId == 0){
    					parentModule.setIsLeaf("1");
    					funcModuleMapper.editEntity(parentModule);
    				}
    			}
    		}
    		//首先删除功能角色关系表的相关数据
    		funcModuleMapper.delFuncModuleRoleByFuncId(funcId);
		}
    	return funcModuleMapper.delBatchEntity(ids);
    }
    
    /**
     * 
     * getFuncUrlByUserId:通过用户ID获得该用户所有的功能URL. <br/>
     *
     * @author hongcheng
     * @param userId 用户ID
     * @return
     * @since JDK 1.6
     */
    public List<String> getFuncUrlByUserId(Long userId){
    	
    	List<String> primissions = new ArrayList<String>();
    	if(userId==null){
    		return primissions;
    	}
    	List<FuncModule> list = funcModuleMapper.getFuncModuleByUserId(userId);
    	for (FuncModule funcModule : list) {
    		
    		if(SystemConstants.FUNC_TYPE_MENU.equals(funcModule.getFuncType())){
    			//菜单
    			primissions.add("/" + funcModule.getUrl());
    		}else{
    			//按钮
    			primissions.addAll(getUrlsByStr(funcModule.getUrl(),funcModule.getParams()));
    		}
		}
    	return primissions;
    }
    
    /**
     * 
     * getFunCode:查询主节点菜单图标
     *
     * @author qifan
     * @param 无
     * @return
     * @since JDK 1.6
     */
    public List<FuncModule> getFunCode(){
    	List<FuncModule> list = funcModuleMapper.getFunCode();
    	return list;
    }
    
    /**
     * 
    * @Title: getNoFuncUrlByUserId 
    * @Description: TODO(获取禁用功能) 
    * @param userId
    * @return List<String>    返回类型 
    * @throws 
    * @author wangguohong
     */
    public List<String> getNoFuncUrlByUserId(Long userId){
    	
    	List<String> primissions = new ArrayList<String>();
    	if(userId==null){
    		return primissions;
    	}
    	List<FuncModule> list = funcModuleMapper.getFuncModuleByUserId(userId);
    	for (FuncModule funcModule : list) {
    		
    		if(SystemConstants.FUNC_TYPE_MENU.equals(funcModule.getFuncType())){
    			//菜单
    			primissions.add("/" + funcModule.getUrl());
    		}else{
    			//按钮
    			primissions.addAll(getUrlsByStr(funcModule.getUrl(),funcModule.getParams()));
    		}
		}
    	return primissions;
    }
	/**
	 * 
	 * getSortCount:根据序号查询是否存在. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @param funcSort 序号
	 * @return
	 * @since JDK 1.6
	 */
	public Long getSortCount(long parentId,long funcSort){
		return funcModuleMapper.getSortCount(parentId, funcSort);
	}
	
	/**
	 * 
	 * getSortCountEdit:根据序号查询是否存在. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @param funcSort 序号
	 * @param id id
	 * @return
	 * @since JDK 1.6
	 */
	public Long getSortCountEdit(long parentId,long funcSort,long id){
		return funcModuleMapper.getSortCountEdit(parentId, funcSort,id);
	}
	
	/**
	 * 
	 * getMenuCountByParentId:通过父ID查询菜单数目. <br/>
	 *
	 * @author hongcheng
	 * @param parentId 父ID
	 * @return 菜单数目
	 * @since JDK 1.6
	 */
	public long getMenuCountByParentId(Long parentId){
		return funcModuleMapper.getMenuCountByParentId(parentId);
	}
	
	/**
     * getTreeGridAllDic: 获取所有的子节点,已树形关系存储
     * getTreeGridAllDic:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author hongcheng
     * @param dic
     * @return
     * @since JDK 1.6
     */
	public FuncModule getTreeGridAllFuncModule(FuncModule func, long parentId) {
		// 组装查询条件，使用父节点查询所有的子节点
		FuncModule funcModule = new FuncModule();
		funcModule.setParentId(parentId);
		List<FuncModule> funcs = funcModuleMapper.list(funcModule);
		
		if(funcs != null && funcs.size()>0){
			for (FuncModule f : funcs) {
				if(f.getParentId() != null){
					FuncModule funcTmp = new FuncModule();
					funcTmp.setParentId(f.getId());
					List<FuncModule> fTmp = funcModuleMapper.list(funcTmp);
					if(fTmp != null && fTmp.size()>0){
						getTreeGridAllFuncModule(f, f.getId());
					}
				}else{
					break;
				}
			}
			func.setChildren(funcs);
		}
		return func;
	}
    /**
	 * 
	 * getUrlsByStr:解析字符串. <br/>
	 *
	 * @author hongcheng
	 * @param prefix 字符串
	 * @param params 参数
	 * @return url集合
	 * @since JDK 1.6
	 */
	private List<String> getUrlsByStr(String prefix,String params){
		
		List<String> urls = new ArrayList<String>();
		String suffix = ".do";
		
		
		if(prefix.indexOf("redirectHomePage.do") >0){
			
			//jsp页面
			//类似/system/redirect/redirectHomePage.do?path=system/user
			///system/redirect/redirectHomePage.do?path=/system/user_detail&id=34&_=1384761773230
			
			//1./system/redirect/redirectHomePage.do?path=system/user *
			//2./system/redirect/redirectHomePage.do?path=system/user user_detail
			//3./system/redirect/redirectHomePage.do?path=system/user user_add,user_detail
			if(params.equals("*")){
				urls.add("/" +prefix + params);
				return urls;
			}
			if(params.indexOf(",")==-1){
				prefix = prefix.substring(0,prefix.lastIndexOf("/")+1);
				urls.add("/" +prefix + params);
				return urls;
			}
			//存在“，”分隔符
			String[] funcs = params.split(",");
			for (String string : funcs) {
				
				if(string.indexOf("_")!=-1){
					string = string.substring(string.indexOf("_"));
				}
				urls.add("/" + prefix + string);
			}
		}else{
			//后台ajax方法
			
			if(params.indexOf(",")==-1){
				urls.add("/" +prefix + "/" + params);
				return urls;
			}
			//存在“，”分隔符
			String[] funcs = params.split(",");
			for (String string : funcs) {
				urls.add("/" + prefix + "/" + string + suffix);
			}
		}
		return urls;
	}
	
	 /**
	 * 
	 * operaCheckTreeNodeBean:处理节点. <br/>
	 *
	 * @author hongcheng
	 * @param treeNodeBeanList 节点
	 * @return
	 * @since JDK 1.6
	 */
	private List<CheckTreeNodeBean> operaCheckTreeNodeBean(List<CheckTreeNodeBean> treeNodeBeanList){
		
		List<CheckTreeNodeBean> childNodeBeanList = new ArrayList<CheckTreeNodeBean>();
		for (CheckTreeNodeBean checkTreeNodeBean : treeNodeBeanList) {
			
			childNodeBeanList = checkTreeNodeBean.getChildren();
			while(childNodeBeanList !=null && childNodeBeanList.size()>0){
				for (CheckTreeNodeBean childNode : childNodeBeanList) {
					
					if(!childNode.isChecked()){
						checkTreeNodeBean.setChecked(false);
					}
					childNodeBeanList = childNode.getChildren();
				}
			}
			
		}
		return treeNodeBeanList;
	}

	/**
	 * getCurrentPath 查询当前所在模块及其所有的父模块及从根节点到当前路径的节点；
	 *
	 * @author tangbiao
	 * @param funcCode 角色ID
	 * @since JDK 1.6
	 */
	public List<FuncModule> getCurrentPath(String funcCode) {
		List<FuncModule> currentPaths = new ArrayList<FuncModule>();
		
		FuncModule funcModule = new FuncModule();
		funcModule.setFuncCode(funcCode);
		funcModule.setFuncType("0");
		List<FuncModule> currentModules = funcModuleMapper.listNotLike(funcModule);
		if(currentModules != null && currentModules.size()>0){
			currentPaths.add(currentModules.get(0));
			currentPaths = getAllParentModule(currentModules, currentModules.get(0).getParentId());
		}
		
		return currentPaths;
	}
	
	/**
	 * 
	 * getAllParentModule:(递归方法，查询所有的父节点菜单数据). <br/>
	 *
	 * @author tangbiao
	 * @param funcModules
	 * @param parentId
	 * @return
	 * @since JDK 1.6
	 */
	public List<FuncModule> getAllParentModule(List<FuncModule> funcModules, long parentId){
		FuncModule funcModule = new FuncModule();
		funcModule.setId(parentId);
		List<FuncModule> currentModules = funcModuleMapper.list(funcModule);
		if(currentModules != null && currentModules.size()>0){
			funcModule = currentModules.get(0);
			funcModules.add(funcModule);
			
			funcModules = getAllParentModule(funcModules, funcModule.getParentId());
		}
		return funcModules;
	}
	
	/** 
	* @Title: getBreadMenu 
	* @Description: TODO(获取面包屑导航菜单集合) 
	* @param id
	* @return List<FuncModule>    返回类型 
	* @throws 
	* @author wangguohong
	*/
	public List<FuncModule> getBreadMenu(List<FuncModule> funcModules,Long id){
		FuncModule funcModule = getEntityById(id);
		if(funcModule!=null){
			funcModules.add(funcModule);
			funcModules = getAllParentModule(funcModules,funcModule.getParentId());
		}
		return funcModules;
	}
	
	
}
