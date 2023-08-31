package com.online.shop.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Address - Customer's address maintained by this entity with seperate data
 * 
 * @category - Associate Address module
 * @author Mohanlal
 */
@Data
@Accessors(chain = true)
public class Address {

	@Id
	private String id = UUID.randomUUID().toString();

	@Field(name = "address_line")
	private String addressLine;

	@Field(name = "city")
	private String city;

	@Field(name = "state")
	private String state;

	@Field(name = "pincode")
	private String pincode;
}
