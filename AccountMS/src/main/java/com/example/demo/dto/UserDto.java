package com.example.demo.dto;

public class UserDto {
	
	String userName;
	String password;
	String phoneNumber;
	String loginName;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String userName, String password, String phoneNumber, String loginName) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", loginName=" + loginName + "]";
	}
}
