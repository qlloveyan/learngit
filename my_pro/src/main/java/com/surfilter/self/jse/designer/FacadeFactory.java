package com.surfilter.self.jse.designer;

/**
 * 
* @ClassName: FacadeFactory  
* @Description: 外观模式
* @author quanli 
* @date 2016年3月19日 上午11:44:37  
*
 */
public class FacadeFactory {

	//外观模式,将常见的表现形式包装在一起,最形象的比喻,就是电脑
	public static void main(String[] args) {
		FacadeFactory ff = new FacadeFactory();
		Computer computer = ff.new Computer();
		computer.start();
	}
	
	//机箱类
	class Case{
		public void start(){
			System.out.println("机箱电源打开!");
		}
	}
	
	//主板类
	class MotherBoard{
		public void start(){
			System.out.println("主板通电启动!");
		}
	}
	
	//外观,电脑类
	class Computer{
		
		private Case caseBox;
		private MotherBoard board;
		
		public void start(){
			caseBox = new Case();
			board = new MotherBoard();
			
			caseBox.start();
			board.start();
			System.out.println("电脑正常启动!");
		}
	}
}
