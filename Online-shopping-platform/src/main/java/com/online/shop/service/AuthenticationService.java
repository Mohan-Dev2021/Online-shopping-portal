package com.online.shop.service;


import java.util.Set;


import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.ManagerDto;
import com.online.shop.model.Customer;

/**
 * Authentication - service layer which hs all the methods need to be
 * implemented in security module
 * 
 * @category - security service module
 * @author - Mohanlal
 */
public interface AuthenticationService {

	/*
	 * Sign-up - implementation for user registration
	 */
	public CustomerDto signUp(CustomerDto customer);
	
     public ManagerDto managementSignUp(ManagerDto manager);

	/*
	 * Authority - Updating user's authority
	 */
	public Boolean updateUserAuthority(String id,Set<String> authorities);

	

}
