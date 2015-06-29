package com.surfilter.extjsexp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surfilter.extjsexp.dao.UserDao;
import com.surfilter.extjsexp.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User checkUser(String username , String password){
		return userDao.checkUser(username, password);
	}
}
