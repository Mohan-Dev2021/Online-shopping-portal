package com.online.shop.error_response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4591174442406123118L;
	private String errorMsg;
	private int status;
	private LocalDateTime timeStamp;
}
