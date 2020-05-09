package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.Account;
import com.example.demo.entities.User;

public interface IUserService {
	
public User addUser(UserDto userDto);
	
	public Account addAccount(String userName);
	
	public User getUser(String userName);
	
	public List<User> getAllUsers();
	
	public boolean deleteUser(String userName);
	
	public User changePassword(String userName, UserDto userDto);
	
	public boolean deleteAccount(String userName);
	
}
