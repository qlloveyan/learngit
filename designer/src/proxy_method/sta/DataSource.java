package proxy_method.sta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/helloworld?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123";
	
	//链接集合
	private static List<Connection> collections = new ArrayList<Connection>();
	
	//实体对象定义
	private static DataSource dataSource = null;
	
	static{
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private DataSource(){
		for( int i = 0 ; i < 10 ; i++){
			try {
				collections.add( DriverManager.getConnection(URL, USERNAME, PASSWORD));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//获取对象
	public synchronized static DataSource getInstance(){
		if( dataSource == null ){
			dataSource = new DataSource();
		}
		return dataSource;
	}
	
	//获取连接
	public Connection getCollection(){
		if( collections.size() > 0 ){
			return collections.remove(0);
		}
		return null;
	}
	
	//关闭连接
	public void close(Connection connection){
		if( connection != null){
			collections.add(connection);
		}
	}
	
	public static void getLong(){
		System.out.println(collections.size());
	}
}
