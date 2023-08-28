package com.online.shop.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.model.Authorities;
import com.online.shop.model.Customer;
import com.online.shop.repository.UserRepo;
import com.online.shop.service.AuthenticationService;

/**
 * Authentication service implementation - Business layer where we're written
 * all the business logic which has to implement the authentication and
 * authorization by very efficient way
 * 
 * @category - security module
 * @author - Mohanlal
 * 
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepo userRepository;

	@Override
	public String signUp(Customer customer) {
		return userRepository.save(customer).getId();
	}

	@Override
	public Boolean updateUserAuthority(String id, Set<String> authorities) {
		Customer existingCustomer = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist!"));
		java.util.List<Authorities> userAuthorities = existingCustomer.getUserAuthorities();
		userAuthorities.addAll(userAuthorities);
		return true;
	}

}
