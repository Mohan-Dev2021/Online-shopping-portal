package com.online.shop.error_response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EShopError {

	IMAGE_FORMAT_INVALID(406, "Image file is invalid..");
	
	private Integer erroCode;
	private String message;
}
