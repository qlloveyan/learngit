/**
 * Project Name:lichen
 * File Name:SysLogInterceptor.java
 * Package Name:com.surfilter.system.interceptors
 * Date:2014年1月2日上午9:51:05
 *
 */

package com.surfilter.system.interceptors;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.util.WebUtils;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.model.SysLog;
import com.surfilter.system.model.SysLog.OperType;
import com.surfilter.system.model.User;
import com.surfilter.system.service.FuncModuleService;
import com.surfilter.system.service.SysLogService;

/**
 * ClassName:SysLogInterceptor <br/>
 * Date: 2014年1月2日 上午9:51:05 <br/>
 * 
 * @author Tuyan
 * @version
 * @since JDK 1.6
 * @see
 */
public class SysLogInterceptor extends HandlerInterceptorAdapter {

	private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
	
	@Autowired
	private SysLogService sysLogService;
	
	@Autowired
	private FuncModuleService funcModuleService;
	
	private List<String> noLogSuffixs;
	
	/**
	 * REDIRECT_HOME_PAGE_URI: 重定向到首页的URL地址.
	 * 
	 * @since JDK 1.6
	 */
	public final static String REDIRECT_HOME_PAGE_METHOD = "redirectHomePage";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession(true).getServletContext());
//		SysLogService sysLogService = wac.getBean(SysLogService.class);

		String methodName = methodNameResolver.getHandlerMethodName(request);
		if (methodName.indexOf(REDIRECT_HOME_PAGE_METHOD) == -1) {
			String suffix = getMethodSuffix(methodName);
			if (isNoLogSuffixs(suffix)) {
				return;
			}
			SysLog sysLog = new SysLog();
			sysLog.setOpreaterIp(sysLogService.getIpAddr(request));
			sysLog.setModuleName(getModuleName(handler));
			sysLog.setOperateType(getOperType(suffix));
			sysLog.setLogType(SysLog.BEHAVIOR_LOG_TYPE);
			sysLog.setOpreateTime(new Date());
			Object sessionUser = WebUtils.getSessionAttribute(request, FrameworkGlobal.AUTHORIZATION_TOKEN);
			if (null != sessionUser) {
				UserAuthorization auth = (UserAuthorization) sessionUser;
				User user = auth.getSubject();
				sysLog.setUserName(user.getUserName() + "\t[" + user.getUserCode() + "]");
			}
			sysLog.setDescription(methodName);
			sysLogService.addEntity(sysLog);
		}
		// 如果是重定向到模块首页的URL那么不记录日志
	}
	
	private boolean isNoLogSuffixs(String suffix) {
		return noLogSuffixs.contains(suffix);
	}
	
	/**
	 * noLogSuffixs.
	 *
	 * @return  the noLogSuffixs
	 * @since   JDK 1.6
	 */
	public final List<String> getNoLogSuffixs() {
		return noLogSuffixs;
	}

	/**
	 * noLogSuffixs.
	 *
	 * @param   noLogSuffixs    the noLogSuffixs to set
	 * @since   JDK 1.6
	 */
	public final void setNoLogSuffixs(List<String> noLogSuffixs) {
		this.noLogSuffixs = noLogSuffixs;
	}

	private String getOperType(String suffix) {
		OperType ot = OperType.forSuffix(suffix);
		if (null == ot) {
			return suffix;
		}
		String operType = "未知";
		switch (ot) {
		case GET:
		case LIST:
			operType = "查询";
			break;
		case ADD:
			operType = "新增";
			break;
		case DEL:
			operType = "删除";
			break;
		case EDIT:
			operType = "修改";
			break;
		case IS:
			operType = "判断";
			break;
		default:
			operType = suffix;
			break;
		}
		return operType;
	}
	

	private String getMethodSuffix(String methodName) {
		char[] cs = methodName.toCharArray();
		int endIndex = 0;
		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if (Character.isUpperCase(c)) {
				endIndex = i;
				break;
			}
		}
		return methodName.substring(0, endIndex);
	}

	private String getModuleName(Object handler) {
		String className = handler.getClass().getSimpleName();
		String funcCode = StringUtils.replace(className, "Ctrl", "");
		String funcName = funcCode;
		List<FuncModule> funcModules = funcModuleService.listFuncModuleByCode(funcCode);
		if (null != funcModules && !funcModules.isEmpty()) {
			funcName = funcModules.get(0).getFuncName();
		}
		return funcName;
	}
}
