package com.online.shop.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.shop.model.Customer;
import com.online.shop.model.Manager;
import com.online.shop.repository.ManagerRepo;
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

	private final ManagerRepo managerRepo;

	/**
	 * Method to load the user by user name or email and get the granted authorities
	 * or roles
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> customer = userRepository.findByEmailId(username);
		if (customer.isEmpty()) {
			Manager admin = managerRepo.findByEmailId(username)
					.orElseThrow(() -> new UsernameNotFoundException("User doesn't exists!..."));
			List<GrantedAuthority> authorities = new ArrayList<>();
			admin.getManagerAuthorities().stream().forEach(adm -> {
				authorities.add(new SimpleGrantedAuthority(adm.getRole()));
			});
			return new User(admin.getEmailId(), admin.getPassword(), authorities);
		} else {
			List<GrantedAuthority> authorities = new ArrayList<>();
			customer.get().getUserAuthorities().stream().forEach(cust -> {
				authorities.add(new SimpleGrantedAuthority(cust.getRole()));
			});
			return new User(customer.get().getEmailId(), customer.get().getPassword(), authorities);
		}
	}

	/* Generate JWT token for user */
	public String generateToken(User user) {
		UserDetails userdetails= new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		return jwtService.generateToken(userdetails);
	}

}
