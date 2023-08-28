package com.online.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security - app security configuration class which is responsible to secure
 * the application by ensure the efficient authentication and authorization for
 * each and every request for an api
 * 
 * @category security configuration
 * @author Mohanlal
 * 
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain securityChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests((httpReq) -> httpReq
				.requestMatchers(HttpMethod.POST, "/online-shopping-platform/authentication/v1/sign-up").permitAll()
				.requestMatchers(HttpMethod.GET, "/online-shopping-platform/authentication/welcome-page").permitAll()
				.anyRequest().authenticated())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
