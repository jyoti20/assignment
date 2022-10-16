package com.banking.demoapi.model;

import java.math.BigDecimal;


public class TransferRequest {
	
	private String fromAccount;
	
	private String toAccount;
	
	private String transferRequestId;

    private BigDecimal transactionAmount;

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getTransferRequestId() {
		return transferRequestId;
	}

	public void setTransferRequestId(String transferRequestId) {
		this.transferRequestId = transferRequestId;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
