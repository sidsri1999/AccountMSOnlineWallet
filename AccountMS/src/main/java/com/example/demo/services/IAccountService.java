package com.example.demo.services;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entities.Account;

public interface IAccountService {
	
	public boolean deleteAccount(String accountId);
	
	public Account addAccount(String accountId);
	
	public Account addMoney(String accountId, TransactionDto transactionDto);
	
	public Account sendMoney(String accountId, TransactionDto transactionDto);
	
	public Account getAccount(String accountId);
	
}
