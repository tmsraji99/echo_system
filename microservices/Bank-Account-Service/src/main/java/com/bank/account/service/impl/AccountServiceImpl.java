package com.bank.account.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.account.constants.Constants;
import com.bank.account.dto.AccountDTO;
import com.bank.account.dto.CustomerDTO;
import com.bank.account.entity.AccountEntity;
import com.bank.account.entity.CustomerEntity;
import com.bank.account.exception.CustomerAccountException;
import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.CustomerMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import com.bank.account.service.IAccountService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class AccountServiceImpl implements IAccountService {

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;

	@Override
	public String createCustomer(CustomerDTO customerDto) {

		CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDto, new CustomerEntity());
		Optional<CustomerEntity> mobileNumber = customerRepository.findByMobileNumber(customerEntity.getMobileNumber());
		if (mobileNumber.isPresent()) {
			throw new CustomerAccountException("ERROR_4002", "Mobile Number already Present with Different User");
		}
		customerEntity.setCreationDate(LocalDateTime.now());
		customerEntity.setCreatedBy("User");
		CustomerEntity save = customerRepository.save(customerEntity);

		if (null != save.getCustomerId()) {

			AccountEntity account = accountRepository.save(createNewAccount(save,customerDto));

			return String.valueOf(account.getAccountNumber());
		} else {
			throw new CustomerAccountException("ERROR_4001", "Unable to create the customer");
		}

	}

	private AccountEntity createNewAccount(CustomerEntity customer,CustomerDTO customerDTO) {
		AccountEntity newAccount = new AccountEntity();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(customerDTO.getAccountDTO().getAccountType());
		newAccount.setBranchName(Constants.BRANCH_NAME);
		return newAccount;
	}

	@Override
	public CustomerDTO retrieveAccountByAccountNumber(Long accountNumber) {

		AccountEntity accountEntity = accountRepository.findById(accountNumber)
				.orElseThrow(() -> new CustomerAccountException("Error_4004", "No User found wirh the account number"));

		CustomerEntity customerEntity = customerRepository.findById(accountEntity.getCustomerId())
				.orElseThrow(() -> new CustomerAccountException("Error_4004", "No User found wirh the account number"));

		CustomerDTO customerDto = CustomerMapper.mapToCustomerDto(customerEntity, new CustomerDTO());
		customerDto.setAccountDTO(AccountMapper.mapToAccountsDto(accountEntity, new AccountDTO()));

		return customerDto;
	}

	@Override
	public CustomerDTO retrieveAccountByMobileNumber(String mobileNumber) {
		CustomerEntity entity = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new CustomerAccountException("Error_4003", "No User found wirh the mobile number"));

		AccountEntity orElseThrow = accountRepository.findByCustomerId(entity.getCustomerId())
				.orElseThrow(() -> new CustomerAccountException("Error_4003", "No User found wirh the mobile number"));
		CustomerDTO customerDto = CustomerMapper.mapToCustomerDto(entity, new CustomerDTO());
		customerDto.setAccountDTO(AccountMapper.mapToAccountsDto(orElseThrow, new AccountDTO()));

		return customerDto;
	}

	@Override
	public Boolean updateCustomer(CustomerDTO customerDTO) {

		AccountDTO accountDTO = customerDTO.getAccountDTO();

		if (null != accountDTO) {
			AccountEntity accountEntity = accountRepository.findById(accountDTO.getAccountNumber()).orElseThrow(
					() -> new CustomerAccountException("Error_4005", "No User found wirh the account number"));

			CustomerEntity customerEntity = customerRepository.findById(accountEntity.getCustomerId()).orElseThrow(
					() -> new CustomerAccountException("Error_4005", "No User found wirh the account number"));
			try {
				AccountMapper.mapToAccountEntity(accountDTO, accountEntity);
				accountRepository.save(accountEntity);

				CustomerMapper.mapToCustomerEntity(customerDTO, customerEntity);
				customerRepository.save(customerEntity);
			} catch (Exception e) {
				throw new CustomerAccountException("Error_4006", "User Updation failed");
			}
			return true;
		} else
			return false;

	}

}
