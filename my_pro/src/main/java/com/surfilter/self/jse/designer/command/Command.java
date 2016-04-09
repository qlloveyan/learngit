/**
 * Project Name:my_pro
 * File Name:Command.java
 * Package Name:com.surfilter.self.jse.designer.command
 * Date:2016年4月7日下午8:46:18
 *
*/

package com.surfilter.self.jse.designer.command;
/**
 * ClassName:Command <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午8:46:18 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Command {

	//命令发送者,持有转发命令类
	private CommandSend commandSend;

	public CommandSend getCommandSend() {
		return commandSend;
	}

	public void setCommandSend(CommandSend commandSend) {
		this.commandSend = commandSend;
	}

	public void send(){
		System.out.println("命令发送!");
		commandSend.middleSend();
	}
}

