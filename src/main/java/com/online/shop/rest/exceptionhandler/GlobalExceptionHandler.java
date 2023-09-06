package com.online.shop.rest.exceptionhandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.online.shop.error_response.DuplicateEntryException;
import com.online.shop.error_response.EShopException;
import com.online.shop.error_response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus
	public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentNotValidException exception) {
		ErrorResponse response = new ErrorResponse();
		exception.getBindingResult().getAllErrors().stream().map((error) -> {
			new ErrorResponse().setErrorMsg(((FieldError) error).getField() + " - " + error.getDefaultMessage())
					.setStatus(400).setTimeStamp(LocalDateTime.now());
			return response;
		}).collect(Collectors.toList());
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(EShopException.class)
	@ResponseStatus
	public ResponseEntity<ErrorResponse> genericExceptionHandler(EShopException exception) {
		return ResponseEntity.status(exception.getErrorCode())
				.body(new ErrorResponse().setStatus(exception.getErrorCode()).setErrorMsg(exception.getMessage())
						.setTimeStamp(LocalDateTime.now()));
	}

	@ExceptionHandler(DuplicateEntryException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> exception(DuplicateEntryException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse().setStatus(400).setErrorMsg(e.getMessage()).setTimeStamp(LocalDateTime.now()));
	}

}
