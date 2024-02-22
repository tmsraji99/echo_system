package com.bank.loan.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {

	private Integer statusCode;
	private LocalDateTime time;
	private String message;
	private T data;
	
	
}
