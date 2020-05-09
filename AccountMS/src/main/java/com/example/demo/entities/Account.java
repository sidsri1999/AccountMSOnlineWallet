package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.example.demo.util.Status;

@Component
@Entity
public class Account {
	
	@Id
	String accountId;
	double accountBalance;
	Status status;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String accountId, double accountBalance, Status status) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.status = status;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountBalance=" + accountBalance + ", status=" + status + "]";
	}
	
	
}
