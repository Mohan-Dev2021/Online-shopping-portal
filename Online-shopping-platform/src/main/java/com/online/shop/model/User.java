package com.online.shop.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User - Container which holds all the security related data of the customer
 * and inherited by spring security module to update the authorities and other
 * account related details without disturbing main table
 * 
 * @category Security module
 * @author Mohanlal
 * 
 */
public class User extends Customer implements UserDetails {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Authorities - Mapping customer's authorities with this UserDetails object
	 * which is implement by spring security. With this approach we can able to
	 * update the user authorities by efficient way
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getUserAuthorities().stream().map(user -> new SimpleGrantedAuthority(user.getRole()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
