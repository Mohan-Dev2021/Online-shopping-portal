package com.online.shop.dto;



import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

	private String id;

	private String cartId;
	
	private  BigDecimal subTotal;
	
	private List<ProductDto> products;

	private List<PaymentDto> payment;
	
}
