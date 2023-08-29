package com.online.shop.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.model.Customer;
import com.online.shop.repository.UserRepo;

import lombok.AllArgsConstructor;

/**
 * User service - Service class which is used to authenticate and load the
 * granted authorities of the customer and roles in this application.
 * 
 * @category security module
 * @author Mohanlal
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

	/* Persistence layer for the customer */
	private final UserRepo userRepository;

	/* Jwt service - manipulating and validating jwt token */
	private final JwtService jwtService;

	/**
	 * Method to load the user by user name or email and get the granted authorities
	 * or roles
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = userRepository.findByEmailId(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		List<GrantedAuthority> authorities = new ArrayList<>();
		customer.getUserAuthorities().stream().forEach(cust -> {
			authorities.add(new SimpleGrantedAuthority(cust.getRole()));
		});
		return new User(customer.getEmailId(), customer.getPassword(), authorities);
	}

	/* Generate JWT token for user */
	public String generateToken(User user) {
		return jwtService.generateToken(user);
	}

}
