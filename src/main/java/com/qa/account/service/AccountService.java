package com.qa.account.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.account.dto.AccountDTO;
import com.qa.account.entity.Account;
import com.qa.account.exceptions.AccountAlreadyExistsException;
import com.qa.account.exceptions.AccountNotFoundException;
import com.qa.account.repository.AccountRepository;
@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountRepository accRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Account saveAccount(Account account) throws AccountAlreadyExistsException {
		
		Account createdAccount = null;
		/*
		 * 1. Check whether account already exists with this number 2. If yes, throw
		 * AccountAlreadyExistsException 3. If no, save the account object into the
		 * database 4. Return the saved account
		 */

		Optional<Account> accountByNumber = this.accRepository.findByAccountNumber(account.getAccountNumber());
		if(accountByNumber != null) {
			throw new AccountAlreadyExistsException();
		} else {
		createdAccount = this.accRepository.save(account);
		}
		
		return createdAccount;
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

	@Override
	public List<AccountDTO> findAccountDetailsWithDTO() {
		return this.accRepository.findAll().stream().map(this::mapToAccountDTO).collect(Collectors.toList());
	}
	
	private AccountDTO mapToAccountDTO(Account account) {
		return this.modelMapper.map(account, AccountDTO.class);
	}


}
