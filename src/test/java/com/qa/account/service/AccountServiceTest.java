package com.qa.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.account.entity.Account;
import com.qa.account.exceptions.AccountAlreadyExistsException;
import com.qa.account.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@Mock
	private AccountRepository accRepository;
	
	@Autowired
	@InjectMocks
	private AccountService accService;
	
	Account acc1;
	Account acc2;
	Account acc3;
	
	List<Account> accList;
	
	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances
		 * Create dummy data
		 */
		acc1 = new Account(1L,"34523432", "acc1", "user1", "pass1");
		acc2 = new Account(2L,"45645645","acc2","user2", "pass2");
		acc3 = new Account(3L,"55645645","acc3","user3", "pass3");
		accList = Arrays.asList(acc1,acc2,acc3);
	}
	
	@AfterEach
	public void tearDown() {
		acc1 = acc2 = acc3 = null;
		accList = null;
	}
	
	@Test
	@DisplayName("save-account-test")
	@Disabled
	public void given_Account_To_Save_Should_Return_The_Saved_Account() throws AccountAlreadyExistsException {
		when(accRepository.findByAccountNumber(any())).thenReturn(null);
		when(accRepository.save(any())).thenReturn(acc1);
		Account savedAccount = accService.saveAccount(acc1);
		assertNotNull(savedAccount);
		assertEquals(1,savedAccount.getId());
		verify(accRepository,times(1)).findByAccountNumber(any());
		verify(accRepository,times(1)).save(any());
	}
	
//	@Test
//	@DisplayName("save-account-throws-exception-test")
//	
//	public void given_Existing_Account_To_Save_Method_Should_Throw_Exception() throws AccountAlreadyExistsException {
//		when(accRepository.findByAccountNumber(any())).thenReturn(acc1);
//		
//		//accService.saveAccount(acc1);
//		assertThrows(AccountAlreadyExistsException.class,()-> accService.saveAccount(acc1));
//	}
}
