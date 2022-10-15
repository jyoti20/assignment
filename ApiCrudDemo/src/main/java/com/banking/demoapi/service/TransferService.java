package com.banking.demoapi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.banking.demoapi.entity.TransferEntity;
import com.banking.demoapi.repository.TransferRepository;

@Service
public class TransferService {
	
	
	TransferRepository tranferRepo;
	
	
	
	TransferEntity createTransfer(String fromAccount, String toAccount, BigDecimal transactionAmount)
	{
		
		TransferEntity transferEntity = new TransferEntity();
		transferEntity.setFromAccount(fromAccount);
		transferEntity.setToAccount(toAccount);
		transferEntity.setTransactionAmount(transactionAmount);
		
		
		transferEntity = tranferRepo.save(transferEntity);
		return transferEntity;
		
	}
}
