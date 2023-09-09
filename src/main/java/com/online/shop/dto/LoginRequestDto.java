package com.online.shop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

	@NotBlank(message = "emailId shouldn't be empty!")
	private String emailId;

	@NotBlank(message = "password shouldn't be empty!")
	private String password;
}
