package com.surfilter.self.jse.designer;

/**
 * 
* @ClassName: DecoratorFactory  
* @Description: 装饰者模式
* @author quanli 
* @date 2016年3月19日 上午10:59:18  
* 
* @version jdk1.7
 */
public class DecoratorFactory {

	public static void main(String[] args) {
		//装饰者模式,和我理解的对象的适配器很类似,持有一个类的实例,然后对目标方法进行前后修改,不影响内部逻辑
		DecoratorFactory df = new DecoratorFactory();
		Source source = df.new Source();
		source.say();
		System.out.println("=============");
		Decorate dec = df.new Decorate(source);
		dec.say();
		
	}
	
	class Source{
		public void say(){
			System.out.println("我是原始方法!");
		}
	}
	
	class Decorate{
		private Source source;

		public Decorate(Source source) {
			super();
			this.source = source;
		}
		
		public void say(){
			System.out.println("装饰");
			source.say();
			System.out.println("装饰");
		}
	}
}
