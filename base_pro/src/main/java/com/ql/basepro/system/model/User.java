package com.ql.basepro.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体信息类
 * @author ql
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号码
	 */
	private String identityCard;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 大头像
	 */
	private String avatar;
	/**
	 * 小头像
	 */
	private String smallAvatar;
	/**
	 * 省id
	 */
	private Long provinceId;
	/**
	 * 省中文名称
	 */
	private String provinceName;
	/**
	 * 城市id
	 */
	private Long cityId;
	/**
	 * 城市中文名称
	 */
	private String cityName;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 账户状态
	 */
	private Long active;
	/**
	 * 用户类型
	 */
	private Long status;
	/**
	 * 地址
	 */
	private String addr;
	/**
	 * 标识位
	 */
	private String tokenId;
	/**
	 * 所在学校最新地址库时间戳
	 */
	private Date addrsUpdStamp;
	/**
	 * 个推客户端id 
	 */
	private String clientId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSmallAvatar() {
		return smallAvatar;
	}
	public void setSmallAvatar(String smallAvatar) {
		this.smallAvatar = smallAvatar;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getActive() {
		return active;
	}
	public void setActive(Long active) {
		this.active = active;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Date getAddrsUpdStamp() {
		return addrsUpdStamp;
	}
	public void setAddrsUpdStamp(Date addrsUpdStamp) {
		this.addrsUpdStamp = addrsUpdStamp;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
