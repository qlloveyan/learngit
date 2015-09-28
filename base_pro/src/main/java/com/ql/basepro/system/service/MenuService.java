/**
 * Project Name:smcs<br>
 * File Name:MenuService.java<br>
 * Package Name:com.ql.basepro.system.service<br>
 * Date:2015年06月09日  下午02:47:35<br>
 *
*/
package com.ql.basepro.system.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.basepro.framework.Page;
import com.ql.basepro.framework.TreeNode;
import com.ql.basepro.system.dao.MenuMapper;
import com.ql.basepro.system.model.Menu;

/**
 * ClassName:MenuService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月09日  下午02:47:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MenuService {

	/**
	 *注入menuMapper
	 */
	@Autowired
	private MenuMapper menuMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<Menu> getPageModel(Menu entity,RowBounds rowBounds) throws Exception{
		Page<Menu> page = new Page<Menu>();
		long total = menuMapper.count(entity);
		List<Menu> rows = menuMapper.list(entity,rowBounds);
		page = new Page<Menu>(total, rows);
		return page;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author lenovo
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<Menu> getPageList(Menu entity) throws Exception{
		List<Menu> rows = menuMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(Menu entity) throws Exception{
		menuMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(Menu entity) throws Exception{
		return menuMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author lenovo
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public Menu getEntityById(long id) throws Exception{
		return menuMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id) throws Exception{
		
		return menuMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author lenovo
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids) throws Exception{
    	return menuMapper.delBatchEntity(ids);
    }
    
    /**
	 * 拿到的menus是按照  showIndex 的升序排列
	 * @param menus
	 * @return
	 */
	public List<TreeNode> getTree() throws Exception{
		//获取一级菜单
		List<Menu> menus = menuMapper.getChildByParent(null);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();//一级树总树
		for(Menu menu : menus){//循环
			TreeNode treeNode = new TreeNode();
			treeNode.setText(menu.getName());
			treeNode.setId(menu.getPageCmpUrl());
			treeNode.setExpanded(true);
			treeNode.setIconCls(menu.getIconCls());
			treeNode.setLeaf(false);
			
			List<Menu> childMenu = menuMapper.getChildByParent( menu.getId() );
			List<TreeNode> treeNodes1 = new ArrayList<TreeNode>();//二级树总树
			for(Menu temp : childMenu){//循环
				TreeNode treeNodeTemp = new TreeNode();
				treeNodeTemp.setText(temp.getName());
				treeNodeTemp.setId(temp.getPageCmpUrl());
				treeNodeTemp.setExpanded(false);
				treeNodeTemp.setIconCls(temp.getIconCls());
				treeNodeTemp.setLeaf(true);
				
				treeNodes1.add(treeNodeTemp);
			}
			treeNode.setChildren(treeNodes1);//设置第一级树的子树
			treeNodes.add(treeNode);
		}
		return treeNodes;
		
	}

	public List<Menu> getParentMenu() {
		return menuMapper.getChildByParent( null );
	}
}
