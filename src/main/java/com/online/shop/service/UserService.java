package com.online.shop.service;

import java.util.List;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;


public interface UserService {

	public CustomerDto getUserDetailsById(String id);
	public CustomerDto getUserDetailsByEmailId(String emailId);
	public CustomerDto updateUserById(String id,CustomerDto customerDto);
}
