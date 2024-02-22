package com.bank.account.resource;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.constants.Constants;
import com.bank.account.dto.CustomerDTO;
import com.bank.account.dto.ErrorResponseDTO;
import com.bank.account.dto.ResponseDTO;
import com.bank.account.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Account Resource ", description = "Account Resource Info")
@RestController
public class AccountResource {

	private IAccountService accountService;

	@Operation(summary = "Create Account")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http status created"),
			@ApiResponse(responseCode = "400", description = "Http status Bad Request"),
			@ApiResponse(responseCode = "500", description = "Http status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	
	@PostMapping("/account/create")
	public ResponseEntity<ResponseDTO<String>> createAccount(@Valid @RequestBody CustomerDTO customer) {

		String accountNuber = accountService.createCustomer(customer);
		ResponseDTO<String> responseDTO = new ResponseDTO<String>(Constants.STATUS_201, LocalDateTime.now(), Constants.MESSAGE_201, accountNuber);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@GetMapping("/account/retrieve/accountnumber/{accountnumber}")
	public ResponseEntity<ResponseDTO<CustomerDTO>> featchAccountDetailsByAcoountNumber(
			@PathVariable(value = "accountnumber") final Long accountnumber) {
		CustomerDTO customerDTO = accountService.retrieveAccountByAccountNumber(accountnumber);
		ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>(Constants.STATUS_200, LocalDateTime.now(),
				Constants.MESSAGE_200, customerDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@GetMapping("/account/retrieve/mobilenumber/{mobilenumber}")
	public ResponseEntity<ResponseDTO<CustomerDTO>> featchAccountDetailsByMobileNumber(
			@PathVariable(value = "mobilenumber") final String mobilenumber) {

		CustomerDTO customerDTO = accountService.retrieveAccountByMobileNumber(mobilenumber);
		ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>(Constants.STATUS_200, LocalDateTime.now(),
				Constants.MESSAGE_200, customerDTO);
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

	}

	@PutMapping("/account/update")
	public ResponseEntity<ResponseDTO<String>> updateAccountDetails(@RequestBody CustomerDTO customerDTO) {

		boolean updateCustomer = accountService.updateCustomer(customerDTO);

		if (updateCustomer) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDTO<String>(Constants.STATUS_202,
					LocalDateTime.now(), Constants.MESSAGE_202, "Success"));
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseDTO<String>(Constants.STATUS_500, LocalDateTime.now(), Constants.MESSAGE_500, "Failure"));
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public AccountResource(IAccountService accountService) {
		this.accountService = accountService;
	}

}