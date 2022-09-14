package com.qa.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.entity.Account;
import com.qa.account.exceptions.AccountAlreadyExistsException;
import com.qa.account.exceptions.AccountNotFoundException;
import com.qa.account.service.AccountService;

@RestController
@RequestMapping("api/v1/account-service")
public class AccountController {
	
	@Autowired
	AccountService accService;
	
	/*
	 * It is responsible for sending the response to the client converting java
	 * objects to json by default along with the status code
	 * 
	 * 
	 */
	ResponseEntity<?> responseEntity;
	
	@PostMapping("/account")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account) throws AccountAlreadyExistsException {
		Account createdAccount;
		try {
			createdAccount = this.accService.saveAccount(account);
		} catch (AccountAlreadyExistsException e) {
			throw e;
		}
		responseEntity = new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/account")
	public ResponseEntity<?> getAllAccounts(){
		return new ResponseEntity<>(this.accService.getAllAccounts(), HttpStatus.OK);
	}

	//account/3 -> Path Variable
	
	@GetMapping("/account/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable("id") Long id) throws AccountNotFoundException {
		Account account;
		try {
			account = this.accService.getAccountById(id);
		} catch (AccountNotFoundException e) {
			throw e;
		}
		responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
		return responseEntity;
	}
	//account?id=3 -> RequestParameter
	@DeleteMapping("/account")
	public ResponseEntity<?> deleteAccountById(@RequestParam("id") Long id) throws AccountNotFoundException {
		boolean status;
		try {
			status = this.accService.deleteAccountById(id);
			responseEntity = new ResponseEntity<>("Account Deleted Successfully !!!", HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			throw e;
		}		
		return responseEntity;
	}
	
	@PutMapping("/account")
	public ResponseEntity<?> updateAccount(@RequestBody Account account) throws AccountNotFoundException{
		try {
			responseEntity = new ResponseEntity<>(accService.updateAccount(account),HttpStatus.OK);
		} catch(AccountNotFoundException e) {
			throw e;
		}		
		return responseEntity;
	}
	
	@GetMapping("/account/dto-details")
	public ResponseEntity<?> getAccountDTODetails(){
		return new ResponseEntity<>(accService.findAccountDetailsWithDTO(),HttpStatus.OK);
	}
	
}
