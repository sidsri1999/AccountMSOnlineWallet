package com.example.demo.services;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountRepo;
import com.example.demo.dto.TransactionDto;
import com.example.demo.entities.Account;
import com.example.demo.exceptions.AccountAlreadyExistsException;
import com.example.demo.exceptions.AccountDoesNotExistsException;
import com.example.demo.exceptions.InsufficientFundsException;
import com.example.demo.util.Status;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	AccountRepo accountRepo;
	
	@Transactional
	@Override
	public boolean deleteAccount(String accountId) {
		System.out.println("Delete Account "+accountId);
		
		accountRepo.deleteById(accountId);
		return true;
	}
	
	@Transactional
	@Override
	public Account addAccount(String accountId) {
		Boolean exists = accountRepo.existsById(accountId);
		System.out.println(exists);
		if(exists==true) {
			throw new AccountAlreadyExistsException("User with accountId "+accountId+" already exists");
		}
		Account account = new Account();
		account.setAccountId(accountId);
		account.setAccountBalance(0);
		account.setStatus(Status.TRUE);
		Account account2 = accountRepo.save(account);
		return account2;
	}

	@Transactional
	@Override
	public Account addMoney(String accountId, TransactionDto transactionDto) {
		Boolean exists = accountRepo.existsById(accountId);
		if(exists == false) {
			throw new AccountDoesNotExistsException("User with accountId "+accountId+" does not exists");
		}
		Account account = accountRepo.getOne(accountId);
		account.setAccountBalance(account.getAccountBalance()+transactionDto.getAmount());
		account = accountRepo.save(account);
		return account;
	}

	@Transactional
	@Override
	public Account sendMoney(String accountId, TransactionDto transactionDto) {
		Boolean senderExists = accountRepo.existsById(accountId);
		if(senderExists==false) {
			throw new AccountDoesNotExistsException("User with accountId "+accountId+" does not exists");
		}
		Account senderAccount = accountRepo.getOne(accountId);
		Boolean recieverExists = accountRepo.existsById(transactionDto.getRecieverAccountId());
		System.out.println(recieverExists);
		if(recieverExists==false) {
			throw new AccountDoesNotExistsException("User with accountId "+transactionDto.getRecieverAccountId()+" does not exists");
		}
		Account recieverAccount = accountRepo.getOne(transactionDto.getRecieverAccountId());
		if(senderAccount.getAccountBalance()-transactionDto.getAmount()>=0) {
			senderAccount.setAccountBalance(senderAccount.getAccountBalance()-transactionDto.getAmount());
		}else {
			throw new InsufficientFundsException("Insufficient funds to send money");
		}
		Account account =accountRepo.save(senderAccount);
		recieverAccount.setAccountBalance(senderAccount.getAccountBalance()+transactionDto.getAmount());
		accountRepo.save(recieverAccount);
		return account;
	}

	@Override
	public Account getAccount(String accountId) {
		boolean exists = accountRepo.existsById(accountId);
		if(exists == false) {
			throw new AccountDoesNotExistsException("User with accountId "+accountId+" does not exists");
		}
		Account account = accountRepo.getOne(accountId);
		System.out.println(account);
		return account;
		
	}
	

}
