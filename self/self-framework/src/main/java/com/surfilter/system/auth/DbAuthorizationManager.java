/**
 * Project Name:smcs
 * File Name:DbAuthorizationManager.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月23日上午9:51:05
 *
*/

package com.surfilter.system.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.octo.captcha.service.CaptchaServiceException;
import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.auth.Authorization;
import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.auth.AuthorizationManager;
import com.surfilter.framework.auth.PasswordEncoder;
import com.surfilter.framework.auth.UnauthorizedToken;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.search.KeywordHandler;
import com.surfilter.framework.search.KeywordModel;
import com.surfilter.framework.utils.StringUtils;
import com.surfilter.framework.verification.CustomGenericManageableCaptchaService;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.auth.ehandlers.UserCodeOrPasswdException;
import com.surfilter.system.model.AccessLog;
import com.surfilter.system.model.SysLog;
import com.surfilter.system.model.User;
import com.surfilter.system.service.AccessLogService;
import com.surfilter.system.service.ElementService;
import com.surfilter.system.service.FuncModuleService;
import com.surfilter.system.service.RoleService;
import com.surfilter.system.service.SkinService;
import com.surfilter.system.service.SysSyleService;
import com.surfilter.system.service.UserService;

/** 
 * ClassName:DbAuthorizationManager. <br/>
 * 从数据库中获取权限对象.
 * Date:     2013年9月23日 上午9:51:05 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 AuthorizationManager
 */
public class DbAuthorizationManager extends BaseController implements AuthorizationManager {
	
	/**
	 * TYPE_MODULE_MENU: 菜单类型功能.
	 * @since JDK 1.6
	 */
	public static final String TYPE_MODULE_MENU = "MODULE_MENU";
	
	/**
	 * TYPE_MODULE_CONTROL: 控件类型功能.
	 * @since JDK 1.6
	 */
	public static final String TYPE_MODULE_CONTROL = "MODULE_CONTROL";
	
	/**
	 * TYPE_MODULE_POST: 提交类型功能.
	 * @since JDK 1.6
	 */
	public static final String TYPE_MODULE_POST = "MODULE_POST";

	/**
	 * passwordEncoder: 加密器.
	 * @since JDK 1.6
	 */
	private PasswordEncoder passwordEncoder;

	/**
	 * userService: 用户服务类.
	 * @since JDK 1.6
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private SkinService skinService;
	
	@Resource(name="captchaService")  
	private CustomGenericManageableCaptchaService captchaService;  
	/**
	 * sysModuleService: 功能模块服务类.
	 * @since JDK 1.6
	 */
	@Autowired
	private FuncModuleService funcModuleService;
	
	@Autowired
	private AccessLogService accessLogService;
	
	/**
	 * 页面元素服务.
	 */
	@Autowired
	private ElementService elementService;
	
	@Autowired
	private RoleService roleService;
	/**
	 * 系统样式server
	 */
	@Autowired
	private SysSyleService sysSyleService;
	
	
	/**
	 * superAdministratorLoginCode: 超级管理员登录名称，就是拥有所有功能权限的超级用户.
	 * @since JDK 1.6
	 */
	@Value("${super.administrator.login.code}")
	private String superAdministratorLoginCode;
	
	/**
	 * superAdministratorLoginId:TODO(超级管理员id).
	 * @since JDK 1.6
	 */
	@Value("${super.administrator.login.id}")
	private long superAdministratorLoginId;
	
	/**
	 * superAdministratorLoginPassword: 超级管理员登录密码，这个密码是加密过后的密码，就是拥有所有功能权限的超级用户.
	 * @since JDK 1.6
	 */
	@Value("${super.administrator.login.password}")
	private String superAdministratorLoginPassword;
	
	public DbAuthorizationManager() {
		super();
	}
	
	@Override
	public synchronized Authorization authenticate(UnauthorizedToken token) throws AuthorizationException {
		
		String userCode = token.getParameter("username");
		String rawPassword = token.getParameter("pass");	//密码明文
		
		if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(rawPassword)) {
			throw new UserCodeOrPasswdException(userCode + "#1#用户名或密码错误");
		}
		
        String password = passwordEncoder.encode(rawPassword);
		
		User user = null;
		//临时变量,用于用户密码锁定安全验证
		User codeUser = null;
		boolean isSuperAdministrator = false;
		
		//获取是否需要超级管理员验证功能
		String needSuperAdmin = FileUtil.getResouseValue("need_super_admin");
		if(StringUtils.isNotEmpty(needSuperAdmin) && "true".equals(needSuperAdmin)){
			//为了防止权限问题，去掉超级管理员权限判断
			isSuperAdministrator = isSuperAdministrator(userCode, password);
		}
		//如果是超级用户那么直接进行授权，获取所有的权限信息
		
		//判断验证码
		boolean vflag = verifivation(token);
		if(!vflag){
			throw new UserCodeOrPasswdException(userCode + "#3#验证码错误");
		}
		if (isSuperAdministrator) {
			//超级用户需要更加详细的权限赋值，当系统只使用菜单级别的权限时以下做法不会有问题，但是如果更详细的权限就不适合用以下方法实例化用户信息
			user = new User();
			user.setUserCode(superAdministratorLoginCode);
			user.setUserName(superAdministratorLoginCode);
			user.setId(superAdministratorLoginId);
			user.setUserPass(superAdministratorLoginPassword);
		} else {
			//验证用户名是否存在
			codeUser = userService.getByCode(userCode);
			
			if( codeUser != null ){
				user = userService.getEntityByCodeAndPasswd(userCode, password);
			}else{
				saveAuthLog(SysLog.OPER_EXECPTION, "尝试使用用户名[" + userCode + "]登录失败!用户名不存在!", (WebUnauthorizedToken) token,userCode);
				//1、用户名和密码错误,
				throw new UserCodeOrPasswdException(userCode + "#1#用户名不存在");
			}
		}
		
		
		if (null == user) {
			saveAuthLog(SysLog.OPER_EXECPTION, "尝试使用用户名[" + userCode + "]登录失败!", (WebUnauthorizedToken) token,userCode);
			throw new UserCodeOrPasswdException(userCode + "#1#用户名或密码错误");
		}
		
		//判断用户类型。如果不是超级管理员的话需要用用户角色关系
		if( !isSuperAdministrator ){
			if(!roleService.checkUserRole(user.getId())){
				throw new UserCodeOrPasswdException(userCode + "#1#非法用户，没有分配角色");
			}
		}
		//初始化样式菜单
		sysSyleService.addLoginEntity(user);
		//根据用户信息创建已授权对象
		UserAuthorization authorization = new UserAuthorization(user);
		UnCheckTreeNodeBean uiMenus = initMenus(user, isSuperAdministrator,authorization);
		
		//获取菜单关键词集合
		List<KeywordModel> listkeymenu = new ArrayList<KeywordModel>();
		KeywordHandler.setMenuWordsToSession(uiMenus, listkeymenu);
		//获取与当前用户对应的密码更新标识
		setSesionValues(token, user, listkeymenu);
		
		saveAuthLog(SysLog.OPER_SUCCESS, "使用用户名[" + userCode + "]登录成功!", (WebUnauthorizedToken) token,user.getUserName());
		return authorization;
	}


	/**
	 * verifivation:(判断验证码). <br/>
	 *
	 * @author wangguohong
	 * @return
	 * @since JDK 1.6
	 */
	private boolean verifivation(UnauthorizedToken token){
	    
		String isCodestr = FileUtil.getResouseValue("isVcode");
		boolean isVcode = true; //是否需要验证码
		
	    //非单点登录
	    if(!isCodestr.equals("")){
            try {
                isVcode = Boolean.parseBoolean(isCodestr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		Boolean isResponseCorrect = Boolean.FALSE;
		if(isVcode){
			
			WebUnauthorizedToken wut = (WebUnauthorizedToken) token;
			String captchaId = wut.getRequest().getSession().getId();    
			String captcha_value = wut.getRequest().getParameter("captcha_value");
			try {
				isResponseCorrect = captchaService.validateResponseForID(captchaId, captcha_value);    
			} catch (CaptchaServiceException e) {       
				e.printStackTrace();    
			}
			captchaService.removeCaptcha(captchaId);
		}else{
			isResponseCorrect = true;
		}
		return isResponseCorrect;
	}
	/**
	 * setSesionValues:(设置session值). <br/>
	 *
	 * @author wangguohong
	 * @param token
	 * @param user
	 * @param listkeymenu
	 * @since JDK 1.6
	 */
	private void setSesionValues(UnauthorizedToken token, User user,List<KeywordModel> listkeymenu) {
		token.setAttribute("menuKeyword", listkeymenu);
		user.setLastLoginIp( getRealIp(token) );
		//这里
		token.setAttribute("user", user);
		token.setAttribute("userId", user.getId());
		token.setAttribute("userName", user.getUserName());
		token.setAttribute("userCode", user.getUserCode());
		if( user.getUserCode().equals(superAdministratorLoginCode) ){
			token.setAttribute("isSuperAdmin", "true");
		}else{
			token.setAttribute("isSuperAdmin", "false");
		}
		//设置布局方式
		String layout = sysSyleService.getlayoutStle(user.getId());
		String layouttemp = "";
		if(layout.equals("2")){
			layouttemp = "lr";
    	}
    	if(layout.equals("1")){
    		layouttemp = "ud";
    	}
		token.setAttribute("layout", layouttemp);
		//设置皮肤
		token.setAttribute("skin", sysSyleService.getskin(user.getId(),layout));
		//获取logo
		token.setAttribute("logo",  sysSyleService.getLogo(user.getId(),layout));
		
		//加载当前用户皮肤Skin entity = skinService.getEntityByUserId(userid);
		token.setAttribute("skinCode", skinService.getEntityByUserId(user.getId()).getSkinCode());
		
		//获取单位信息
		user = userService.getEntityById(user.getId());
		if(user!=null){
			token.setAttribute("nativeId", user.getSysunit().getNativeId());
			token.setAttribute("unitId", user.getSysunit().getId());
			token.setAttribute("unitName", user.getSysunit().getUnitName());
		}
	}
	/**
	 * initMenus:(初始化菜单). <br/>
	 *
	 * @author wangguohong
	 * @param user
	 * @param isSuperAdministrator
	 * @param authorization
	 * @return
	 * @since JDK 1.6
	 */
	private UnCheckTreeNodeBean initMenus(User user,boolean isSuperAdministrator, UserAuthorization authorization) {
		//添加用户权限
		//没有用户限定，因为这里就是针对登录用户的
		//没有角色限定，因为这里就是针对登录用户角色的
		//需要限定访问的URL和控件
		//FIXME 加入权限URL，进行URL权限过滤
		//FIXME 超级用户需要所有的URL权限
		Long loginUserId = user.getId();
		if (isSuperAdministrator) {
			authorization.setSuperAdmin(true);
		}
		UnCheckTreeNodeBean uiMenus = new UnCheckTreeNodeBean();
		
		//初始化用户可以使用的菜单
		String platform = FileUtil.getResouseValue(FrameworkGlobal.SYSTEM_PLATFORM);
		Long parentId = 0L;
		if(platform.equalsIgnoreCase("gj")){
			parentId = Long.valueOf(-1);
		}else if(platform.equalsIgnoreCase("sw")){
			parentId = Long.valueOf(-2);
		}
		uiMenus = funcModuleService.listAllModuleByUserAndType(uiMenus, loginUserId, "0",parentId, true);
		authorization.setUIMenus(uiMenus);
		
		List<String> permissions = funcModuleService.getNoFuncUrlByUserId(loginUserId);
		Map<String,List<String>> permissionsMap = new HashMap<String,List<String>>();
		permissionsMap.put(platform, permissions);
		authorization.addPermissions(permissionsMap);
		
		//页面元素权限
		List<String> elementPermissions = elementService.getElementCodeByUserId(loginUserId);
		authorization.addElementPermissions(elementPermissions);
		return uiMenus;
	}

	
	
	
	@Override
	public void setPasswordEncoder(PasswordEncoder encoder) {
		this.passwordEncoder = encoder;
	}
	
	/**
	 * isSuperAdministrator: 是否为拥有所有功能权限的万能用户. <br/>
	 *
	 * @author Tuyan
	 * @param userCode	登录用户名
	 * @param password	登录用户密码
	 * @return	True/False
	 * @since JDK 1.6
	 */
	protected boolean isSuperAdministrator(String userCode, String password) {
		return (userCode.equals(superAdministratorLoginCode) && password.equals(superAdministratorLoginPassword));
	}
	
	/**
	 * saveAuthLog: 记录授权日志. <br/>
	 *
	 * @author Tuyan
	 * @param operResult	操作结果
	 * @param description	操作描述
	 * @param request	请求
	 * @since JDK 1.6
	 */
	protected void saveAuthLog(long operResult, String description, WebUnauthorizedToken wut,String userCode) {
//		SysLog sysLog = new SysLog();
//		sysLog.setOpreaterIp(sysLogService.getIpAddr(wut.getRequest()));
//		sysLog.setModuleName("登录授权");
//		sysLog.setUserName( wut.getParameter("username") );
//		sysLog.setOperateType("登录授权");
//		sysLog.setLogType(SysLog.AUTH_LOG_TYPE);
//		sysLog.setOpreateTime(new Date());
//		sysLog.setOperateResult(operResult);
//		sysLog.setDescription(description);
//		sysLogService.addEntity(sysLog);
		
		AccessLog accessLog = new AccessLog();
		accessLog.setLogMsg(description);
		accessLog.setIp(getRealIp(wut));
		accessLog.setUserName(userCode);
		accessLog.setTime( new Date() );
		accessLog.setFuncModule( "系统管理" );
		accessLog.setMethodType( "登录" );
		
		accessLogService.addEntity(accessLog);
	}
	
	/**
	 * 
	 * getRealIp:(获取用户真实IP)
	 * @author wangguohong
	 * @param token
	 * @return
	 * @since JDK 1.6
	 */
	public String getRealIp(UnauthorizedToken token) {
		WebUnauthorizedToken wtoken = (WebUnauthorizedToken) token;
		HttpServletRequest request = wtoken.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static void main(String[] args) {
		String s = "js/sysui/images/logoimage/top-left_prdt.png";
		String pngname = s.substring(s.lastIndexOf("/")+1, s.length());
		System.out.println(pngname.substring(0, pngname.length()-4));
	}
}

