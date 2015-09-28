package com.ql.basepro.system.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.basepro.framework.BaseController;
import com.ql.basepro.framework.ExtJsObject;
import com.ql.basepro.framework.Page;
import com.ql.basepro.framework.utils.ConfigUtil;
import com.ql.basepro.framework.utils.PageUtil;
import com.ql.basepro.framework.utils.SimpleMD5PasswordEncoder;
import com.ql.basepro.system.model.User;
import com.ql.basepro.system.service.UserService;

/**
 * 用户信息控制类
 * @author ql
 */
@Controller
@RequestMapping(value="user")
public class UserCtrl extends BaseController{
	
	/**
	 * 注入用户服务类
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/getPageModel")
	@ResponseBody
	public Page<User> getPageModel(HttpServletRequest request , User user , Integer page , Integer rows){
		Page<User> pageModel = null;
		try {
			pageModel = userService.getPageModel(user, PageUtil.get(page, rows));
			
		} catch (Exception e) {
			System.out.println("用户信息分页查询失败!");
			e.printStackTrace();
		}
		return pageModel;
	}
	
	/**
	 * list:根据查询条件查询实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<User> list(User user){
		List<User> result = null;
		try {
			result = userService.list(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/addEntity")
	@ResponseBody
	public ExtJsObject addEntity(User user){
		ExtJsObject result = new ExtJsObject(true, "用户信息添加成功!");
		try {
			//合法入库
			User temp = new User();
			temp.setPhone( user.getPhone() );
			if( userService.list( temp ).size() == 0  ){
				//设置剩余参数
				user.setPassword(ConfigUtil.getConfig("default_password"));
				user.setRegisterTime( new Date() );
				user.setUpdateTime(new Date());
				
				userService.addEntity(user);
			}else{
				result = new ExtJsObject(false, "该手机号码已被注册!");
			}
		} catch (Exception e) {
			result = new ExtJsObject(false, "用户信息添加失败!");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * delEntity:根据ID删除实体.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/delEntity")
	@ResponseBody
    public ExtJsObject delEntity(long id){
		try {
			userService.delEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
			return renderObject(false, "删除失败,服务器内部错误!", null);
		}
    	return renderSuccess();
    }
    
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author ql
	 * @param ids 实体ID集合
	 * @return 批量删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/delBatchEntity")
	@ResponseBody
    public ExtJsObject delBatchEntity(long[] ids){
		try {
			userService.delBatchEntity(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return renderObject(false, "删除失败,服务器内部错误!", null);
		}
    	return renderSuccess();
    }
	
   /**
	 * edit:编辑实体信息.
	 *
	 * @author ql
	 * @param user 实体信息
	 * @return 编辑实体结果
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/editEntity")
	@ResponseBody
    public ExtJsObject editEntity(User user){
		try {
			userService.editEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
			return renderObject(false, "用户信息修改失败,服务器内部错误!", null);
		}
    	return renderSuccess();
    }
	
	/**
	 * getEntityById:根据实体ID查询实体信息.
	 *
	 * @author ql
	 * @param id 实体ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/getEntity")
	@ResponseBody
	public User getEntityById(long id){
		User result = null;
		try {
			result = userService.getEntityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据用户名获取实体
	 * @param phone 用户手机号码
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public ExtJsObject login(HttpServletRequest request , String phone,String password){
		
		ExtJsObject result = new ExtJsObject(false, "用户名或密码错误!");
		try {
			
			User user = userService.getEntityByCodeAndPas(phone, SimpleMD5PasswordEncoder.encode( password ),"100");
			if( user != null ){
				if( user.getActive() != 1 ){
					//设置session属性值
					HttpSession session = request.getSession();
					session.setAttribute("userId", user.getId());
					session.setAttribute("userName", user.getName());
					session.setAttribute("user", user);
					
					result = new ExtJsObject(true, "用户登录成功!");
				}else{
					result = new ExtJsObject(true, "该用户已禁用!");
				}
			}
		} catch (Exception e) {
			result = new ExtJsObject(false, "服务器内部错误!");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据用户名获取实体
	 * @param phone 用户手机号码
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value="/logindy")
	public String logindy(HttpServletRequest request , String phone,String password){
		String pageUrl = "../main";
		try {
			User user = userService.getEntityByCodeAndPas(phone, SimpleMD5PasswordEncoder.encode( password ),"100");
			if( user != null ){
				if( user.getActive() == 1 ){
					//设置session属性值
					HttpSession session = request.getSession();
					session.setAttribute("userId", user.getId());
					session.setAttribute("userPhone", user.getPhone());
					session.setAttribute("userName", user.getName());
					session.setAttribute("user", user);
				}else{
					pageUrl = "../login";
					
					request.setAttribute("phone", phone);
					request.setAttribute("error", "该用户已被禁用!");
				}
				
			}else{
				pageUrl = "../login";
				
				request.setAttribute("phone", phone);
				request.setAttribute("error", "用户名或密码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageUrl;
	}
	
	/**
	 * 退出系统操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		
		//清空session信息
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "../login";
	}
	
	
	@RequestMapping(value="/changePassword")
	@ResponseBody
	public ExtJsObject changePassword(HttpServletRequest request,String password , String newPassword ){
		ExtJsObject result = new ExtJsObject(false, "用户密码修改失败!");
		User user = (User) request.getSession().getAttribute("user");
		if( user.getPassword().equals( SimpleMD5PasswordEncoder.encode(password) )  ){
			try {
				User temp = new User();
				temp.setId( user.getId() );
				temp.setPassword( SimpleMD5PasswordEncoder.encode(newPassword) );
				temp.setUpdateTime( new Date() );
				
				userService.editEntity(temp);
				
				result = new ExtJsObject(true, "用户密码修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			result = new ExtJsObject(false, "原始密码输入错误!");
		}
		return result;
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
	@RequestMapping(value="/reUserState")
	@ResponseBody
	public ExtJsObject reUserState(HttpServletRequest request,long userId, long userState){
		ExtJsObject result = new ExtJsObject(true, "用户状态修改成功!");
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user.getId().longValue()==userId){
				result = new ExtJsObject(false, "不能对自己的状态进行操作!");
			}else{
				userService.reUserState(userId, userState);
			}
		} catch (Exception e) {
			System.out.println("用户状态操作失败!");
			e.printStackTrace();
			result = new ExtJsObject(false, "用户状态修改失败!");
		}
		return result;
	}
	
	/**
	 * 将普通用户设置为管理员(规则：添加新用户,设置状态为100)
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value="/toAdmin")
	@ResponseBody
	public ExtJsObject reUserState(HttpServletRequest request,long userId){
		ExtJsObject result = new ExtJsObject(true, "用户状态修改成功!");
		try {
			User user = userService.getEntityById(userId);
			user.setId(null);
			user.setStatus(100L);
			user.setRegisterTime( new Date() );
			user.setUpdateTime( new Date() );
			
			userService.addEntity( user );
		} catch (Exception e) {
			System.out.println("用户状态操作失败!");
			e.printStackTrace();
			result = new ExtJsObject(false, "用户状态修改失败!");
		}
		return result;
	}
	
	/**
	 * checkUser:审核用户状态.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param userState 用户状态
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/checkUser")
	@ResponseBody
	public ExtJsObject checkUser(HttpServletRequest request,String userId, long active){
		ExtJsObject result = new ExtJsObject(true, "用户状态修改成功!");
		try {
			User user = (User) request.getSession().getAttribute("user");
			String[] ids = userId.split(",");
			User temp = null;
			for( String idTemp : ids ){
				long id = Long.parseLong(idTemp);
				if( id != user.getId() ){
					temp = new User();
					
					temp.setId(id);
					temp.setActive(active);
					userService.editEntity(temp);
				}
			}
		} catch (Exception e) {
			System.out.println("用户状态操作失败!");
			e.printStackTrace();
			result = new ExtJsObject(false, "用户状态修改失败!");
		}
		return result;
	}
}
