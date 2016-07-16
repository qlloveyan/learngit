/**
 * Project Name:smcs
 * File Name:HomePageFilter.java
 * Package Name:com.surfilter.framework.system.auth.filters
 * Date:2013-10-22下午6:21:10
 *
 */

package com.surfilter.system.auth.filters;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.auth.URLAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ClassName:HomePageFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013-10-22 下午6:21:10 <br/>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 * @see
 */
public class HomePageFilter extends URLAuthorizationFilter {

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token)
			throws AuthorizationException {
		// 启用响应
		token.enableRequestResponse();
		// 马上进行响应
		token.disableResponseWithFilterChainDone();
		token.setRequestResponseURL("/" + FileUtil.getResouseValue("SYSTEM_NAME") + "/WEB-INF/views/br/house.jsp", false);
	}

}
