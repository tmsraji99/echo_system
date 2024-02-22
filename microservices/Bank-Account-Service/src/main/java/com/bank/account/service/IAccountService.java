package com.bank.account.service;

import com.bank.account.dto.CustomerDTO;

/**
 * 
 */
public interface IAccountService {

	/**
	 * 
	 * @param customerDto
	 */
	String createCustomer(CustomerDTO customerDto);
	/**
	 * 
	 * @param accountNumber
	 * @return
	 */

	CustomerDTO retrieveAccountByAccountNumber(Long accountNumber);

	CustomerDTO retrieveAccountByMobileNumber(String mobileNumber);
	
	Boolean updateCustomer(CustomerDTO customerDTO);
}
