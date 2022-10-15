package com.banking.demoapi.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.demoapi.entity.Account;
import com.banking.demoapi.repository.AccountRepository;

@Service
public class AccountService {
	
	
	@Autowired
	AccountRepository accountRepo;
	
	public Account getAccountByAccountNumber(String accountNumber)
	{
		Optional<Account> account = accountRepo.findByAccountNumberEquals(accountNumber);
		
		if(account.isPresent())
			return account.get();
		
		
			return null;
	}

	public Account updateBalance(Account account, BigDecimal updatedBalance) {
		
		account.setCurrentBalance(updatedBalance);
		return accountRepo.save(account);
		
	}


}
