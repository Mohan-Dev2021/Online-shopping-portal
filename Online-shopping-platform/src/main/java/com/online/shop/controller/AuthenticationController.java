package com.online.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.model.Customer;
import com.online.shop.service.AuthenticationService;

/**
 * Authentication controller - The gateway where we're handling all the
 * authentication and authorization related activities of the user's and
 * management person based on their roles and authorities
 * 
 * @version v_0.1
 * @category Security module
 * @author Mohanlal
 * 
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/welcome-page")
	public ResponseEntity<String> getWelcomePageOfapplication() {
		return ResponseEntity.status(HttpStatus.OK)
				.body("Welcome to Online shopping platform......Have a great shopping!!");
	}

	@PostMapping("/v1/sign-up")
	public ResponseEntity<String> signUp(@RequestBody Customer customer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signUp(customer));
	}
}
