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
	
	
	@NotBlank(message = "addressLine should  not be blank")
	@Size(min = 1, max = 50, message = "address should have atleast 1-50 characters")
	@Field(name = "addressLine")
	private String addressLine;

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


}
