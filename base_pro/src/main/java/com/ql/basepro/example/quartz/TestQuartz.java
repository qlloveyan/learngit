package com.ql.basepro.example.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ql.basepro.system.model.User;
import com.ql.basepro.system.service.UserService;

/**
 * 定时器使用实例
 * @author ql
 */
public class TestQuartz{

	@Autowired
	private UserService userService;
	
	protected void execute(){
//		System.out.println("定时器使用实例-开始!");
//		try {
//			List<User> userList = userService.list( new User() );
//			if( userList != null ){
//				System.out.println("当前系统用户数："+userList.size());
//			}else{
//				System.out.println("当前系统不存在用户!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("定时器使用实例-结束!");
	}

}
