package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserRepo extends JpaRepository<User, String>{

	//@Query("from user u where u.username=?1")
	public List<User> findByUserName(String userName);
	
	public void deleteByUserName(String userName);
	
}
