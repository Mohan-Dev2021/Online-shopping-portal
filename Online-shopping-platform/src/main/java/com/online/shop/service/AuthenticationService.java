package com.online.shop.service;

import java.util.Set;

import com.online.shop.dto.CustomerDto;

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

	/*
	 * Authority - Updating user's authority
	 */
	public Boolean updateUserAuthority(String id, Set<String> authorities);

	/*
	 * Login - user login to authenticate and generate the jwt
	 */
	public String signIn(String emailId, String password);

}
