package com.surfilter.scma.util;

import java.io.Serializable;

public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean flag;
	private String msg;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Result(boolean flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}
	public Result() {
		
	}
}
