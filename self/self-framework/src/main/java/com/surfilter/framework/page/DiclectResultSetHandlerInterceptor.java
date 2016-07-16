/**
 * Project Name:smcs<br>
 * File Name:DiclectResultSetHandlerInterceptor.java<br>
 * Package Name:com.smcs.core.common.page<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.framework.page;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.NestedResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * ClassName: DiclectResultSetHandlerInterceptor <br/>
 * Function: 分页拦截器. <br/>
 * date: 2013年9月18日 上午11:02:44 <br/>
 *
 * @author hongcheng
 * @version 
 * @since JDK 1.6
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class DiclectResultSetHandlerInterceptor implements Interceptor {

	/**
	 * 拦截器
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @param invocation 注入
	 * @return 对象
	 * @throws Throwable 异常信息
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		FastResultSetHandler resultSet = (FastResultSetHandler) invocation.getTarget();
		if(!(resultSet instanceof NestedResultSetHandler)) {
			RowBounds rowBounds = (RowBounds) ReflectUtil.getClassField(resultSet, "rowBounds");
			if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
				ReflectUtil.setClassField(resultSet, "rowBounds", new RowBounds());
			}
		}
		return invocation.proceed();
	}

	/**
	 * 
	 * 插件.
	 * @param target 目标
	 * @return 对象
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置属相信息.
	 * @param properties 属性
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) {
	}
}