/**
 * Project Name:smcs
 * File Name:UserURLPermissionFilter.java
 * Package Name:com.smcs.framework.system.auth.filters
 * Date:2013年9月23日下午2:26:58
 *
*/

package com.surfilter.system.auth.filters;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.auth.WebAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.User;

/**
 * ClassName:UserURLPermissionFilter. <br/>
 * 用户权限过滤器，这里只过滤URL
 * Date:     2013年9月23日 下午2:26:58 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationFilter
 */
public class UserURLPermissionFilter extends WebAuthorizationFilter {
	
	/**
	 * logger: 记录日志.
	 * @since JDK 1.6
	 */
	private static Log logger = LogFactory.getLog(UserURLPermissionFilter.class);
	
	
	/**
	 * Creates a new instance of UserURLPermissionFilter.
	 *
	 */
	public UserURLPermissionFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		UserAuthorization authorization = (UserAuthorization)token.getAuthorization();
		String uri = token.getURI();
		
		
		if(uri!=null && uri.startsWith("/framework/")){
			return;
		}
		//判断此用户是否为超级管理员,如果为超级管理员则不对权限进行过滤控管
		if(authorization.isSuperAdmin()){
			return;
		}
		if("/index.jsp".equals(uri)){
			return;
		}
		if("/system/redirect/getkeyWordMenu.do".equals(uri)){
			return;
		}
		if("/system/funcModule/listAllModule.do".equals(uri)){
			return;
		}
		
		List<String> list = authorization.getPermissions();
		for (String string : list) {
			logger.info("==============+++++++" + string);
			//System.out.println("==============+++++++" + string);
		}
		///system/redirect/redirectHomePage.do?path=system/sysParam/*
		uri = getUri(uri,token.getRequest().getQueryString());
		if (!authorization.hasNoPermission(uri)) {   //   hasPermission 以前是判断有哪些权限 现在改成除了禁用菜单意外其他的都可以访问
			
			System.out.println("用户没有权限访问此功能！URI=[" + uri + "]");
			
			//启用响应
			token.enableRequestResponse();
			//马上进行响应
			token.disableResponseWithFilterChainDone();
			if(uri.indexOf("redirectHomePage.do") >0){
				
				token.setRequestResponseURL("/" + FileUtil.getResouseValue("SYSTEM_NAME") + "/noRight.jsp?uri=" + uri.substring(1), true);
			}
			String errorMsg = "您目前没有权限访问:" + uri;
			String message =  "{\"success\":false,\"message\":\""+errorMsg+"\",\"errorCode\":\"602\"}";
			token.setRequestResponseMessage(message, true);
				
			//throw new IllegalPermissionException("用户没有权限访问此功能！URI=[" + uri + "]");
		}
	}
	
	/**
	 * 
	 * getUri:通过url、查询参数组装最后的uri. <br/>
	 *
	 * @author hongcheng
	 * @param uri uri
	 * @param queryString queryString
	 * @return uri
	 * @since JDK 1.6
	 */
	private String getUri(String uri,String queryString){
		 
		if(!uri.equals("/system/redirect/redirectHomePage.do")){
			//ajax类型url
			return uri; 	
		}
		if(queryString.indexOf("&")>0){
			queryString = queryString.substring(0,queryString.indexOf("&"));
		}
		//path=system/user_detail&id=34&_=1384761773230
		//path=system/user
		return uri + "?" + queryString;
	}
	
	
}

