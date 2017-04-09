package com.oneself.cloud.consumer.movie.feign.with.hystrix.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 204280193416956395L;

	private String userId;
	private String userName;
	private String userPassword;
	private String delFlag;

	public UserInfo() {
	}

	public UserInfo(String userId, String userName, String userPassword, String delFlag) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.delFlag = delFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
