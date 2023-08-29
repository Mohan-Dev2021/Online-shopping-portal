package com.online.shop.rest.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.online.shop.error_response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus
	public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException exception) {
		ErrorResponse response = new ErrorResponse();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();

			response.setErrorMsg(fieldName + " - " + errorMessage);
			response.setStatus(400);
			response.setTimeStamp(LocalDateTime.now());
		});
		return response;
	}


}
