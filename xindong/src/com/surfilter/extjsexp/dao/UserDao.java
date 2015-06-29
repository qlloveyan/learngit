package com.surfilter.extjsexp.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.surfilter.extjsexp.model.User;

@Repository
public class UserDao{ 
	
	@Autowired
    protected MongoTemplate mongoTemplate;
	
	public void save(){
		
	}

	public User checkUser(String username, String password) {
		User user = null;
		Query query = new Query();
		Criteria cr = new Criteria();
		query.addCriteria(cr.andOperator(Criteria.where("username").is(username),Criteria.where("password").is(password)));
		List<User> uList = mongoTemplate.find(query, User.class);
		System.out.println("长度：" + uList.size());
		return user;
	}
}
