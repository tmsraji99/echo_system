package com.bank.loan.service;

import java.util.List;

import com.bank.loan.dto.LoanDto;
import com.bank.loan.resource.bean.Loan;

public interface ILoanService {

	String createLoan(Loan loan);
	
	LoanDto featchLoan(String loanNumber);
	
	List<LoanDto> featchAllLoans(String mobileNumbers);
	
	LoanDto UpdateLoan(String loanNumber);
	
	boolean deleteLona(String loanNumber);
}
