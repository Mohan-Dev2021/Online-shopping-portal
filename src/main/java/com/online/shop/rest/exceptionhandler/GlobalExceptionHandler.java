package com.online.shop.rest.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.online.shop.error_response.DuplicateEntryException;
import com.online.shop.error_response.EShopException;
import com.online.shop.error_response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException exception) {
		ErrorResponse response = new ErrorResponse();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			response.setStatus(400);
			response.setErrorMsg(errorMessage);
			response.setTimeStamp(LocalDateTime.now());
		});
		return response;
	}

	@ExceptionHandler(EShopException.class)
	@ResponseStatus
	@ResponseBody
	public ErrorResponse genericExceptionHandler(EShopException exception) {
		return new ErrorResponse().setStatus(exception.getErrorCode()).setErrorMsg(exception.getMessage())
				.setTimeStamp(LocalDateTime.now());
	}

	@ExceptionHandler(DuplicateEntryException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse exception(DuplicateEntryException e) {
		return new ErrorResponse().setStatus(400).setErrorMsg(e.getMessage()).setTimeStamp(LocalDateTime.now());
	}

//	@ExceptionHandler(Exception.class)
//	@ResponseStatus
//	public ResponseEntity<ErrorResponse> generalException(Exception e) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new ErrorResponse(e.getLocalizedMessage(), 400, LocalDateTime.now()));
//	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse resourceNotFound(EShopException ex) {
		ErrorResponse response = new ErrorResponse();

		if (ex.getLocalizedMessage() == null) {
			return new ErrorResponse(response.getErrorMsg(), 400, response.getTimeStamp());
		}
		response.setStatus(ex.getErrorCode());
		response.setErrorMsg(ex.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		return new ErrorResponse(response.getErrorMsg(), 400, response.getTimeStamp());
	}

}
