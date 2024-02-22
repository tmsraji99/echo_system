package com.bank.loan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "c_loan")
@Setter
@Getter
public class LoanEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Long loanId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name="loan_number")
	private String loanNumber;
	@Column(name="loan_type")
	private String loanType;
	@Column(name="total_amount")
	private Double totalAmount;
	@Column(name="amount_paid")
	private Double amountPaid;
	@Column(name="outstanding_amount")
	private Double outstandingAmount;
	
}
