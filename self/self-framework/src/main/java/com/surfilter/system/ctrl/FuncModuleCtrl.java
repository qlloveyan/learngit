/**
 * Project Name:smcs<br>
 * File Name:FuncModuleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年11月06日  下午01:41:40<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
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
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.FuncModuleService;

/**
 * ClassName:FuncModuleCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:40<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class FuncModuleCtrl extends BaseController implements ExcelOperate<FuncModule>{

	/**
	 * 注入funcModuleService.
	 */
	@Autowired
	private FuncModuleService funcModuleService;
	
	
	/**
	 * 注入ExcelService.
	 */
	@Autowired
	private ExcelService<FuncModule> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(FuncModuleCtrl.class);
	
	/**
	 * uiMenus: 界面显示的菜单.
	 * @since JDK 1.6
	 */
	private UnCheckTreeNodeBean uiMenus;
	
	
	public UnCheckTreeNodeBean getUiMenus() {
		return uiMenus;
	}

	public void setUiMenus(UnCheckTreeNodeBean uiMenus) {
		this.uiMenus = uiMenus;
	}

	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<FuncModule> getPageModel(FuncModule entity,Integer page,Integer rows){
	
		Page<FuncModule> pageModel = null;
		try {
			pageModel = funcModuleService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return pageModel;
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
	
		FuncModule entity = null;
		try {
			entity = funcModuleService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author hongcheng
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(FuncModule entity,HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("user");
  		List<FuncModule> list = null;
		
		try {
			list =  funcModuleService.getPageList(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		ModelAndView mv = excelService.exportExcel(entity,list, entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		return mv;
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
	public ExtJsObject addEntity(FuncModule entity){
	
		try {
			if(SystemConstants.FUNC_TYPE_MENU.equals(entity.getFuncType())){
				//如果是菜单，则需要判断序号不能重复
				long count = funcModuleService.getSortCount(entity.getParentId(), entity.getFuncSort());
				if(count >0){
					return renderObject(false,"菜单号不能重复!",null);
				}
			}else{
				//新增url资源,需要判断是否为叶子菜单，只能对叶子菜单添加资源
				long count = funcModuleService.getMenuCountByParentId(entity.getParentId());
				if(count >0){
					return renderObject(false,"只能对叶子菜单添加资源!",null);
				}
			}
			funcModuleService.addEntity(entity);
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
	public ExtJsObject editEntity(FuncModule entity){
	
		try {
			
			if(SystemConstants.FUNC_TYPE_MENU.equals(entity.getFuncType())){
				//如果是菜单，则需要判断序号不能重复
				long count = funcModuleService.getSortCountEdit(entity.getParentId(), entity.getFuncSort(),entity.getId());
				if(count >0){
					return renderObject(false,"菜单序号不能重复!",null);
				}
			}else{
				//新增url资源,需要判断是否为叶子菜单，只能对叶子菜单添加资源
				long count = funcModuleService.getMenuCountByParentId(entity.getParentId());
				if(count >0){
					return renderObject(false,"只能对叶子菜单添加资源!",null);
				}
			}
			funcModuleService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
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
			funcModuleService.delBatchEntity(ids);
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
			funcModuleService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
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
		funcModuleService.moveModule(Integer.valueOf(id), Integer.valueOf(newParentId), Integer.valueOf(oldParentId), Integer.valueOf(index));
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
		funcModuleService.moveModuleEasyUI(targetid, sourceid, point);
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
		nodeBean = funcModuleService.listAllModuleByUserAndType(nodeBean, null, null, Long.valueOf(0), true);
		List<UnCheckTreeNodeBean> list = new ArrayList<UnCheckTreeNodeBean>();
		//添加一个顶级菜单
		return nodeBean.getChildren()==null?list:nodeBean.getChildren();
	}
	
	/**
	 * getTreeGridAllDic: 获取所有的节点，以层级关系展示节点,同步树，一次取出所有的节点
	 * getTreeGridAllDic:(这里用一句话描述这个方法的作用). <br/>
	 * @author tangbiao
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<FuncModule> getTreeGridAllFuncModule(FuncModule entity){
		FuncModule func = new FuncModule();
		func = funcModuleService.getTreeGridAllFuncModule(func,entity.getParentId());
		return func.getChildren()==null?new ArrayList<FuncModule>():func.getChildren();
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
	 * listAllModule: 查询所有模块列表. <br/>
	 *
	 * @author tangbiao
	 * @param request	用户请求
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listModuleTree(HttpServletRequest request){
		
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		UnCheckTreeNodeBean uiMenus = new UnCheckTreeNodeBean();
		
		Long loginUserId = null;
		if(!userAuth.isSuperAdmin()){
			loginUserId = userAuth.getSubject().getId();
		}
		String platform = FileUtil.getResouseValue(FrameworkGlobal.SYSTEM_PLATFORM);
		uiMenus = funcModuleService.listAllRootModuleByUserAndType(uiMenus, null);
		
		UnCheckTreeNodeBean uiMenusQy = new UnCheckTreeNodeBean();
		List<UnCheckTreeNodeBean> listQy  =  new ArrayList<UnCheckTreeNodeBean>();
		
		//qf add初始化管局侧和企业侧的菜单
		if(platform.equalsIgnoreCase("gj")) {
			this.setUiMenus(uiMenus);//管局所有信息
		} 
		else if(platform.equalsIgnoreCase("gj")) {//企业信息
			for (UnCheckTreeNodeBean a : uiMenus.getChildren()) {
				if (! a.getId().equals("-1")) {
					listQy.add(a);
				}
			}
			uiMenusQy.setChildren(listQy);
			this.setUiMenus(uiMenusQy);
		}
		//userAuth.setUIMenus(uiMenus);
		return uiMenus.getChildren();
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
	    //UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN); //没判断是不是企业或者管局	//qf del
		List<UnCheckTreeNodeBean> listtemp = new ArrayList<UnCheckTreeNodeBean>();
		//List<UnCheckTreeNodeBean> list = userAuth.getUIMenus().getChildren();  //qf del
		List<UnCheckTreeNodeBean> list = this.getUiMenus().getChildren(); //qf add
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
		Map<String,Object> m = new HashMap<String,Object>();
		if(!ishasTopmenu){
			topmenu.setId(SystemConstants.NOT_HAS_PARENT_MODULE_ID);
			topmenu.setText(SystemConstants.NOT_HAS_PARENT_MODULE);
			if(list !=null && list.size()>0){
				topmenu.setLeaf(false);
			}else{
				topmenu.setLeaf(true);
			}
			m.put("parentId", 0);
			topmenu.setAttributes(m);
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
		return funcModuleService.listAllJsPath();
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
	public List<CheckTreeNodeBean> getRoleModuleByRoleKey(HttpServletRequest request,Long roleId){
		String userid = this.getSessionObj(request, "userId").toString();
		CheckTreeNodeBean checkTreeNodeBean = new CheckTreeNodeBean();
		checkTreeNodeBean = funcModuleService.getRoleModuleByRoleKey(checkTreeNodeBean, roleId,Long.parseLong(userid));
		
		List<CheckTreeNodeBean> list = new ArrayList<CheckTreeNodeBean>();
		return checkTreeNodeBean.getChildren()==null?list:checkTreeNodeBean.getChildren();
	}
	
	/**
	 * isHasModuleCode获取对应的RoleId的模块权限.
	 *
	 * @author tangbiao
	 * @param funcCode 角色ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isHasModuleCode(String funcCode){
		return renderObject(funcModuleService.isHasFuncCode(funcCode));
	}
	
	/**
	 * getCurrentPath 查询当前所在模块及其所有的父模块及从根节点到当前路径的节点；
	 *
	 * @author tangbiao
	 * @param funcCode 角色ID
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<FuncModule> getCurrentPath(String funcModule){
		List<FuncModule> funcModuleList = new ArrayList<FuncModule>();
		funcModuleList = funcModuleService.getCurrentPath(funcModule);
		
		return funcModuleList;
	}
	
	
	/**
	 * getCurrentPath 查询当前所在模块及其所有的父模块及从根节点到当前路径的节点；
	 *
	 * @author tangbiao
	 * @param funcCode 角色ID
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<String> getFuncUrlByUserId(HttpServletRequest request){
		Object o = request.getSession().getAttribute("userId");
		String userId = (o == null) ? null : o.toString();
		List<String> urlList = new ArrayList<String>();
		if(!StringUtils.isEmpty(userId)){
			urlList = funcModuleService.getFuncUrlByUserId(Long.valueOf(userId));
		}
		return urlList;
	}
	
	
	/** 
	* @Title: getBreadMenu 
	* @Description: TODO(获取面包屑导航) 
	* @param request
	* @param id
	* @return List<FuncModule>    返回类型 
	* @throws 
	* @author wangguohong
	*/
	@RequestMapping
	@ResponseBody
	public List<FuncModule> getBreadMenu(HttpServletRequest request,long id){
		List<FuncModule> funcModules = new ArrayList<FuncModule>();
		return funcModuleService.getBreadMenu(funcModules, id);
	}
}
