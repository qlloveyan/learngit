package singleton_method;

public class TestThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(Singleton.getSingleton());
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
