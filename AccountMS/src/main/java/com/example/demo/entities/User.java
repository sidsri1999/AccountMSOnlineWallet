package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class User {
	@Id
	String userName;
	String password;
	@Column(unique=true)
	String phoneNumber;
	String loginName;
	@OneToOne(cascade = CascadeType.ALL)
	Account account;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String password, String phoneNumber, String loginName, Account account) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
		this.account = account;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", loginName="
				+ loginName + ", account=" + account + "]";
	}
	
	

}
