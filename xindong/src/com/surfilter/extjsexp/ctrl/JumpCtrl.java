package com.surfilter.extjsexp.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpCtrl {

	@RequestMapping("/jump/{page}")
	public String jump(HttpServletRequest request ,@PathVariable String page){
		String[] paths = page.split("-");
		String path = "";
		for(String temp : paths){
			path += temp + "/";
		}
		path = path.substring(0, path.length()-1);
		System.out.println("跳转链接为[" + path + "]");
		return path;
	}
}
