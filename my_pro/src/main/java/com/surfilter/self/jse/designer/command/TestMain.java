/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.designer.command
 * Date:2016年4月7日下午8:43:33
 *
*/

package com.surfilter.self.jse.designer.command;
/**
 * ClassName:TestMain <br/>
 * Function: 命令者模式. <br/>
 * Date:     2016年4月7日 下午8:43:33 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 命令者模式：命令发送者、命令转发者、命令接受者三者解耦合关联做一件事情的处理
	 */
	public static void main(String[] args) {
		Receiver rec = new Receiver();
		CommandSend cs = new CommandSend();
		cs.setReceiver(rec);
		Command command = new Command();
		command.setCommandSend(cs);
		
		command.send();
	}
}

