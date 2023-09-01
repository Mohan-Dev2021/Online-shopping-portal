package com.online.shop.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Document(collection = "app_manager")
@Accessors(chain = true)
/**
 * Customer - Main manager table which has every information of the manager
 * whose are all registered and using our application for shopping experience
 * 
 * @category Entity module
 * @author Sneka S
 */
public class Manager implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id - Primary key
	 * 
	 * @category - Random string UUID
	 */
	@Id
	private String id = UUID.randomUUID().toString();

	@Field(name = "first_name")
	private String firstName;

	@Field(name = "last_name")
	private String lastName;

	@Indexed
	@Field(name = "email_id")
	private String emailId;

	@Indexed
	@Field(name = "user_name")
	private String userName;

	@Field(name = "password")
	private String password;

	@Field(name = "contact_no")
	private String contactNo;

	@Field(name = "is_active")
	private boolean isActive;

	@Field(name = "registered_at")
	@CreatedDate
	private LocalDateTime registeredAt;
	
	
	private List<Authorities> managerAuthorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return managerAuthorities.stream().map(manager -> new SimpleGrantedAuthority(manager.getRole()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isActive;
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
