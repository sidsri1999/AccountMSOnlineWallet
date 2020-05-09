package com.example.demo.exceptions;

public class AccountDoesNotExistsException extends RuntimeException {

	public AccountDoesNotExistsException(String msg) {
		super(msg);
	}
}
