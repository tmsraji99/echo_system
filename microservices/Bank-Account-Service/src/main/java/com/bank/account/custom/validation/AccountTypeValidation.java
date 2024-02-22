package com.bank.account.custom.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountTypeValidation implements ConstraintValidator<AccountTypeConstraintValidation, String> {

	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<String> accountTypeList = Arrays.asList("SAVING", "CURRENT");
		return accountTypeList.contains(value.toUpperCase());

	}

}
