package proxy_method.sta;

import java.sql.Connection;

public class StaticProxy {

	//创建
	public Connection createConnection(){
		DataSource dataSource = DataSource.getInstance();
		return dataSource.getCollection();
	}
	
	//关闭
	public void close(Connection connection){
		DataSource dataSource = DataSource.getInstance();
		dataSource.close(connection);
	}
}
