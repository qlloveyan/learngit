package singleton_method;

public class Singleton {
	
	//1、定义返回实体
	private static Singleton singleton = null;
	
	private Singleton(){//2、构造器私有化
		
	}
	
	//3、定义方法
	public synchronized static Singleton getSingleton(){
		if( singleton == null ){
			singleton = new Singleton();
		}
		return singleton;
	}
	
}
