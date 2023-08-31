package com.online.shop.serviceImpl;

import java.util.Arrays;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.model.Address;
import com.online.shop.model.Authorities;
import com.online.shop.model.Customer;
import com.online.shop.repository.UserRepo;
import com.online.shop.security.UserServiceImpl;
import com.online.shop.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	/* Repository layer - used interact with database */
	private final UserRepo userRepository;

	/* Model mapper - mapper conversion */
	private final ModelMapper modelMap;

	/* Encoder - password encoder */
	private final PasswordEncoder passwordEncoder;

	/* User details service - used to authenticate the user details */
	private final UserServiceImpl userService;

	/* Authentication - manager which is used authenticate the user details */
	private final AuthenticationManager authenticationManager;

	@Override
	public CustomerDto signUp(CustomerDto customer) {
		Customer saveCustomer = modelMap.map(customer, Customer.class);
		Address address = modelMap.map(customer.getAddress(), Address.class);
		saveCustomer.setAddress(address);
		saveCustomer.setUserAuthorities(Arrays.asList(new Authorities().setRole("ROLE_USER")));
		saveCustomer.setPassword(passwordEncoder.encode(saveCustomer.getPassword()));
		Customer saveDetailValues = userRepository.save(saveCustomer);
		CustomerDto customerDto = modelMap.map(saveDetailValues, CustomerDto.class);
		AddressDto addressDto = modelMap.map(saveDetailValues.getAddress(), AddressDto.class);
		customerDto.setAddress(addressDto);
		return customerDto;
	}

	@Override
	public Boolean updateUserAuthority(String id, Set<String> authorities) {
		Customer existingCustomer = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist!"));
		java.util.List<Authorities> userAuthorities = existingCustomer.getUserAuthorities();
		userAuthorities.addAll(userAuthorities);
		return true;
	}

	@Override
	public String signIn(String emailId, String password) {
		Customer existingCustomer = userRepository.findByEmailId(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist!"));
		if (passwordEncoder.matches(password, existingCustomer.getPassword())) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(existingCustomer.getEmailId(),
					existingCustomer.getPassword()));
		}
		String token = userService.generateToken(new User(existingCustomer.getEmailId(), existingCustomer.getPassword(),
				existingCustomer.getAuthorities()));
		return token;
	}

}
