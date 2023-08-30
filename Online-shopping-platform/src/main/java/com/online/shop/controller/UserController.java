package com.online.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.CustomerDto;
import com.online.shop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService getUserDetails;

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getUserDetailsById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(getUserDetails.getUserDetailsById(id));

	}

	@GetMapping("/{emailId}")
	public ResponseEntity<CustomerDto> getUserEmailId(@PathVariable String emailId) {
		return ResponseEntity.status(HttpStatus.OK).body(getUserDetails.getUserDetailsByEmailId(emailId));

	}

}
