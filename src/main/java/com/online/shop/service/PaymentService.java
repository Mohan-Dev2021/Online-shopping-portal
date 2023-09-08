package com.online.shop.service;

import java.util.List;

import com.online.shop.dto.OrderDto;
import com.online.shop.dto.PaymentDto;

public interface PaymentService {

	public OrderDto savePaymentDetails(PaymentDto savePayment,String id);

	public List <PaymentDto> getPaymentById(String id);
	
	public OrderDto getOrderPaymentById(String id);

	



}
