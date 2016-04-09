/**
 * Project Name:my_pro
 * File Name:CommandSend.java
 * Package Name:com.surfilter.self.jse.designer.command
 * Date:2016年4月7日下午8:46:40
 *
*/

package com.surfilter.self.jse.designer.command;
/**
 * ClassName:CommandSend <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午8:46:40 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CommandSend {

	//命令的转发者持有命令的接收者
	private Receiver receiver;

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
	public void middleSend(){
		System.out.println("我转发命令!");
		receiver.doSth();
	}
	
}

