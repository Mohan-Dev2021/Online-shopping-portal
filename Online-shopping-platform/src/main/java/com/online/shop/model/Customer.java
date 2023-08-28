package com.online.shop.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Customer - Main user table which has every information of the user whose are
 * all registered and using our application for shopping experience
 * 
 * @category Entity module
 * @author Mohanlal
 */
@Data
@NoArgsConstructor
@Document(collection = "app_customer")
@Accessors(chain = true)
public class Customer {

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

	@DBRef
	private Address address;

	@DBRef
	private List<Authorities> userAuthorities;
}
