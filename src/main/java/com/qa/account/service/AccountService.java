package com.qa.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.account.entity.Account;
import com.qa.account.exceptions.AccountAlreadyExistsException;
import com.qa.account.exceptions.AccountNotFoundException;
import com.qa.account.repository.AccountRepository;
@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountRepository accRepository;

	@Override
	public Account saveAccount(Account account) throws AccountAlreadyExistsException {
		/*
		 * 1. Check whether account already exists with this number 2. If yes, throw
		 * AccountAlreadyExistsException 3. If no, save the account object into the
		 * database 4. Return the saved account
		 */

		Optional<Account> findByNumber = accRepository.findByAccountNumber(account.getAccountNumber());
		if (findByNumber.isPresent())
			throw new AccountAlreadyExistsException();
		else
			return accRepository.save(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accRepository.findAll();
	}

	@Override
	public Account getAccountById(Long id) throws AccountNotFoundException {
		Optional<Account> findById = accRepository.findById(id);
		if (!findById.isPresent())
			throw new AccountNotFoundException();
		else
			return findById.get();
	}

	@Override
	public Account updateAccount(Account account) throws AccountNotFoundException {
		Optional<Account> findById = accRepository.findById(account.getId());

		if (!findById.isPresent())
			throw new AccountNotFoundException();
		else {
			Account existingAcc = findById.get();
			//updating only the number of the account
			existingAcc.setAccountNumber(account.getAccountNumber());		
			return accRepository.saveAndFlush(existingAcc);
		}
	}

	@Override
	public boolean deleteAccountById(Long id) throws AccountNotFoundException {
		boolean status = false;
		Optional<Account> findById = accRepository.findById(id);
		if (!findById.isPresent())
			throw new AccountNotFoundException();
		else {
			accRepository.delete(findById.get());
			status = true;
		}

		return status;
	}

}