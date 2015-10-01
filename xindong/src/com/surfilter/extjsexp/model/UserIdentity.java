package com.surfilter.extjsexp.model;

public class UserIdentity {

	private OId oid;
	//用户身份名称
	private String name;
	//用户身份标识
	private String value;
	public OId getOid() {
		return oid;
	}
	public void setOid(OId oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
