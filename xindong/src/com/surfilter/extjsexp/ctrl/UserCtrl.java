package com.surfilter.extjsexp.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.surfilter.extjsexp.model.User;
import com.surfilter.extjsexp.model.UserIdentity;
import com.surfilter.extjsexp.service.UserService;
import com.surfilter.extjsexp.util.Result;

@Controller
@RequestMapping("user")
public class UserCtrl {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Result login(HttpServletRequest request , String username , String password){
		Result result = new Result();
		User user = userService.checkUser(username, password);
		if( user != null ){
			System.out.println("登陆成功……");
			result.setFlag(true);
			result.setMsg("登陆成功");
			
			request.getSession().setAttribute("user", user);
		}else{
			System.out.println("登陆不成功!");
			result.setFlag(false);
			result.setMsg("用户名或密码错误");
			result.setObj(username);
		}
		
		return result;
	}
	
	@RequestMapping("/getIdentity")
	@ResponseBody
	public List<UserIdentity> getIdentity(){
		
		List<UserIdentity> result = new ArrayList<UserIdentity>();
		UserIdentity temp = new UserIdentity();
		temp.setName("普通用户");
		temp.setValue("2");
		result.add(temp);
		
		temp = new UserIdentity();
		temp.setName("管理员");
		temp.setValue("1");
		result.add(temp);
		
		return result;
	}
}
