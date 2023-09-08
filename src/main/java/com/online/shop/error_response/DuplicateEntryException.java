package com.online.shop.error_response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class DuplicateEntryException extends EShopException{/**
	 * 
	 */
	private static final long serialVersionUID = 6531011536469652062L;
	
	public DuplicateEntryException(Integer errorCode, String message) {
	super();
	this.errorCode = errorCode;
	this.message = message;
}

	private Integer errorCode;
	
	private String message;

}
