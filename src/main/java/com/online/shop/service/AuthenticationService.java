package com.online.shop.service;

import java.util.Set;

import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.LoginRequestDto;
import com.online.shop.dto.ManagerDto;

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
	 * managementSign-up - implementation for user registration
	 */
	public ManagerDto managementSignUp(ManagerDto manager);

	/*
	 * Authority - Updating user's authority
	 */
	public Boolean updateUserAuthority(String id, Set<String> authorities);

	public String signIn(LoginRequestDto requestDto);

}
