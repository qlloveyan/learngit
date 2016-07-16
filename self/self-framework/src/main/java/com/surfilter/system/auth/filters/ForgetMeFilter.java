/**
 * Project Name:smcs
 * File Name:ForgetMeFilter.java
 * Package Name:com.smcs.framework.system.auth.filters
 * Date:2013年10月10日下午3:27:31
 *
*/

package com.surfilter.system.auth.filters;


import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.auth.URLAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ClassName:ForgetMeFilter. <br/>
 * 用户注销过滤器
 * Date:     2013年10月10日 下午3:27:31 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 URLAuthorizationFilter
 */
public class ForgetMeFilter extends URLAuthorizationFilter {
	
	/**
	 * 用户安全服务.
	 */
//	@Autowired
//	private SafetyService safetyService;

	/**
	 * Creates a new instance of ForgetMeFilter.
	 *
	 */
	public ForgetMeFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		//将该用户在安全表中的登陆状态置为 1 
//		String userId = token.getAttribute("userId").toString();
//		if( userId != null ){
//			
//			//先获取与用户相关的安全认证
//			Safety entity = safetyService.getEntityByUserId(Long.parseLong(userId));
//			if( entity != null){
//				
//				Safety temp = new Safety();
//				temp.setId( entity.getId() );
//				temp.setLoginState( 1L );
//				safetyService.editEntity(temp);
//			}
//		}
		
		token.clearAttributes();
		//启用响应
		token.enableRequestResponse();
		// 马上进行响应
		token.disableResponseWithFilterChainDone();
		// 设置响应的URL以及是否重定向
		//这里做单点登录的判断，如果是单点登录，则返回导航页面。
		if ("true".equals(FileUtil.getResouseValue("isSsoLogin"))) {
		    token.setRequestResponseURL(FileUtil.getResouseValue("rebackSSOURL"), true);
		} else {
		    token.setRequestResponseURL("/" + FileUtil.getResouseValue("SYSTEM_NAME"), true);
		}
		
	}

}

