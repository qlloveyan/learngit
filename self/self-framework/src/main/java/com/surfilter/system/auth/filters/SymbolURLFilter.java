/**
 * Project Name:smcs
 * File Name:SymbolURLFilter.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月22日下午2:27:22
 *
*/

package com.surfilter.system.auth.filters;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.utils.StringUtils;
import com.surfilter.framework.web.auth.WebAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;
import com.surfilter.system.auth.ehandlers.IllegalURLException;

/**
 * ClassName:SymbolURLFilter. <br/>
 * 用来过滤URL，如果URL中有一些指定的特殊符号，那么URL就被认定为不合法的，不允许访问<p/>
 * 
 * Date:     2013年9月22日 下午2:27:22 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 WebAuthorizationFilter
 */
public class SymbolURLFilter extends WebAuthorizationFilter {
	
	/**
	 * symbols: 如果URL里面有这些符号，就被认为是不合法的URL.
	 * @since JDK 1.6
	 */
	private String[] symbols;

	/**
	 * Creates a new instance of SymbolURLFilter.
	 *
	 */
	public SymbolURLFilter() {
		super();
	}

	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		String uri = token.getURI();
		for (String symbol : symbols) {
			if (StringUtils.containsText(uri, symbol)) {
				throw new IllegalURLException("错误的URL，此URL[" + uri + "]里面含有不允许的特殊符号[" + symbol + "]！");
			}
		}
	}

	/**
	 * symbols.
	 *
	 * @return  the symbols
	 * @since   JDK 1.6
	 */
	public String[] getSymbols() {
		return symbols;
	}

	/**
	 * symbols.
	 *
	 * @param   symbols    the symbols to set
	 * @since   JDK 1.6
	 */
	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}

}

