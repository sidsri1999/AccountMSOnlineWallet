package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepo;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.Account;
import com.example.demo.entities.User;
import com.example.demo.exceptions.AccountAlreadyExistsException;
import com.example.demo.exceptions.AccountDoesNotExistsException;
import com.example.demo.exceptions.InvalidCredentialsException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserDoesNotExistsException;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	AccountServiceImpl accountService;

	@Transactional
	@Override
	public User addUser(UserDto userDto) {
		List<User> users = getAllUsers();
		for(User user : users) {
			if(user.getPhoneNumber().equals(userDto.getPhoneNumber())){
				throw new UserAlreadyExistsException("User with this phone no: "+userDto.getPhoneNumber()+" already exists!!");
			}
		}
		User user = new User();
		user.setLoginName(userDto.getLoginName());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUserName(userDto.getPhoneNumber()+"@ow");
		User user1 = userRepo.save(user);
		return user1;
	}
	
	@Transactional
	@Override
	public Account addAccount(String userName) {
		User user = userRepo.findByUserName(userName).get(0);
		if(user.getAccount()!=null) {
			throw new AccountAlreadyExistsException("User is already linked with an account");
		}
		Account account = accountService.addAccount(userName);
		user.setAccount(account);
		userRepo.save(user);
		return account;
	}

	@Override
	public User getUser(String userName) {
		User user = userRepo.findByUserName(userName).get(0);
		if(user==null) {
			throw new UserDoesNotExistsException("User does not exists");
		}
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Transactional
	@Override
	public boolean deleteUser(String userName) {
		User user = userRepo.findByUserName(userName).get(0);
		if(user==null) {
			throw new UserDoesNotExistsException("User does not exists");
		}
		if(user.getAccount()!=null) {
			accountService.deleteAccount(userName);
		}
		userRepo.deleteByUserName(userName);
		return true;
	}

	@Transactional
	@Override
	public boolean deleteAccount(String userName) {
		User user = userRepo.findByUserName(userName).get(0);
		if(user==null) {
			throw new UserDoesNotExistsException("User does not exists");
		}
		if(user.getAccount()!=null) {
			accountService.deleteAccount(userName);
		}else {
			throw new AccountDoesNotExistsException("User with userName "+userName+" does not exists");
		}
		user.setAccount(null);
		userRepo.save(user);
		return true;
	}
	
	@Transactional
	@Override
	public User changePassword(String userName, UserDto userDto) {
		User user = userRepo.findByUserName(userName).get(0);
		if(user==null) {
			throw new UserDoesNotExistsException("User does not exists");
		}
		if(user.getPhoneNumber().equals(userDto.getPhoneNumber()) && user.getLoginName().equalsIgnoreCase(userDto.getLoginName())) {
			user.setPassword(userDto.getPassword());
			User user2 = userRepo.save(user);
			return user2;
		}else {
			throw new InvalidCredentialsException("Unable to verfiy");
		}
	}

}
