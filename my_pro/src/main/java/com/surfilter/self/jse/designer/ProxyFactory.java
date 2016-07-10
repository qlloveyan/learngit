package com.surfilter.self.jse.designer;

/**
 * 
* @ClassName: ProxyFactory  
* @Description: 代理模式方法类
* @author quanli 
* @date 2016年3月19日 上午11:26:20  
*
 */
public class ProxyFactory {
	//代理模式在表现形式上和装饰模式很像,但是区别在于,装饰者模式重点在于对某个类或方法的装饰.代理模式强调对代理对象的控制
	
	/*举个例子  A类是原始功能的类， B是装饰模式中对A类的扩展之后的类， C是代理模式中对A类的扩展

	对于用户调用来说。 
	使用装饰模式， 用户更关系的是B的功能(包含A的原始功能)。
	使用代理模式，用户更关心A的功能。并不关系(c的功能)*/
	
	public static void main(String[] args) {
		ProxyFactory pf = new ProxyFactory();
		Client client = pf.new Client();
		
		Proxy proxy = pf.new Proxy(client);
		proxy.proxy(900);
		proxy.proxy(700);
	}
	
	class Client{
		public void rentHouse(){
			System.out.println("800左右的房子我要了!");
		}
	}
	
	class Proxy{
		
		private Client client;
		
		public Proxy(Client client) {
			super();
			this.client = client;
		}

		public void proxy(int money){
			if( money > 800  )
				System.out.println("房子不满足客户需要,我要过滤!");
			else
				client.rentHouse();
		}
	}
}
