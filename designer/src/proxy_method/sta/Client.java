package proxy_method.sta;

import java.sql.Connection;

public class Client {

	public static void main(String[] args) {
		
		StaticProxy proxy = new StaticProxy();
		Connection conn = proxy.createConnection();
		System.out.println(conn);
		DataSource.getLong();
		System.out.println("======================================");
		proxy.close(conn);
		DataSource.getLong();
	}
}
