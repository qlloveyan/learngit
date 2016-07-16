/**
 * Project Name:lichen
 * File Name:UkeyFilter.java
 * Package Name:com.surfilter.system.auth.filters
 * Date:2014-1-2下午3:21:11
 *
*/

package com.surfilter.system.auth.filters;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.web.auth.WebAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;

/**
 * ukey过滤器
 * ClassName:UkeyFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-2 下午3:21:11 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UkeyFilter extends WebAuthorizationFilter{

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token)
			throws AuthorizationException {
		
		System.out.println("这里实现ukey过滤");
		
		
	}

}

