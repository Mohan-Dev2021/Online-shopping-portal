package com.online.shop.dto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  
	 private String id;
	 
	 @NotBlank(message = "productName should  not be blank")
	 @Size(min = 1, max = 50, message = "product Name should have atleast 1-20 characters")
	 @Field(name="productName")
	 private String productName;
	 
	 /* Not required
	 @NotBlank(message = "productId should  not be blank")*/
	 @Field(name="productId")
     private String productId;
	 
	 @NotNull(message="products should not be null")
//	 @Pattern(regexp = "(^$|[0-9]{10})", message = "quantity  should be numeric")
	 private Double quantity;
	 
	 
	 @NotNull(message="price should not be null")
//	 @Pattern(regexp = "(^$|[0-9]{7})", message = "price  should be numeric")
	 private BigDecimal price;
	 
	// @NotNull(message="image should not be blank")
	 private byte[] image;


}
