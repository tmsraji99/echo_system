package com.bank.loan.resource.bean;

import lombok.Data;

@Data
public class Loan {

	private String mobileNumber;
	
	private String loanNumber;
	
	private String loanType;
	
	private Double totalAmount;
	
	private Double amountPaid;
	
	private Double outstandingAmount;
}
