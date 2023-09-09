package com.online.shop.service;

import com.online.shop.dto.OrderDto;

public interface OrderService {

	public String saveOrder(OrderDto saveOrderDto);
	public OrderDto getOrderDetailsById(String id);
}
