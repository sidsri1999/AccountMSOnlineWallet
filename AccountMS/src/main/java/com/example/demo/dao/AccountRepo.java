package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Account;

public interface AccountRepo extends JpaRepository<Account, String> {

	
	
}
