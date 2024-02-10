package com.online.shop.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
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
        return utility.toConvert(customer, CustomerDto.class);
	}

	@Override
	public CustomerDto getUserDetailsByEmailId(String emailId) {
		Customer customerEntity = userRepo.findByEmailId(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return utility.toConvert(customerEntity, CustomerDto.class);
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
