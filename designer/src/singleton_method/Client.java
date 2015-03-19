package singleton_method;

public class Client {

	public static void main(String[] args) {
		Thread t1 = new Thread( new TestThread(),"t1" );
		Thread t2 = new Thread( new TestThread(),"t2" );
		
		t1.start();
		t2.start();
	}
	
}
