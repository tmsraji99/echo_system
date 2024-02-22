package com.bank.account.mapper;

import com.bank.account.dto.AccountDTO;
import com.bank.account.entity.AccountEntity;

public class AccountMapper {
	
	private AccountMapper() {
		
	}

	 public static AccountDTO mapToAccountsDto(AccountEntity accounts, AccountDTO accountsDto) {
	        accountsDto.setAccountNumber(accounts.getAccountNumber());
	        accountsDto.setAccountType(accounts.getAccountType());
	        accountsDto.setBranchName(accounts.getBranchName());
	        return accountsDto;
	    }

	    public static AccountEntity mapToAccountEntity(AccountDTO accountsDto, AccountEntity accounts) {
	        accounts.setAccountNumber(accountsDto.getAccountNumber());
	        accounts.setAccountType(accountsDto.getAccountType());
	        accounts.setBranchName(accountsDto.getBranchName());
	        return accounts;
	    }
}
