package com.bank.loan.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

	private String apiPath;
	private LocalDateTime time;
	private String message;
	private Integer code;
	@JsonIgnore
	private Source source;
	

}
