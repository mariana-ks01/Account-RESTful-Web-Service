package com.qa.account.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.account.entity.Account;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByAccountNumber(String accountNumber);
}
