/**
 * Project Name:smcs<br>
 * File Name:MenuCtrl.java<br>
 * Package Name:<br>
 * Date:2015年06月09日  下午02:47:35<br>
 *
*/
package com.ql.basepro.system.ctrl;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.basepro.framework.BaseController;
import com.ql.basepro.framework.ExtJsObject;
import com.ql.basepro.framework.Page;
import com.ql.basepro.framework.TreeNode;
import com.ql.basepro.framework.utils.PageUtil;
import com.ql.basepro.system.model.Menu;
import com.ql.basepro.system.service.MenuService;


/**
 * ClassName:MenuCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月09日  下午02:47:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping(value="menu")
public class MenuCtrl extends BaseController{

	/**
	 * 注入menuService.
	 */
	@Autowired
	private MenuService menuService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(MenuCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/getPageModel")
	@ResponseBody
	public Page<Menu> getPageModel(Menu entity,Integer page,Integer rows){
	
		Page<Menu> pageModel = null;
		try {
			pageModel = menuService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/getEntity")
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		Menu entity = null;
		try {
			entity = menuService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/addEntity")
	@ResponseBody
	public ExtJsObject addEntity(Menu entity){
	
		try {
			menuService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/editEntity")
	@ResponseBody
	public ExtJsObject editEntity(Menu entity){
	
		try {
			menuService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author lenovo
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/delBatchEntity")
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			menuService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping("/delEntity")
	@ResponseBody
	public ExtJsObject delEntity(long id){
		ExtJsObject result = new ExtJsObject(true, "操作成功!");
		try {
			Menu entity = new Menu();
			entity.setParentMenu( id +"");
			List<Menu> list = menuService.getPageList(entity);
			if( list != null && list.size() > 0 ){
				result = new ExtJsObject(false, "不能对存在子节点的父菜单进行删除操作!");
			}else{
				menuService.delEntity(id);
			}
		} catch (Exception e) {
			result = new ExtJsObject(false, "操作失败!");
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	/**
	 * 加载菜单
	 * @param request http请求对象
	 * @return
	 */
	@RequestMapping("/getMenu")
	@ResponseBody
	public List<TreeNode> getMenu(HttpServletRequest request){
		List<TreeNode> result = null;
		try {
			result = menuService.getTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取
	 * @param request
	 * @return
	 */
	@RequestMapping("/getParentMenu")
	@ResponseBody
	public List<Menu> getParentMenu(HttpServletRequest request){
		List<Menu> result = null;
		try {
			result = menuService.getParentMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
