package com.banking.demoapi.service;

public class NotEnoughBalanceException extends Exception {

	NotEnoughBalanceException(String msg){
	      super(msg);
	   }
}
