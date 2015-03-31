package proxy_method.dny;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import proxy_method.sta.DataSource;

public class ConnectionProxy implements InvocationHandler{
	
	private Connection connection;

	public ConnectionProxy(Connection connection) {
		this.connection = connection;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if( Connection.class.isAssignableFrom(proxy.getClass()) && method.getName().equals("close") ){
			DataSource.getInstance().close(connection);
			return null;
		}else{
			return method.invoke(connection, args);
		}
		
	}

	public Connection getConnectionProxy(){
		return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, this);
	}
}
