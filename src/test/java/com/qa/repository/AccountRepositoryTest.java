package com.qa.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.account.entity.Account;
import com.qa.account.repository.AccountRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository accRepository;

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
		accRepository.deleteAll();
		accList = null;
	}
	
	@Test
	@DisplayName("save-account-test")
	@Disabled
	public void given_Account_To_Save_Should_Return_The_Saved_Account() {
		accRepository.save(acc1);
		Account savedAccount = accRepository.findById(acc1.getId()).get();
		assertNotNull(savedAccount);
		assertEquals("acc1", savedAccount.getName());
	}
	
	@Test
	@Disabled
	@DisplayName("get-account-list-test")
	public void given_GetAllAccounts_Should_Return_Account_List() {
		accRepository.save(acc1);
		accRepository.save(acc2);
		accRepository.save(acc3);
		
		List<Account> accList = accRepository.findAll();
		assertEquals(3, accList.size(),"account list size should be 3");
		assertEquals("acc1",accList.get(0).getName());
	}
	
	@Test
	@DisplayName("get-account-non-existing-id-test")
	//@Disabled
	public void given_Non_Existing_Id_Should_Return_Optional_Empty() {
		accRepository.save(acc1);
		assertThat(accRepository.findById(5L)).isEmpty();
	}
}
