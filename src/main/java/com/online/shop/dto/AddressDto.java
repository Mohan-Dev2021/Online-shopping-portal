package com.online.shop.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class AddressDto {

	@Field(name = "id")
	private String id;

	@Field(name = "customerName")
	private String customerName;

	@Field(name = "address_type")
	private Boolean primary;

	@NotBlank(message = "addressLine should  not be blank")
	@Size(min = 1, max = 180, message = "address should have at least 1-180 characters")
	@Field(name = "address")
	private String address;

	@NotBlank(message = "city should  not be blank")
	@Size(min = 1, max = 15, message = "city should have atleast 1-15 characters")
	@Pattern(regexp = "[a-zA-Z]+", message = "city must not contain special characters & numerics")
	private String city;

	@NotBlank(message = "state should  not be blank")
	@Size(min = 1, max = 15, message = "address should have atleast 1-15 characters")
	@Pattern(regexp = "[a-zA-Z]+", message = "state must not contain special characters & numerics")
	private String state;
	
	// pin code is not applicable for pattern validation as integer so changed to string
	@Size(min = 1, max = 6, message = "pincode should have atleast 1-6 characters")
	private String pincode;

	@Field(name = "delivery_customer_number")
	private String deliveryCustomerNo;


}
