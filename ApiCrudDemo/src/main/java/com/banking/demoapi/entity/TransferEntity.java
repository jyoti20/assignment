package com.banking.demoapi.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;


@Table(name="Transfer_Track")
@Entity
public class TransferEntity {
		
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;
	
	@NonNull
	private String fromAccount;
	
	@NonNull
	private String toAccount;
	
	@NonNull
	private String transferRequestId;

	@NonNull
    private BigDecimal transactionAmount;

    @CreationTimestamp
    @Column(name="transaction_Date_Time")
    private Date transactionDateTime;

    public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}



	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

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

	
    
    

}
