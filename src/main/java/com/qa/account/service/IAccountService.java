package com.qa.account.service;

import java.util.List;

import com.qa.account.entity.Account;
import com.qa.account.exceptions.AccountAlreadyExistsException;
import com.qa.account.exceptions.AccountNotFoundException;

public interface IAccountService {
	//CRUD operations
		public Account saveAccount(Account account) throws AccountAlreadyExistsException;
		public List<Account> getAllAccounts();
		public Account getAccountById(Long id) throws AccountNotFoundException;
		public Account updateAccount(Account account) throws AccountNotFoundException;
		public boolean deleteAccountById(Long id) throws AccountNotFoundException;
}
