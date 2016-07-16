/**
 * Project Name:smcs<br>
 * File Name:SysModuleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.CheckTreeNodeBean;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.bind.JsPath;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.SystemConstants;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.SysModule;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.SysModuleService;

/**
 * ClassName:SysModuleCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SysModuleCtrl extends BaseController implements ExcelOperate<SysModule>{

	/**
	 * 注入sysModuleService
	 */
	@Autowired
	private SysModuleService sysModuleService;
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<SysModule> excelService;
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(SysModuleCtrl.class);
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 查询实体
	 * @param start 起始行数
	 * @param limit 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<SysModule> getPageModel(SysModule entity,Integer page,Integer rows){
	
		Page<SysModule> pages = null;
		try {
			pages = sysModuleService.getPageModel(entity,PageUtil.get(page,rows),SystemConstants.GET_PAGE_TYPE_SEARCH);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pages;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		SysModule entity = null;
		try {
			entity = sysModuleService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * sortModule:排序.
	 *
	 * @author tangbiao
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject moveModule(String id, String newParentId, String oldParentId, String index){
		sysModuleService.moveModule(Integer.valueOf(id), Integer.valueOf(newParentId), Integer.valueOf(oldParentId), Integer.valueOf(index));
		return renderSuccess();
	}
	
	/**
	 * 
	 * moveModuleEasyUI:拖拽树保存顺序. <br/>
	 * @author wangguohong
	 * @param targetid 目标节点ID
	 * @param sourceid 源节点ID
	 * @param point 操作 "append","top","bottom"
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject moveModuleEasyUI(String targetid,String sourceid,String point){
		sysModuleService.moveModuleEasyUI(targetid, sourceid, point);
		return renderSuccess();
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(SysModule entity){
		try {
			sysModuleService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(SysModule entity){
	
		try {
			sysModuleService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
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
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listChildModuleByUserAndType(Long userId, String moduleType, String parentId) {
		UnCheckTreeNodeBean nodeBean = new UnCheckTreeNodeBean();
		nodeBean = sysModuleService.listAllModuleByUserAndType(nodeBean, null, null, "0", true);
		//添加一个顶级菜单
		return nodeBean.getChildren();
	}
	
	/**
	 * listAllModule: 查询所有模块列表. <br/>
	 *
	 * @author tangbiao
	 * @param request	用户请求
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listAllModule(HttpServletRequest request){
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		return userAuth.getUIMenus().getChildren();
	}
	
	/**
	 * listAllModule: 查询所有模块列表用于combox <br/>
	 *
	 * @author wangguohong
	 * @param request	用户请求
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listAllModuleToCombox(HttpServletRequest request){
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		List<UnCheckTreeNodeBean> listtemp = new ArrayList<UnCheckTreeNodeBean>();
		List<UnCheckTreeNodeBean> list = userAuth.getUIMenus().getChildren();
		for(UnCheckTreeNodeBean b : list ){
			listtemp.add(b);
		}
		boolean ishasTopmenu = false;
		UnCheckTreeNodeBean topmenu = new UnCheckTreeNodeBean();
		//如果菜单里没有添加“无”这个菜单节点则添加一个
		for(UnCheckTreeNodeBean temp : listtemp){
			if(temp.getId().equals(SystemConstants.NOT_HAS_PARENT_MODULE_ID)){
				ishasTopmenu = true;
			}
		}
		if(!ishasTopmenu){
			topmenu.setId(SystemConstants.NOT_HAS_PARENT_MODULE_ID);
			topmenu.setText(SystemConstants.NOT_HAS_PARENT_MODULE);
			listtemp.add(topmenu);
		}
		return listtemp;
	}
	
	/**
	 * listAllJsPath: 查询所有模块js路径. <br/>
	 *
	 * @author tangbiao
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<JsPath> listAllJsPath(){
		return sysModuleService.listAllJsPath();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author hongcheng
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			sysModuleService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			sysModuleService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * getRoleModuleByRoleKey获取对应的RoleId的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<CheckTreeNodeBean> getRoleModuleByRoleKey(Long roleId){
		CheckTreeNodeBean checkTreeNodeBean = new CheckTreeNodeBean();
		checkTreeNodeBean = sysModuleService.getRoleModuleByRoleKey(checkTreeNodeBean, roleId);
		return checkTreeNodeBean.getChildren();
	}
	
	/**
	 * isHasModuleCode获取对应的RoleId的模块权限.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isHasModuleCode(String moduleCode){
		return renderObject(sysModuleService.isHasModuleCode(moduleCode));
	}
	
	/**
	 * 
	 * exportExcel:导出数据. <br/>
	 *
	 * @author wangguohong
	 * @param entity
	 * @param properties 属性集合
	 * @param titles 属性名称集合
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public ModelAndView exportExcel(SysModule entity,HttpServletRequest request){
		Page<SysModule> pages = null;
		User user = (User)request.getSession().getAttribute("user");
		try {
			pages = sysModuleService.getPageModel(entity,null,SystemConstants.GET_PAGE_TYPE_EXPORT);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		ModelAndView mv = excelService.exportExcel(entity,pages.getRows(), entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		return mv;
	}
		

}
