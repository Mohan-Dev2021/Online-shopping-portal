package com.online.shop.dto;

import java.math.BigDecimal;
import java.util.List;

import com.online.shop.model.Customer;
import com.online.shop.model.Products;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private String id;

	private String orderId;

	private BigDecimal price;

	private double qty;

	private BigDecimal total;

	private CustomerDto customer;

	private AddressDto address;

	private List<ProductDto> products;

	private List<StatusDto> status;

	private List<PaymentDto> payment;
}
