package com.bank.loan.resource;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.dto.ResponseDTO;
import com.bank.loan.resource.bean.Loan;
import com.bank.loan.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Account Resource ", description = "Account Resource Info")
@RestController
public class LoanResource {

	private ILoanService loanService;
	
	@Operation(summary = "Create Loan")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http status created"),
			@ApiResponse(responseCode = "400", description = "Http status Bad Request"),
			@ApiResponse(responseCode = "500", description = "Http status Internal Server error", content = @Content(schema = @Schema(implementation = com.bank.loan.dto.ErrorResponseDTO.class))) })
	
	@PostMapping("lona/create")
	public ResponseEntity<ResponseDTO<String>> createLoan(@Valid @RequestBody Loan loan){
		String loanNumber= loanService.createLoan(loan);
		ResponseDTO<String> responseDTO = new ResponseDTO<>(HttpStatus.CREATED.value(), LocalDateTime.now(), HttpStatus.CREATED.getReasonPhrase(), loanNumber);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
}
