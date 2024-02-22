package com.bank.account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDTO {

	@NotBlank(message = "Name must be")
	private String name;
	@NotBlank(message = "email must be not null")
	private String email;
	private String mobileNumber;
	@Valid
	private AccountDTO accountDTO;
	
}