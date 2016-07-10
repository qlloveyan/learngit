/**
 * Project Name:shutdown
 * File Name:JdbcUtils.java
 * Package Name:com.surfilter.self.db
 * Date:2016年2月16日下午2:58:58
 *
*/

package com.surfilter.self.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surfilter.self.db.model.User;
import com.surfilter.self.db.utils.SimpleMD5PasswordEncoder;

/**
 * ClassName:JdbcUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月16日 下午2:58:58 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class JdbcUtils {
	
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/etribe?useUnicode=true&characterEncoding=utf8";
	private static String username="root";
	private static String password="123456";
	
	private static String JDBC_FILE="jdbc.properties";
	
	private static Connection conn = null;
	
	private static Properties pro = null;
	
	static{
		try {
			if( pro == null ){
				pro = new Properties();
				pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream(JDBC_FILE));
				
				driver = pro.getProperty("driver");
				url = pro.getProperty("url");
				username = pro.getProperty("username");
				password = pro.getProperty("password");
			}
			
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			if(conn == null){
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		//目标:将数据库中的数据查出来之后通过反射加载到实体中
		List<User> resultList = new ArrayList<User>();
		Connection conn = getConn();
		String querySql = "SELECT * FROM t_user WHERE phone=? AND password=?";
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			pre = conn.prepareStatement(querySql);
			pre.setString(1, "13100710432");
			pre.setString(2, SimpleMD5PasswordEncoder.encode("123456"));
			
			result = pre.executeQuery();
			
			//获取查询到的所有记录的数据展示
			ResultSetMetaData resultColumn = result.getMetaData();
			int columnNum = resultColumn.getColumnCount();
			
			//获取用户的反射结果
			Class cls = User.class;
			Field[] objColumn = cls.getDeclaredFields();
			Method[] objMethod = cls.getMethods();
			
			User user = null;
			while (result.next()) {
				user = new User();
				for(int i = 1 ; i <= columnNum ; i++){
					String columnName = resultColumn.getColumnName(i);
					Object value = result.getObject(columnName);//获取列对应的参数值
					String methodName = "set"+columnName;
					if(value!=null){
						for(Method methodTemp:objMethod){
							if( methodName.equalsIgnoreCase(methodTemp.getName()) ){//列对应的set方法
								//为对象赋值
//								Method destMethod = user.getClass().getMethod(methodTemp.getName(), value.getClass());
//								destMethod.invoke(user, value);
								methodTemp.invoke(user, value);
							}
						}
					}
				}
				resultList.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( result != null ){
				try {
					result.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if( pre != null ){
				try {
					pre.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if( conn != null ){
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		for(User temp : resultList){
			System.out.println(temp.toString());
		}
	}
}

