package com.online.shop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author Sneka S
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerDto {

	private String id;

	@NotBlank(message = "FirstName should  not be blank")
	@Size(min = 2, max = 10, message = "First Name should have atleast 2-10 characters")
	@Pattern(regexp = "[a-zA-Z]+", message = "First name must not contain special characters & numerics")
	private String firstName;

	
	@NotBlank(message = "LastName should not be blank")
	@Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters & numerics")
	@Size(min = 1, max = 10, message = "Last Name should have atleast 2-10 characters")
	private String lastName;

	
	@NotBlank(message = "EmailId shouldn't be empty!")
	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Email is not valid" )
	private String emailId;

	
	@NotNull(message = "Please enter a password")
    @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Invalid password format")
	private String password;


	@NotBlank(message = "userName shouldn't be empty!")
	@Pattern(regexp = "[a-zA-Z]+", message = "userName must not contain special characters & numerics")
	private String userName;

	
	@NotBlank(message = "contactNo  shouldn't be empty!")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "contactNo no should be 0-9")
	private String contactNo;

	
	@Valid
	private AddressDto address;

}
