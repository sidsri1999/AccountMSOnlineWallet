package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.Account;
import com.example.demo.entities.User;
import com.example.demo.exceptions.AccountAlreadyExistsException;
import com.example.demo.exceptions.AccountDoesNotExistsException;
import com.example.demo.exceptions.InsufficientFundsException;
import com.example.demo.exceptions.InvalidCredentialsException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserDoesNotExistsException;
import com.example.demo.services.AccountServiceImpl;
import com.example.demo.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AccountServiceImpl accountService;
	
	@PostMapping("/add")
	ResponseEntity<User> addUser(@RequestBody UserDto userDto){
		User user = userService.addUser(userDto);
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/{userName}/account/add")
	ResponseEntity<Account> addAccount(@PathVariable("userName") String userName){
		Account account = userService.addAccount(userName);
		ResponseEntity<Account> response = new ResponseEntity<Account>(account, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/{userName}")
	ResponseEntity<User> getUser(@PathVariable("userName") String userName){
		User user = userService.getUser(userName);
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.OK);
		return response;
	}
	
	//org.springframework.http.converter.HttpMessageConversionException
	//"Failed to convert from type [java.lang.String] to type [java.lang.Integer] for value '9876543210@ow'; nested exception is java.lang.NumberFormatException: For input string: \"9876543210@ow\""
	@GetMapping("/{userName}/account/")
	ResponseEntity<Account> getAccount(@PathVariable("userName") String accountId){
		System.out.println(accountId);
		Account account = accountService.getAccount(accountId);
		System.out.println("Helllo");
		ResponseEntity<Account> response = new ResponseEntity<Account>(account,HttpStatus.OK);
		System.out.println("Helllo2");
		return response;
	}
	
	@GetMapping("/")
	ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers();
		ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(users,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/{userName}/delete")
	ResponseEntity<Void> deleteUser(@PathVariable("userName") String userName){
		Boolean status = userService.deleteUser(userName);
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/{userName}/account/delete")
	ResponseEntity<Void> deleteAccount(@PathVariable("userName") String userName){
		Boolean status = userService.deleteAccount(userName);
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{userName}/changepassword")
	ResponseEntity<User> changePassword(@PathVariable("userName") String userName,@RequestBody UserDto userDto){
		User user = userService.changePassword(userName, userDto);
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.OK);
		return response;
	}
	//org.springframework.http.converter.HttpMessageConversionException
	//Not returning response
	@PutMapping("/{userName}/account/addmoney")
	ResponseEntity<Account> addMoney(@PathVariable("userName") String accountId,@RequestBody TransactionDto transactionDto){
		Account account = accountService.addMoney(accountId, transactionDto);
		ResponseEntity<Account> response = new ResponseEntity<Account>(account,HttpStatus.OK);
		System.out.println(response);
		return response;
	}
	
	@PutMapping("/{userName}/account/sendmoney")
	ResponseEntity<Account> sendMoney(@PathVariable("userName") String accountId,@RequestBody TransactionDto transactionDto){
		Account account = accountService.sendMoney(accountId, transactionDto);
		ResponseEntity<Account> response = new ResponseEntity<Account>(account,HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleExceptionUserAlreadyExists(UserAlreadyExistsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<String> handleExceptionAccountAlreadyExists(AccountAlreadyExistsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(AccountDoesNotExistsException.class)
	public ResponseEntity<String> handleExceptionAccountDoesNotExists(AccountDoesNotExistsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<String> handleExceptionInsufficientFunds(InsufficientFundsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(UserDoesNotExistsException.class)
	public ResponseEntity<String> handleExceptionUserDoesNotExists(UserDoesNotExistsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleExceptionInvalidCredentials(InvalidCredentialsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
	@ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        log.error("exception caught", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
	
	
}
