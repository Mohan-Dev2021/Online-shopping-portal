package com.online.shop.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.model.Customer;
import com.online.shop.repository.UserRepo;
import com.online.shop.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

	private final UserRepo userRepo;
	private final ModelMapper modelMap;

	@Override
	public CustomerDto getUserDetailsById(String id) {
		Customer customer = userRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
		CustomerDto customerDto = modelMap.map(customer, CustomerDto.class);
		AddressDto addressDto = modelMap.map(customer.getAddress(), AddressDto.class);
		customerDto.setAddress(addressDto);
		return customerDto;
	}

	@Override
	public CustomerDto getUserDetailsByEmailId(String emailId) {
		Customer customerEntiry = userRepo.findByEmailId(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
		CustomerDto customerDto = modelMap.map(customerEntiry, CustomerDto.class);
		AddressDto addressDto = modelMap.map(customerEntiry.getAddress(), AddressDto.class);
		customerDto.setAddress(addressDto);
		return customerDto;
	}

}
