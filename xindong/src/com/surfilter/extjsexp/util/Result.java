package com.surfilter.extjsexp.util;

public class Result {

	//操作成功标识
	private boolean flag;
	//操作结果描述
	private String msg;
	//操作返回对象
	private Object obj;
	
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
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
