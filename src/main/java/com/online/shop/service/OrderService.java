package com.online.shop.service;

import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.OrderDto;

public interface OrderService {

//	public OrderDto saveOrder(OrderDto saveOrderDto);
	public OrderDto getOrderDetailsById(String id);
}
