/**
 * Project Name:smcs<br>
 * File Name:ElementCtrl.java<br>
 * Package Name:<br>
 * Date:2013年11月06日  下午01:41:41<br>
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
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.Element;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ElementService;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.FuncModuleService;

/**
 * ClassName:ElementCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月06日  下午01:41:41<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class ElementCtrl extends BaseController implements ExcelOperate<Element>{

	/**
	 * 注入elementService.
	 */
	@Autowired
	private ElementService elementService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<Element> excelService;
	
	/**
	 * 功能模块服务
	 */
	@Autowired
	private FuncModuleService funcModuleService;
	
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(ElementCtrl.class);
	
	
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
	public Page<Element> getPageModel(Element entity,Integer page,Integer rows){
	
		Page<Element> pageModel = null;
		try {
			pageModel = elementService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Element entity = null;
		try {
			entity = elementService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	
	/**
	 * getElementByFuncId:通过功能ID查询页面元素信息.
	 *
	 * @author hongcheng
	 * @param funcId 功能ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getElementByFuncId(long funcId){
	
		List<Element> list = null;
		try {
			list = elementService.getElementByFuncId(funcId);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(list);
	} 
	
	/**
	 * getElementByFuncId:通过功能ID查询页面元素信息.
	 *
	 * @author hongcheng
	 * @param request 请求
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isSuperAdmin(HttpServletRequest request){
	
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		return renderObject(userAuth.isSuperAdmin());
	} 
	
	/**
	 * getRoleModuleByRoleKey获取对应的RoleId的模块权限.
	 *
	 * @author hongcheng
	 * @param roleId 角色ID
	 * @param funcId 功能ID
	 * @return 对应的RoleId的模块权限
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<CheckTreeNodeBean> getElementByRoleIdAndFuncId(Long funcId,Long roleId){
		CheckTreeNodeBean checkTreeNodeBean = new CheckTreeNodeBean();
		checkTreeNodeBean = elementService.getElementByRoleIdAndFuncId(checkTreeNodeBean,funcId,roleId);
		List<CheckTreeNodeBean> list = new ArrayList<CheckTreeNodeBean>();
		return checkTreeNodeBean.getChildren()==null?list:checkTreeNodeBean.getChildren();
	}
	
	/**
	 * 
	 * getAllElementIds:获取所有页面元素. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<String> getAllElementCode(){
		return elementService.getAllElementCode();
	}
	
	/**
	 * 
	 * getElementPermissions:获取页面元素权限. <br/>
	 *
	 * @author hongcheng
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<String> getElementPermissions(HttpServletRequest request){
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		return userAuth.getElementPermissions();
	}
	
	/**
	 * 导出excel.
	 * @author hongcheng
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(Element entity,HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("user");
  		List<Element> list = null;
		
		try {
			list =  elementService.getPageList(entity);
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
	public ExtJsObject addEntity(Element entity){
	
		try {
			
			List<Element> list =  elementService.getElementByElementCode(entity.getElementCode());
			if(list !=null && list.size() >0){
				return new ExtJsObject(false, "元素编码已经存在!");
			}
			//元素,需要判断是否为叶子菜单，只能对叶子菜单添加资源
			long count = funcModuleService.getMenuCountByParentId(entity.getFuncId());
			if(count >0){
				return renderObject(false,"只能对叶子菜单添加元素!",null);
			}
			elementService.addEntity(entity);
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
	public ExtJsObject editEntity(Element entity){
	
		try {
			//元素,需要判断是否为叶子菜单，只能对叶子菜单添加资源
			long count = funcModuleService.getMenuCountByParentId(entity.getFuncId());
			if(count >0){
				return renderObject(false,"只能对叶子菜单添加元素!",null);
			}
			elementService.editEntity(entity);
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
			elementService.delBatchEntity(ids);
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
			elementService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
}
