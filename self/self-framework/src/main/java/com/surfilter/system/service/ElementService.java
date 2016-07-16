/**
 * Project Name:smcs<br>
 * File Name:ElementService.java<br>
 * Package Name:com.surfilter.system.service<br>
 * Date:2013年11月06日  下午01:41:41<br>
 *
*/
package com.surfilter.system.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.web.bind.CheckTreeNodeBean;
import com.surfilter.system.dao.ElementMapper;
import com.surfilter.system.model.Element;

/**
 * ClassName:ElementService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:41<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class ElementService {

	/**
	 *注入elementMapper
	 */
	@Autowired
	private ElementMapper elementMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Element> getPageModel(Element entity,RowBounds rowBounds){
		Page<Element> page = new Page<Element>();
		long total = elementMapper.count(entity);
		List<Element> rows = elementMapper.list(entity,rowBounds);
		page = new Page<Element>(total, rows);
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
	public List<Element> getPageList(Element entity){
		List<Element> rows = elementMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Element entity){
		
		elementMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Element entity){
		return elementMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Element getEntityById(long id){
		return elementMapper.getEntityById(id);
	}
	
	/**
	 * 
	 * getElementByElementCode:通过元素编码获得元素. <br/>
	 *
	 * @author hongcheng
	 * @param elementCode 元素编码
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<Element> getElementByElementCode(String  elementCode){
		return elementMapper.getElementByElementCode(elementCode);
	}
	
	/**
	 * 
	 * getElementByFuncId:通过功能ID查询页面元素信息. <br/>
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<Element> getElementByFuncId(long funcId){
		return elementMapper.getElementByFuncId(funcId);
	}
	
	/**
	 * 
	 * getElementCodeByUserId:通过用户ID获得该用户的页面元素权限. <br/>
	 *
	 * @author hongcheng
	 * @param userId 用户ID
	 * @return 页面元素权限
	 * @since JDK 1.6
	 */
	public List<String> getElementCodeByUserId(Long userId){
		
		if(userId ==null){
			return new ArrayList<String>();
		}
		return elementMapper.getElementCodeByUserId(userId);
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
		//删除元素角色关系表相关的数据
		elementMapper.delElementRoleByElementId(id);
		return elementMapper.delEntity(id);
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
    	//先删除元素与角色关系表的数据
    	for (long elementId : ids) {
    		elementMapper.delElementRoleByElementId(elementId);
		}
    	return elementMapper.delBatchEntity(ids);
    }
    
    /**
	 * 
	 * getAllElementIds:查询所有页面元素ID. <br/>
	 *
	 * @author hongcheng
	 * @return 页面元素信息
	 * @since JDK 1.6
	 */
	public List<String> getAllElementCode(){
		return elementMapper.getAllElementCode();
	}
    /**
	 * getRoleModuleByRoleKey获取对应的RoleId的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	public CheckTreeNodeBean getElementByRoleIdAndFuncId(CheckTreeNodeBean checkTreeNodeBean, long funcId,long roleId) {
		//该功能下的所有元素
		List<Element> elementList = elementMapper.getElementByFuncId(funcId);
		//该角色在该功能下禁止的元素
		List<String> elementIdList = elementMapper.getElementIdsByFuncIdAndRoleId(funcId, roleId);
		
		if(elementList!=null && elementList.size()>0){
			List<CheckTreeNodeBean> treeNodeBeanList = new ArrayList<CheckTreeNodeBean>();
			for (Element element : elementList) {
				// 将model转换为json中需要的实体
				CheckTreeNodeBean treeNodeBean = new CheckTreeNodeBean();
				treeNodeBean.setId(element.getId().toString());
				treeNodeBean.setText(element.getElementName());
				
				if(elementIdList !=null && elementIdList.contains(element.getId().toString())){
					treeNodeBean.setChecked(true);
				}else{
					treeNodeBean.setChecked(false);
				}
				treeNodeBean.setLeaf(true);
				treeNodeBeanList.add(treeNodeBean);
			}
			checkTreeNodeBean.setChildren(treeNodeBeanList);
		}
		return checkTreeNodeBean;
	}
}
