/**
 * Project Name:smcs<br>
 * File Name:RoleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.Role;
import com.surfilter.system.service.RoleService;

/**
 * ClassName:RoleCtrl.java<br>
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
public class RoleCtrl extends BaseController {

	/**
	 * 注入roleService
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(RoleCtrl.class);
	
	
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
	public Page<Role> getPageModel(HttpServletRequest request,Role entity,Integer page,Integer rows){
	
		Page<Role> pages = null;
		try {
			String isSuperAdmin = request.getSession().getAttribute("isSuperAdmin").toString();
			if( "true".equals(isSuperAdmin)){
					entity.setCreateUserId(null);
			}else{
				String userid = request.getSession().getAttribute("userId").toString();
				entity.setCreateUserId(Long.parseLong(userid));
			}
			pages = roleService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Role entity = null;
		try {
			entity = roleService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
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
	public ExtJsObject addEntity(HttpServletRequest request,Role entity){
		String userid = request.getSession().getAttribute("userId").toString();
		entity.setCreateUserId(Long.parseLong(userid));
		try {
			roleService.addEntity(entity);
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
	public ExtJsObject editEntity(HttpServletRequest request,Role entity){
		
		String userid = request.getSession().getAttribute("userId").toString();
		entity.setCreateUserId(Long.parseLong(userid));
		try {
			roleService.editEntity(entity);
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
			if(roleService.isUsed(ids)){
				return renderObject(false, "该角色已经被用户使用，不能删除!", null);
			}
			roleService.delBatchEntity(ids);
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
			if(roleService.isUsed(id)){
				return renderObject(false, "该角色已经被用户使用，不能删除!", null);
			}
			roleService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * addRoleModule:添加角色权限信息.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param moduleIds 模块ID列表
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addRoleModule(Long roleId, Long[] moduleIds){
		try{
			roleService.addRoleModule(roleId, moduleIds);
		} catch (Exception e) {
			log.error("角色赋权失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * addRoleModule:添加角色权限信息.
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @param moduleIds 模块ID列表
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addRoleElement(Long roleId,Long funcId,Long[] elementIds){
		try{
			roleService.addRoleElement(roleId,funcId,elementIds);
		} catch (Exception e) {
			log.error("角色赋权失败",e);
			e.printStackTrace();
		}
		return renderSuccess();
	}
	
	/**
	 * isUsed:角色是否正在被使用(用户是都与相应的角色相关联).
	 *
	 * @author tangbiao
	 * @param roleId 角色ID
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isUsed(long roleId){
		return renderObject(roleService.isUsed(roleId));
	}
	
	/**
	 * isRoleIdsUsed:批量删除时验证角色是否正在被使用(用户是都与相应的角色相关联).
	 *
	 * @author tangbiao
	 * @param ids 角色ID列表
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isRoleIdsUsed(long[] ids){
		return renderObject(roleService.isUsed(ids));
	}
	
}
