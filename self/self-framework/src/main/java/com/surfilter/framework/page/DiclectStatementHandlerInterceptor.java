package com.surfilter.framework.page;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.surfilter.framework.utils.ConfigUtil;

@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DiclectStatementHandlerInterceptor implements Interceptor {

	/**
	 * 拦截器.
	 * @param invocation 注入
	 * @return 对象
	 * @throws Throwable 异常信息
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
		PreparedStatementHandler handler = (PreparedStatementHandler) ReflectUtil.getClassField(statement, "delegate");
		RowBounds rowBounds = (RowBounds) ReflectUtil.getSuperClassField(handler, "rowBounds");
		if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
			BoundSql boundSql = statement.getBoundSql();
			String sql = boundSql.getSql();
			
			String dataBase = ConfigUtil.getConfig("db.database", "mysql");
			
			//mysql数据库
			if("mysql".equals(dataBase)){
				sql = getLimitStringMysql(sql, rowBounds.getOffset(), rowBounds.getLimit());
			}else if("oracle".equals(dataBase)){
				//oracle数据库
				sql = getLimitStringOracle(sql, rowBounds.getOffset(), rowBounds.getLimit());
			}else{
				throw new Exception("暂时不支持该数据库");
			}
			//System.out.println("sql:" + sql);
			ReflectUtil.setClassField(boundSql, "sql", sql);
		}
		return invocation.proceed();
	}

	/**
	 * 插件
	 * 插件.
	 * @param target 目标对象
	 * @return 插件
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置属性.
	 * @param properties 属性
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) {
	}
	
	/**
	 * 
	 * getLimitStringOracle:封装sql,进行分页. <br/>
	 *
	 * @author hongcheng
	 * @param sql sql
	 * @param offset 偏移量
	 * @param limit 条数
	 * @return 封装后的sql
	 * @since JDK 1.6
	 */
	public String getLimitStringOracle(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()- 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer( sql.length() + 100);
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_ ) where rownum_ <= " + limit + " and rownum_ > " + offset);
		}
		else {
			pagingSelect.append(" ) where rownum <= " + limit);
		}
		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		return pagingSelect.toString();
	}
	
	/**
	 * 
	 * getLimitStringMysql:封装sql,进行分页. <br/>
	 *
	 * @author hongcheng
	 * @param sql sql
	 * @param offset 偏移量
	 * @param limit 条数
	 * @return 封装后的sql信息
	 * @since JDK 1.6
	 */
	public String getLimitStringMysql(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()- 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer( sql.length() + 100);
		if (offset < 0) {
			offset = 0;
		}
		pagingSelect.append(sql);
		pagingSelect.append(" limit " + offset + "," + limit);
		
		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		return pagingSelect.toString();
	}
}