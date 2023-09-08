package com.online.shop.service;

import com.online.shop.dto.CartDto;

public interface CartService {
	public  CartDto saveCartDto(CartDto saveCartDto);

	public  CartDto  getCartDetails(String id);

	public  CartDto updateCartDto(CartDto saveCartDto);
}
