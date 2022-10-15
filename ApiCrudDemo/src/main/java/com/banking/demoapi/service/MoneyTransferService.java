package com.banking.demoapi.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.demoapi.entity.Account;
import com.banking.demoapi.entity.TransferEntity;
import com.banking.demoapi.model.TransferRequest;

@Service
public class MoneyTransferService {

	@Autowired 
	AccountService  accountService;
	
	@Autowired 
	TransferService  transferService;
	
	//@Transactional
	public boolean processTransaction(TransferRequest transferRequest)
	{
	 try
	 {
		 Account fromAccount = accountService.getAccountByAccountNumber(transferRequest.getFromAccount());
		
		Account toAccount = accountService.getAccountByAccountNumber(transferRequest.getToAccount());
		
		validateAccountBalance(fromAccount,transferRequest.getTransactionAmount());
		
		
		TransferEntity transferEntity = transferService.createTransfer(transferRequest.getFromAccount(), 
				transferRequest.getToAccount(), transferRequest.getTransactionAmount());
		
		
		doTransfer(fromAccount,toAccount, transferRequest.getTransactionAmount());
		
		
		return true;
	 }
	 catch(Exception e)
	 {
		 return false;
	 }
		
	}

	private void doTransfer(Account fromAccount, Account toAccount, BigDecimal transactionAmount) {
		
	  try
	  {
		  fromAccount = accountService.updateBalance(toAccount, toAccount.getCurrentBalance().add(transactionAmount));
		
		  toAccount = accountService.updateBalance(fromAccount, fromAccount.getCurrentBalance().subtract(transactionAmount));
	   }
	  catch(Exception e)
	  {
		  
	  }
	}

	private void validateAccountBalance(Account fromAccount, BigDecimal transactionAmount) throws NotEnoughBalanceException {
		if(fromAccount.getCurrentBalance().compareTo(transactionAmount) < 0)
		 throw new NotEnoughBalanceException("Not Enough Balance in account to Transfer ");
	}
}
