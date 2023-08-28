package com.online.shop.model;

import org.springframework.data.annotation.Id;

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
	private String id;

	private String addressLine;

	private String city;

	private String state;

	private Integer pincode;
}
