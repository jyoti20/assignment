package com.banking.demoapi.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.demoapi.entity.Account;
import com.banking.demoapi.entity.TransferEntity;
import com.banking.demoapi.model.TransferRequest;
import com.banking.demoapi.validationexception.NegativeBalanceException;
import com.banking.demoapi.validationexception.NotEnoughBalanceException;

@Service
public class MoneyTransferService {

	@Autowired 
	AccountService  accountService;
	
	@Autowired 
	TransferService  transferService;
	
	/**ProcessTransaction for given transferRequest
	* Update appropriate balances 
	* Create Transfer statement 
	*/
	@Transactional
	public boolean processTransaction(TransferRequest transferRequest)
	{
	 try
	 {
		 Account fromAccount = accountService.getAccountStatement(transferRequest.getFromAccount());
		
		Account toAccount = accountService.getAccountStatement(transferRequest.getToAccount());
		
		validateAccountBalance(fromAccount,transferRequest.getTransactionAmount());
		
		TransferEntity transferEntity = transferService.createTransfer(transferRequest.getFromAccount(), 
				transferRequest.getToAccount(), transferRequest.getTransactionAmount(), transferRequest.getTransferRequestId());
		
		
		doTransfer(fromAccount,toAccount, transferRequest.getTransactionAmount());
		
		
		return true;
	 }
	 catch(Exception e)
	 {
		 return false;
	 }
		
	}

	/**doTransfer given transactionAmount from source:fromAccount to the distination:toAccount
	* Update appropriate balances 
	* Create Transfer statement 
	*/
	private void doTransfer(Account fromAccount, Account toAccount, BigDecimal transactionAmount) {
		
	  try
	  {
		   accountService.updateBalance(toAccount, toAccount.getCurrentBalance().add(transactionAmount));
		
		   accountService.updateBalance(fromAccount, fromAccount.getCurrentBalance().subtract(transactionAmount));
	   }
	  catch(Exception e)
	  {
		  
	  }
	}
	
	private void validateAccountBalance(Account fromAccount, BigDecimal transactionAmount) throws NotEnoughBalanceException,NegativeBalanceException {
		if(fromAccount.getCurrentBalance().compareTo(transactionAmount) < 0)
		 throw new NotEnoughBalanceException("Not Enough Balance in account to Transfer ");
		
		if(transactionAmount.compareTo(BigDecimal.ZERO) > 0)
			 throw new NegativeBalanceException("Negative Balance : Invalid Amount ");
	}


}
