/**
 * Project Name:shutdown
 * File Name:TestJetty.java
 * Package Name:com.surfilter.self.jetty
 * Date:2016年3月3日下午2:42:40
 *
*/

package com.surfilter.self.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * ClassName:TestJetty <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月3日 下午2:42:40 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestJetty extends AbstractHandler{

	public void handle(String param, Request arg1, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String taskId = request.getParameter("taskId");
		String taskId1 = arg1.getParameter("taskId");
		System.out.println(taskId);
		System.out.println(taskId1);
		System.out.println(sendResponse(true,"processing"));
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server(8081);
//			WebAppContext context = new WebAppContext();
//			context.setContextPath("ftpSync");
//			context.setResourceBase("");
			server.setHandler(new TestJetty());
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String sendResponse(boolean success,String msg){
		return "{\"success\":\""+(success?"true":"false")+"\",\"msg\":\""+msg+"\"}";
	}
}

