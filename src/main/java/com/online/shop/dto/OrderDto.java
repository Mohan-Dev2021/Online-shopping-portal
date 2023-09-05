package com.online.shop.dto;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.online.shop.model.Address;
import com.online.shop.model.Customer;
import com.online.shop.model.Payment;
import com.online.shop.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	private String id ;
	
	private CustomerDto customer;

	private AddressDto address;

	private List<ProductDto> products;

	private  List<PaymentDto> payment;
}
