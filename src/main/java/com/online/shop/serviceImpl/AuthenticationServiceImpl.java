package com.online.shop.serviceImpl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.ManagerDto;
import com.online.shop.model.Authorities;
import com.online.shop.model.Customer;
import com.online.shop.model.Manager;
import com.online.shop.repository.ManagerRepo;
import com.online.shop.repository.UserRepo;
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

	private final UserRepo userRepository;
	private final ManagerRepo managerRepo;
	private final ModelMapper modelMap;

	@Override
	public CustomerDto signUp(CustomerDto customer) {
		Customer saveDetail = modelMap.map(customer, Customer.class);
		Customer saveDetailValues = userRepository.save(saveDetail);
		CustomerDto customerDto = modelMap.map(saveDetailValues, CustomerDto.class);
		return customerDto;
	}

	@Override
	public ManagerDto managementSignUp(ManagerDto manager) {
		Manager saveDetail = modelMap.map(manager, Manager.class);
		Manager saveDetailValues = managerRepo.save(saveDetail);
		ManagerDto ManagerDto = modelMap.map(saveDetailValues, ManagerDto.class);
		return ManagerDto;
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
