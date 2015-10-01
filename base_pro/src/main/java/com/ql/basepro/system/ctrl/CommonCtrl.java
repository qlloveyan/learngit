package com.ql.basepro.system.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息控制类
 * @author ql
 */
@Controller
public class CommonCtrl {
	
	@RequestMapping(value="/toPage")
	public String toPage(HttpServletRequest request , String destPage){
		if( request.getParameter("id") != null ){
			request.setAttribute("id", request.getParameter("id").toString());
		}
		return "views/"+destPage;
	}
	
}
