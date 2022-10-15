package com.banking.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.demoapi.entity.Account;
import com.banking.demoapi.entity.TransferEntity;
import com.banking.demoapi.model.TransferRequest;
import com.banking.demoapi.repository.AccountRepository;
import com.banking.demoapi.service.AccountService;
import com.banking.demoapi.service.MoneyTransferService;
import com.banking.demoapi.service.NotEnoughBalanceException;

@RestController
public class AccountController {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AccountService  accService;
	
	@Autowired
	MoneyTransferService  moneyTransferService;
	
	@GetMapping("/getAccount/{accountNumber}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable String accountNumber)
	{
		Account account =  accService.getAccountByAccountNumber(accountNumber);
		try {
			if(null != account)
			{
 				return new ResponseEntity<Account>(account,HttpStatus.OK);
			}
			
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@PostMapping("/initiateAmountTranser")
	public ResponseEntity<String> initiateAmountTransfer(@RequestBody TransferRequest transferRequest)
	{
		boolean status = false;
	   try {
		
		   status = moneyTransferService.processTransaction(transferRequest);
		   
		   String msg = "transaction successful : response " + status;
			return  new ResponseEntity<String>(msg,HttpStatus.OK);
	   }
	   catch(Exception e){
		   
		   String msg = "transaction unsuccessful : response " + status + " cause :" +e.getMessage();
			return  new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
		
	}
	
	@PostMapping("/save/account")
	public ResponseEntity<Account> save(@RequestBody Account account)
	{
		try {
			return new ResponseEntity<>(accountRepo.save(account), HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getAccounts")
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		List<Account> list =  accountRepo.findAll();
		try {
			if(list.size() == 0 || list.isEmpty())
			{
				return new ResponseEntity<List<Account>>(list,HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Account>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	

		
	

	
	
	
	
}
