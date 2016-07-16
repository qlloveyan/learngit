/**
 * Project Name:smcs<br>
 * File Name:UserCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.Role;
import com.surfilter.system.model.Safety;
import com.surfilter.system.model.SysUnit;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.SafetyService;
import com.surfilter.system.service.UserService;

/**
 * ClassName:UserCtrl.java<br>
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
public class UserCtrl extends BaseController   implements ExcelOperate<User>{
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<User> excelService;
	/**
	 * 注入userService
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 注入SafetyService
	 */
	@Autowired
	private SafetyService safetyService;
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(UserCtrl.class);
	
	
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
	public Page<User> getPageModel(HttpServletRequest request,User entity,Integer page,Integer rows){
		Page<User> pages = null;
		try {
			String isSuperAdmin = request.getSession().getAttribute("isSuperAdmin").toString();
			if("true".equals(isSuperAdmin)){
				entity.setCreateUserId(null);
			}else{
				String userid = request.getSession().getAttribute("userId").toString();
				entity.setCreateUserId(Long.parseLong(userid));
			}
			
			pages = userService.getPageModel(entity,PageUtil.get(page, rows));
		} catch (Exception e) {
			e.printStackTrace();
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
	
		User entity = null;
		try {
			entity = userService.getEntityById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	
	/**
	 * getEntityByCode:判断登录名称是否重复.
	 *
	 * @author tangbiao
	 * @param userCode 登录名称
	 * @return 存在登录名称返回 true，不存在则返回false
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isExistsCode(String userCode,String userName){
		return renderObject(userService.isExistsCode(userCode,userName));
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
	public ExtJsObject addEntity(HttpServletRequest request, User entity){
		UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		User user = userAuth.getSubject();
		try {
			entity.setUserState(0L); // 默认用户状态为启用
			entity.setCreateTime(new Date());
			entity.setCreateUserId(user.getId());
			//添加安全认证信息
//			Safety safety = entity.getSafety();
			userService.addEntity(entity);
			
			//获取插入数据库的user
//			User usertempc = new User();
//			usertempc.setUserName(entity.getUserName());
//			User usertemp = userService.getPageList(usertempc).get(0);
//			safety.setUserId(usertemp.getId());
//			safetyService.addEntity(safety);
		} catch (Exception e) {
			e.printStackTrace();
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
	public ExtJsObject editEntity(User entity){
	
		try {
//			boolean flag = true;
//			Long safetyId = entity.getSafety().getId();
//			Safety safety = null;
//			if(null == safetyId){
//				flag = false;
//				safety = new Safety();
//			}else{
//				flag = true;
//				safety = safetyService.getEntityById(entity.getSafety().getId());
//			}
//			safety.setUkeyCode(entity.getSafety().getUkeyCode());
//			safety.setUkeyId(entity.getSafety().getUkeyId());
//			if(flag){
//				safetyService.editEntity(safety);
//			}else{
//				safety.setUserId(entity.getId());
//				safetyService.addEntity(safety);
//			}
		
			userService.editEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
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
			userService.delBatchEntity(ids);
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
			userService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * authRole:分配角色.
	 *
	 * @author tangbiao
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject authRole(long userId, long[] roleId){
		try {
			userService.authRole(userId, roleId);
		} catch (Exception e) {
			log.error("分配角色失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * getSelectRoleByUserId:获取用户已分配的角色.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @return 获取用户已分配的角色.
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Role> getSelectRoleByUserId(HttpServletRequest request,long userId){
		String currentUserid = request.getSession().getAttribute("userId").toString();
		return userService.getSelectRoleByUserId(userId,Long.parseLong(currentUserid));
	}
	
	/**
	 * reUserState:改变用户状态.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param userState 用户状态
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject reUserState(HttpServletRequest request,long userId, long userState){
		try {
			UserAuthorization userAuthorization = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
			User user = userAuthorization.getSubject();
			if(user.getId().longValue()==userId){
				return renderObject(false, "不能对自己的状态进行操作!", null);
			}
			userService.reUserState(userId, userState);
		} catch (Exception e) {
			log.error("启用/停用用户失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * getUserFromSession: 从session中获取登录者的信息.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param newPw 新密码
	 * @param oldPw 旧密码
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public User getUserFromSession(HttpServletRequest request){
		UserAuthorization user = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		return user.getSubject();
	}
	
	/**
	 * updatePassWord: 更新用户密码.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param newPw 新密码
	 * @param oldPw 旧密码
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody	
	public ExtJsObject updatePassWord(HttpServletRequest request,String newPw, String oldPw){
		UserAuthorization userAuthorization = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
		User u = userAuthorization.getSubject();
		if(!userService.checkOldPw(u.getId(), oldPw)){ // 
			return renderObject(false, "密码输入错误", null);
		}else{
			User user = new User();
			user.setId(u.getId());
			user.setUserPass(newPw);
			userService.editEntity(user);
			
			Safety safety = safetyService.getEntityByUserId(u.getId()); 
			//更新用户安全信息中的密码修改时间
			safety.setPassUpdateTime( new Date() );
			safetyService.editEntity(safety);
			
			//将session中的用户密码状态置为1
			request.getSession().setAttribute("pasUpdate", 1);
		}
		return renderSuccess();
	}
	
	/**
	 * 
	 * loginFail:登录失败. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public String loginFail(Model model,HttpServletRequest res){
		model.addAttribute("message",getSessionObj(res, "message"));
		return "/login";
	}
	
	/**
	 * 
	 * caLoginFail:登录失败. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public String caLoginFail(Model model,HttpServletRequest res){
		model.addAttribute("message",getSessionObj(res, "message"));
		model.addAttribute("errorType",getSessionObj(res, "errorType"));
		return "/errorMsg";
	}
	
	@RequestMapping
	public String tpLoginFail(Model model,HttpServletRequest res){
		model.addAttribute("message",getSessionObj(res, "message"));
		model.addAttribute("errorType",getSessionObj(res, "errorType"));
		return "/errorMsg";
	}
	/**
	 * 
	 * loginOut:退出登录. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public String loginOut(Model model){
		model.addAttribute("message","您已成功退出系统!");
		model.addAttribute("errorType","0");
		return "/errorMsg";
	}
	
	@RequestMapping
	@ResponseBody
	public ExtJsObject resetPwdUser(long userId){
		try {
			
			User user = new User();
			user.setId(userId);
			String pass = FileUtil.getResouseValue("defaultPass");
			user.setUserPass(pass);
			userService.editEntity(user);
			
			//重置密码的同时将用户的登陆锁定状态修改为 0 
			Safety safety = safetyService.getEntityByUserId(userId);
			if( safety != null){
				safety.setPassErrorCount( 0L );
				safetyService.editEntity( safety );
			}else{
				//初始化实体信息,插入用户登陆信息
				Safety temp = new Safety();
				temp.setUserId( userId );
				temp.setPassErrorCount(0L);
				
				safetyService.addEntity(temp);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return renderObject(false, "操作失败!", null);
		}
		return renderObject(true, "操作成功!", null);
	}

	@RequestMapping
	@Override
	public ModelAndView exportExcel(final User entity, final HttpServletRequest request) {
		final User user = (User)request.getSession().getAttribute("user");
		Page<User> pages = null;
		ModelAndView mv = null;
		if(entity.isIsback()){
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Page<User> pagestemp = null;
					ModelAndView mvtemp = null;
					int batchCount = FileUtil.calculateBatch(Integer.parseInt(entity.getRecordsum()), Integer.parseInt(entity.getBatchsize()));
					for(int i =1;i<=batchCount;i++){
						pagestemp = userService.getPageModel(entity,PageUtil.get(i, Integer.parseInt(entity.getBatchsize())));
						entity.setBatchnum(String.valueOf(i));
						if(i==batchCount){
							entity.setBatchEnd(String.valueOf(true));
						}else{
							entity.setBatchEnd(String.valueOf(false));
						}
						mvtemp= excelService.exportExcel(entity,pagestemp.getRows(), entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
					}
				}
			});
			t.start();
			return null;
		}else{
			
			try {
				pages = userService.getPageModel(entity,PageUtil.get(Integer.parseInt(entity.getBatchnum()), Integer.parseInt(entity.getBatchsize())));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("分页信息失败",e);
			}
			
			mv= excelService.exportExcel(entity,pages.getRows(), entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		}
		return mv;
		
	
	}
	
	/**
	 * 获取用户角色列表.
	 */
	@RequestMapping
	@ResponseBody
	public List<Role> getRoles(HttpServletRequest request){
		Role role = new Role();
		List<Role> roles = new ArrayList<Role>();
		String isSuperAdmin = request.getSession().getAttribute("isSuperAdmin").toString();
		if("true".equals(isSuperAdmin)){
			roles = userService.getRoles();
		}else{
			String userid = request.getSession().getAttribute("userId").toString();
			role.setCreateUserId(Long.parseLong(userid));
			roles = userService.getRoles(role);
		}
		return roles;
	}
	
	
	/**
	 * 
	* @Title: getRolesByUser 
	* @Description: TODO(根据创建用户获取角色) 
	* @param request
	* @param role
	* @return List<Role>    
	* @throws 
	* @author wangguohong
	 */
	@RequestMapping
	@ResponseBody
	public List<Role> getRolesByUser(HttpServletRequest request,Role role){
		List<Role> roles = null;
		String isSuperAdmin = request.getSession().getAttribute("isSuperAdmin").toString();
		if("true".equals(isSuperAdmin)){
			Role roleadmin = new Role();
			roleadmin.setId(role.getId());
			roles = userService.listjcjs(roleadmin);
		}else{
			Role roletemp = new Role();
			String userid = request.getSession().getAttribute("userId").toString();
			roletemp.setCreateUserId(Long.parseLong(userid));
			roles = userService.listjcjs(roletemp);
		}
		return roles;
	}
	/**
	 * 获取单位类型列表.
	 */
	@RequestMapping
	@ResponseBody
	public List<SysUnit> getSysUnits(HttpServletRequest request){
		List<SysUnit> sysUnits = userService.getSysUnits();
		return sysUnits;
	}
	
	/**
	 * 获取用户验证码配置列表.
	 */
	@RequestMapping
	@ResponseBody
	public List<SysUnit> getRegexList(HttpServletRequest request){
		List<SysUnit> sysUnits = userService.getSysUnits();
		return sysUnits;
	}
}
