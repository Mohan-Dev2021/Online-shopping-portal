package com.online.shop.error_response;

public class OrderNotFoundException extends EShopException {
	public OrderNotFoundException(Integer errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 6531011536469652062L;

	private Integer errorCode;

	private String message;

}
