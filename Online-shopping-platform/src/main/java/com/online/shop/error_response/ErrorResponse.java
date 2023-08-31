package com.online.shop.error_response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends RuntimeException{

	private String errorMsg;
	private int status;
	private LocalDateTime timeStamp;
}
