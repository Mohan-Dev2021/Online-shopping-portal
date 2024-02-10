package com.online.shop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService getUserDetails;

	/**
	 * Api for GetUserDetailsById
	 * 
	 * @param customerDetails
	 * @return customerDto
	 * @category get customerDetaisById module
	 * @author Sneka S
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getUserDetailsById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(getUserDetails.getUserDetailsById(id));

	}

	/**
	 * Api for GetUserDetailsByEmailId
	 * 
	 * @param customerDetails
	 * @return customerDto
	 * @category get customerDetaisByEmailId module
	 * @author Sneka S
	 */
	@GetMapping
	public ResponseEntity<CustomerDto> getUserEmailId(@RequestParam String emailId) {
		return ResponseEntity.status(HttpStatus.OK).body(getUserDetails.getUserDetailsByEmailId(emailId));

	}

	/**
	 * Api for Update UserDetails By Id
	 * 
	 * @param customerDetails
	 * @return customerDto
	 * @category update customerDetaisById module
	 * @author Mohan SK
	 */
	@PutMapping
	public ResponseEntity<CustomerDto> updateUserById(@RequestParam String id, @RequestBody CustomerDto customerDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(getUserDetails.updateUserById(id, customerDto));

	}

}
