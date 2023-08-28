package com.online.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class AppSecurityConfig {

	/* User details service - fetch basic authentication details of user */
	private final UserServiceImpl userService;

	/* Authentication filter - For every request authorization */
	private final AuthenticationFilter authenticationFilter;

	/* Access denied handler - security exception handler */
	private final AccessDeniedHanlder accessDeniedHanlder;

	/**
	 * Security filter chain - we're handling all the authentication & authorization
	 * configurations including api end points and everything with proper exception
	 * handler
	 * 
	 * @param httpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/authentication/v1/sign-up").permitAll()
						.requestMatchers("/authentication/welcome-page").permitAll().anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
				.exceptionHandling((exception) -> exception.accessDeniedHandler(accessDeniedHanlder))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	/* Global password encoder bean */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* Authentication provider configuration with user service(external DB) */
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/* Authentication manager bean for user request */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/* CORS Filtration by each and every possible HTTP requests */
	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
