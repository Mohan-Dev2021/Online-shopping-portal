package com.online.shop.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.dto.ProductImageDto;
import com.online.shop.model.Address;
import com.online.shop.model.Customer;
import com.online.shop.repository.UserRepo;
import com.online.shop.service.UserService;
import com.online.shop.utility.EShopUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

	private final UserRepo userRepo;
	private final ModelMapper modelMap;
	private final EShopUtility utility;

	@Override
	public CustomerDto getUserDetailsById(String id) {
		Customer customer = userRepo.findById(id)
				.orElse(new Customer());
		CustomerDto customerDto = utility.toConvert(customer, CustomerDto.class);
		AddressDto addressDto = utility.toConvert(customer.getAddress(), AddressDto.class);
		customerDto.setAddress(addressDto);
		return customerDto;
	}

	@Override
	public CustomerDto getUserDetailsByEmailId(String emailId) {
		Customer customerEntiry = userRepo.findByEmailId(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
		CustomerDto customerDto = utility.toConvert(customerEntiry, CustomerDto.class);
		AddressDto addressDto = utility.toConvert(customerEntiry.getAddress(), AddressDto.class);
		customerDto.setAddress(addressDto);
		return customerDto;
	}

	@Override
	public CustomerDto updateUserById(String id, CustomerDto customerDto) {
		Customer existsCustomer = userRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
		existsCustomer.setFirstName(customerDto.getFirstName());
		existsCustomer.setLastName(customerDto.getLastName());
		existsCustomer.setContactNo(customerDto.getContactNo());
		existsCustomer.setUserName(customerDto.getUserName());
		userRepo.save(existsCustomer);
		return utility.toConvert(existsCustomer, CustomerDto.class);
	}

}
