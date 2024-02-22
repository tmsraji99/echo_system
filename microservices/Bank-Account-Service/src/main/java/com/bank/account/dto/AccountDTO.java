package com.bank.account.dto;

import com.bank.account.custom.validation.AccountTypeConstraintValidation;

import lombok.Data;

@Data
public class AccountDTO {

	@AccountTypeConstraintValidation
	private String accountType;
	private Long accountNumber;
	private String branchName;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
