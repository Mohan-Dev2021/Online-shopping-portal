package com.online.shop.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Global exception handler - Main handler which is used to handle the security
 * module exceptions
 * 
 * @category security exception handler
 * @author Mohanlal
 */
@Component
public class SecurityAccessDeniedHanlder implements AccessDeniedHandler {

	/* Handling over the request and response with exception and proper message */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(accessDeniedException.getLocalizedMessage());
	}

}
