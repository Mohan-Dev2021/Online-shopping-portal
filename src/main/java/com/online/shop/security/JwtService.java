package com.online.shop.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Jwt service - Service which is used to generate the json web token with
 * secure credentials including secret key expiration claims and subject user
 * name i.e emailId of the user
 * 
 * @category Jwt module
 * @author Mohanlal
 */
@Service
public class JwtService {

	@Value("${token.signing.key}")
	private String jwtSigningKey;

	public static final long FORTY_FIVE_MINUTE_DURATION = 2700000;

	/*
	 * public static final long THREE_MONTH_DURATION = 7776000000L; public static
	 * final long ONE_MINUTE_DURATION = 60000;/*
	 * 
	 * 
	 * /* Method used to extract the username from the token
	 */
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/* Method used to generate the token based on the user details */
	public String generateToken(UserDetails userDetails) {
		Map<String, String> claims = new HashMap<>();
		userDetails.getAuthorities().stream().forEach(authority -> {
			claims.put("role", authority.getAuthority());
		});
		return token(claims, userDetails);
	}

	/* Method used to validate the user's jwt token */
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	/* Method used to extract the claim details from the user's token */
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}

	/* Main method used to manipulate the json web token for the user */
	private String token(Map<String, String> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + FORTY_FIVE_MINUTE_DURATION))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	/* Method used to check whether the user's token is expired or not */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/* Method used to extract the expiration details of the token */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/* Method to used extract all the claim details from the user */
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	/*
	 * Method used to encyrpt the signing key using SHA
	 */
	private java.security.Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}