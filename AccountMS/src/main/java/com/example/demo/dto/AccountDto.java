package com.example.demo.dto;

import com.example.demo.util.Status;

public class AccountDto {
	
	double accountBalance;
	Status status;
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountDto(double accountBalance, Status status) {
		super();
		this.accountBalance = accountBalance;
		this.status = status;
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
		return "AccountDto [accountBalance=" + accountBalance + ", status=" + status + "]";
	}
}
