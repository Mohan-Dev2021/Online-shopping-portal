package com.online.shop.service;

import com.online.shop.dto.CustomerDto;


public interface UserService {

	public CustomerDto getUserDetailsById(String id);
	public CustomerDto getUserDetailsByEmailId(String emailId);
	
}
