/**
 * Project Name:smcs<br>
 * File Name:UserCtrl.java<br>
 * Package Name:<br>
 * Date:2015年05月18日  下午07:06:32<br>
 *
*/
package com.surfilter.scma.ctrl;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.scma.framework.BaseController;
import com.surfilter.scma.framework.ExtJsObject;
import com.surfilter.scma.framework.Page;
import com.surfilter.scma.framework.PageUtil;
import com.surfilter.scma.model.Manager;
import com.surfilter.scma.model.User;
import com.surfilter.scma.service.ManagerService;
import com.surfilter.scma.service.UserService;
import com.surfilter.scma.util.SimpleMD5PasswordEncoder;

/**
 * ClassName:UserCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:06:32<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping(value="/user")
public class UserCtrl extends BaseController{

	/**
	 * 注入userService.
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private ManagerService managerService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(UserCtrl.class);
	
	
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
	@RequestMapping
	@ResponseBody
	public Page<User> getPageModel(User entity,Integer page,Integer rows){
	
		Page<User> pageModel = null;
		try {
			pageModel = userService.getPageModel(entity,PageUtil.get(page,rows));
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
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		User entity = null;
		try {
			entity = userService.getEntityById(id);
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
	@RequestMapping(value="/addEntity")
	@ResponseBody
	public ExtJsObject addEntity(User entity){
		ExtJsObject result = new ExtJsObject(true, "注册成功!",null);
		try {
			if( entity.getAge() == null || ( Integer.parseInt( entity.getAge() ) >= 1 && Integer.parseInt( entity.getAge() ) <= 150) ){
				entity.setPassword( SimpleMD5PasswordEncoder.encode( entity.getPassword() ) );
				
				userService.addEntity(entity);
			}else{
				result = new ExtJsObject(false, "注册失败,年龄必须在 1 - 150之间!",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ExtJsObject(false, "注册失败,系统内部错误!",null);
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(User entity){
	
		try {
			userService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * 密码修改
	 * @param id 用户id
	 * @param oldPas 原始密码
	 * @param newPas 新密码
	 * @return
	 */
	@RequestMapping(value="changePas")
	@ResponseBody
	public ExtJsObject changePas(Long id,String oldPas,String newPas){
		ExtJsObject result = new ExtJsObject(true, "密码修改成功!");
		try {
			User entity = userService.getEntityById(id);
			if( entity != null ){
				if( entity.getPassword().equals( SimpleMD5PasswordEncoder.encode( oldPas ) ) ){
					entity.setPassword( SimpleMD5PasswordEncoder.encode(newPas) );
					
					userService.editEntity(entity);
				}else{
					result = new ExtJsObject(false, "原始密码输入错误!");
				}
			}else{
				result = new ExtJsObject(false, "系统错误!");
			}
			
		} catch (Exception e) {
			result = new ExtJsObject(false, "系统错误!");
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author lenovo
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
	 * @author lenovo
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
	 *  登录
	 * @param request
	 * @param username 用户名
	 * @param password 密码
	 * @param identity 身份
	 * @return
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public ExtJsObject login(HttpServletRequest request,String username,String password,String identity){
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("username", username);
		
		ExtJsObject result = new ExtJsObject(false, "用户名或密码错误!",obj);
		try {
			if( "1".equals(identity) ){//普通用户
				User user = userService.getEntityByCodeAndPas(username, SimpleMD5PasswordEncoder.encode(password));
				if( user != null ){//可获取相应用户
					request.getSession().setAttribute("userId", user.getId());
					request.getSession().setAttribute("userName", user.getUsername());
					request.getSession().setAttribute("identity", identity);
					request.getSession().setAttribute("user", user);
					
					result = new ExtJsObject(true, "登录成功!",null);
				}
			}else if( "2".equals(identity) ){//管理员
				Manager manager = managerService.getEntityByCodeAndPas(username, SimpleMD5PasswordEncoder.encode(password));
				if( manager != null ){//可获取相应用户
					request.getSession().setAttribute("userId", manager.getId());
					request.getSession().setAttribute("userName", manager.getMname());
					request.getSession().setAttribute("identity", manager.getEntitle());
					request.getSession().setAttribute("user", manager);
					
					result = new ExtJsObject(true, "登录成功!",null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ExtJsObject(false, "系统错误!",null);
		}
		return result;
	}
	
	/**
	 * 退出方式
	 * @return  主页
	 */
	@RequestMapping(value="loginOut")
	public String loginOut(HttpServletRequest request){
		
		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("identity");
		request.getSession().removeAttribute("user");
		
		return "index";
	}
}
