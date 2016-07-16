/**
 * Project Name:smcs<br>
 * File Name:SysUnitService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年10月25日  下午07:41:42<br>
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
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.system.dao.SysUnitMapper;
import com.surfilter.system.model.AreaCode;
import com.surfilter.system.model.SysUnit;

/**
 * ClassName:SysUnitService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年10月25日  下午07:41:42<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SysUnitService {

	/**
	 *注入sysUnitMapper
	 */
	@Autowired
	private SysUnitMapper sysUnitMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysUnit> getPageModel(SysUnit entity,RowBounds rowBounds){
		Page<SysUnit> page = new Page<SysUnit>();
		long total = sysUnitMapper.count(entity);
		List<SysUnit> rows = sysUnitMapper.list(entity,rowBounds);
		page = new Page<SysUnit>(total, rows);
		return page;
	}
	
	
	/**
	 * listByNativeId:(根据nativeId获取单位). <br/>
	 *
	 * @author wangguohong
	 * @param nativeId
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysUnit> listByNativeId(String nativeId,String prentId){
		return sysUnitMapper.listByNativeId(nativeId,prentId);
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
	public UnCheckTreeNodeBean listChildSysUnitByLevelAndParentId(UnCheckTreeNodeBean node,String unitLevel, String  parentId,boolean isId){
		List<SysUnit> sysUnitList = sysUnitMapper.listChildSysUnitByLevelAndParentId(unitLevel,parentId);
		if(sysUnitList != null && sysUnitList.size() > 0){
			List<UnCheckTreeNodeBean> treeNodeBeanList = new ArrayList<UnCheckTreeNodeBean>();
			for (SysUnit sysUnit : sysUnitList) {
				// 将model转换为json中需要的实体
				UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
				if(isId){
					treeNodeBean.setId(sysUnit.getId().toString());
				}
				treeNodeBean.setText(sysUnit.getUnitName());
				
				List<SysUnit> childUnitList = sysUnitMapper.listChildSysUnitByLevelAndParentId(unitLevel,sysUnit.getId().toString());
				
				Map<String,Object> m = new HashMap<String,Object>();
				
				
				if(childUnitList != null && childUnitList.size() > 0){
					listChildSysUnitByLevelAndParentId(treeNodeBean,unitLevel, sysUnit.getId().toString(), isId);
					treeNodeBean.setLeaf(false);
					m.put("isLeaf", "false");
					m.put("id", sysUnit.getId());
					m.put("parentId", sysUnit.getParentId());
					m.put("unitLevel", sysUnit.getUnitLevel());
					m.put("nativeId", sysUnit.getNativeId());
					treeNodeBean.setAttributes(m);
				}else{
					treeNodeBean.setLeaf(true);
					m.put("isLeaf", "true");
					m.put("id", sysUnit.getId());
					m.put("parentId", sysUnit.getParentId());
					m.put("unitLevel", sysUnit.getUnitLevel());
					m.put("nativeId", sysUnit.getNativeId());
					treeNodeBean.setAttributes(m);
				}
				treeNodeBeanList.add(treeNodeBean);
			}
			node.setChildren(treeNodeBeanList);
		}
		return node;
	}
	
	
	
	/**
	 * 通过单位代码查询单位信息
	 * getEntityByUnitCodeAndParentId:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author hongcheng
	 * @param unitCode 单位编码
	 * @param parentId 父ID
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysUnit> getEntityByParentId(String  parentId){
		
		return sysUnitMapper.getEntityByParentId(parentId);
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysUnit entity){
		sysUnitMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysUnit entity){
		return sysUnitMapper.editEntity(entity);
	}
	
	
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysUnit getEntityById(long id){
		return sysUnitMapper.getEntityById(id);
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
		
		return sysUnitMapper.delEntity(id);
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
    	return sysUnitMapper.delBatchEntity(ids);
    }
    
	/**
	 * list:根据单位类型条件查询实体信息.
	 *
	 * @author zhangjw
	 * @param unitType 单位类型
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<SysUnit> getListByUnitType(String unitType){
		return sysUnitMapper.getListByUnitType(unitType);
	}
	
	/**
	 * getAreaByType:查询所有市的信息
	 *
	 * @author ql
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	public List<AreaCode> getAreaByType(String provinceCode){
		return sysUnitMapper.getAreaByType(provinceCode);
	}

}
