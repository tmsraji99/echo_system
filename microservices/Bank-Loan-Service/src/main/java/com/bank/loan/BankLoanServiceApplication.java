package com.bank.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(info = @Info(title = "Loan Service", version = "V1", description = "Loan Service Information") )
@SpringBootApplication
public class BankLoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankLoanServiceApplication.class, args);
	}

}
