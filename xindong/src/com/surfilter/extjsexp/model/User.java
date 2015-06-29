package com.surfilter.extjsexp.model;

import java.util.Date;

public class User {
	//ObjectId
	private OId oid;
	//登录名
	private String username;
	//真实名称
	private String realname;
	//用户名
	private String password;
	//身份标识
	private Long identity;
	//性别
	private Long gender;
	//注册时间
	private Date time;
	
	public OId getOid() {
		return oid;
	}
	public void setOid(OId oid) {
		this.oid = oid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getIdentity() {
		return identity;
	}
	public void setIdentity(Long identity) {
		this.identity = identity;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
