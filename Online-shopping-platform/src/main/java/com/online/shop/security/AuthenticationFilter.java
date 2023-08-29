package com.online.shop.security;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Authentication filter - Filter which is used to authorize each and every
 * request from the client and redirecting the relevant apis by validating the
 * json web token and credentails
 * 
 * @category Filter module
 * @author Mohanlal
 * 
 */
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter extends OncePerRequestFilter {

	/* White listing Urls */
	private final List<String> whiteListedUrls = List.of("/v1/sign-up", "/authentication/welcome-page");

	/* Jwt service - component used to manipulate web token */
	private final JwtService jwtService;

	/* User details - service which implement load by user name method */
	private final UserServiceImpl userService;

	/**
	 * Filter - Authorizing each and every request by validating jwt token and
	 * represent the details to security application context
	 * 
	 * @category - Filter module
	 * @author Mohanlal
	 */
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		/* Redirecting the request without authorizing the jwt token - permit All */
		if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")
				|| this.isUrlWhiteListed(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUserName(jwt);
		if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(userEmail);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * White listed Url collectively those are non authorizable
	 * 
	 * @param url
	 * @return
	 */
	private boolean isUrlWhiteListed(String url) {
		return (whiteListedUrls.stream().anyMatch(url::contains));
	}
}
