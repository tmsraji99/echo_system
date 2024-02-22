package com.bank.account.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.account.dto.ErrorResponseDTO;

@RestControllerAdvice
public class AccountGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerAccountException.class)
	public ResponseEntity<ErrorResponseDTO> handleException(CustomerAccountException exception, WebRequest webRequest) {

		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setMessage(exception.getMessage());
		errorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponseDTO.setTime(LocalDateTime.now());
		errorResponseDTO.setApiPath(webRequest.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleUserException(Exception exception, WebRequest webRequest) {

		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setMessage(exception.getMessage());
		errorResponseDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponseDTO.setTime(LocalDateTime.now());
		errorResponseDTO.setApiPath(webRequest.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Map<String, String> errorHashMap = new HashMap<>();

		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

		allErrors.stream().forEach(e -> {
			String field = ((FieldError) e).getField();
			String defaultMessage = e.getDefaultMessage();
			errorHashMap.put(field, defaultMessage);
		});
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setMessage(allErrors.get(0).getDefaultMessage());
		errorResponseDTO.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponseDTO.setTime(LocalDateTime.now());
		errorResponseDTO.setApiPath(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);

	}
}
