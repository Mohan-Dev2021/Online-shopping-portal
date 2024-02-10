package com.online.shop.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "app_customer_address")
public class Address {

	@Id
	private String id = UUID.randomUUID().toString();

	@Field(name = "customer_name")
	private String customerName;

	@Field(name = "address_type")
	private Boolean primary;
	
	@Field(name = "address")
	private String address;

	@Field(name = "city")
	private String city;

	@Field(name = "state")
	private String state;

	@Field(name = "pin_code")
	private String pincode;

	@Field(name = "delivery_customer_number")
	private String deliveryCustomerNo;
}
