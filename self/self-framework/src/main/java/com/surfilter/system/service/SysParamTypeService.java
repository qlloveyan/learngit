/**
 * Project Name:smcs<br>
 * File Name:SysParamTypeService.java<br>
 * Package Name:com.surfilter.smcs.otherservices.as.service<br>
 * Date:2014年01月02日  下午07:21:08<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.system.dao.SysParamTypeMapper;
import com.surfilter.system.model.SysParamType;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:SysParamTypeService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:08<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SysParamTypeService {

	/**
	 *注入sysParamTypeMapper
	 */
	@Autowired
	private SysParamTypeMapper sysParamTypeMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author dengqw
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysParamType> getPageModel(SysParamType entity,RowBounds rowBounds){
		Page<SysParamType> page = new Page<SysParamType>();
		long total = sysParamTypeMapper.count(entity);
		List<SysParamType> rows = sysParamTypeMapper.list(entity,rowBounds);
		page = new Page<SysParamType>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author dengqw
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<SysParamType> getPageList(SysParamType entity){
		List<SysParamType> rows = sysParamTypeMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysParamType entity){
		sysParamTypeMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysParamType entity){
		return sysParamTypeMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author dengqw
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysParamType getEntityById(long id){
		return sysParamTypeMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return sysParamTypeMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author dengqw
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return sysParamTypeMapper.delBatchEntity(ids);
    }
    /**
     * getParent:获取父类型
     * @author dengqw
     * @return
     * @since JDK 1.6
     */
    
	public List<SysParamType> getParent() {
		return sysParamTypeMapper.getParent();
	}
	 /**
     * getEntityByParent:通过父类型查找子类型
     * @author dengqw
     * @return
     * @since JDK 1.6
     */
	public List<SysParamType> getEntityByParent(Long parentId) {
		return sysParamTypeMapper.getEntityByParent(parentId);
	}
	/**
	 * 
	 * getTypeTree:获取类型树. 
	 *
	 * @author dengqw
	 * @return
	 * @since JDK 1.6
	 */
	public UnCheckTreeNodeBean getTypeTree() {
		UnCheckTreeNodeBean node=new UnCheckTreeNodeBean();
		List<SysParamType> lstParent= sysParamTypeMapper.getParent();
		List<UnCheckTreeNodeBean> nodeList = new ArrayList<UnCheckTreeNodeBean>();
		if(lstParent!=null && lstParent.size()>0){
			for(SysParamType paramType:lstParent){
				UnCheckTreeNodeBean treeNodeBean = new UnCheckTreeNodeBean();
				treeNodeBean.setId(paramType.getId().toString());
				treeNodeBean.setText(paramType.getTypeCn());
				treeNodeBean.setIconCls("icon-book_open");
				List<SysParamType> lstChildren= sysParamTypeMapper.getEntityByParent(paramType.getId());
				Map<String,Object> m = new HashMap<String,Object>();
				if(lstChildren!=null && lstChildren.size()>0){
					treeNodeBean.setLeaf(false);
					m.put("isLeaf", "false");
					m.put("id", paramType.getId());
					m.put("text", paramType.getTypeCn());
					m.put("childText", null);
					treeNodeBean.setAttributes(m);
					treeNodeBean.setIconCls("icon-folder_page");
					List<UnCheckTreeNodeBean> nodeChildren=new ArrayList<UnCheckTreeNodeBean>();
					Map<String,Object> attm = new HashMap<String,Object>();
					for(SysParamType child:lstChildren){
						UnCheckTreeNodeBean childNode = new UnCheckTreeNodeBean();
						childNode.setId(child.getId().toString());
						childNode.setText(child.getTypeCn());
						childNode.setIconCls("icon-book_open");
						treeNodeBean.setLeaf(true);
						attm.put("isLeaf", "true");
						attm.put("id", child.getId());
						attm.put("text", child.getTypeCn());
						attm.put("childText", child.getTypeCn());
						attm.put("businessType", child.getBusinessType());
						attm.put("typeEn", child.getTypeEn());
						attm.put("typeCn", child.getTypeCn());
						attm.put("parentName", paramType.getTypeCn());
						attm.put("parentId", child.getParentId());
						childNode.setAttributes(attm);
						nodeChildren.add(childNode);
					}
					treeNodeBean.setChildren(nodeChildren);
				}else{
					treeNodeBean.setLeaf(true);
					m.put("isLeaf", "true");
					m.put("id", paramType.getId());
					m.put("text", paramType.getTypeCn());
					m.put("childText", paramType.getTypeCn());
					treeNodeBean.setAttributes(m);
				}
				nodeList.add(treeNodeBean);
			}
			node.setChildren(nodeList);
		}
		return node;
	}
	/**
	 * 
	 * getId:获取Id
	 *
	 * @author dengqw
	 * @return
	 * @since JDK 1.6
	 */
	public Long getId(){
		return sysParamTypeMapper.getId();
	}
	/**
	 * isUnique:判断唯一性
	 * @author dengqw
	 * @param entity
	 * @return
	 * @since JDK 1.6
	 */
	public List<SysParamType> isUnique(SysParamType entity) {
		return sysParamTypeMapper.isUnique(entity);
	}
	
	
}
