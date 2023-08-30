package com.online.shop.service;

import java.util.Optional;

import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.ProductDto;

public interface ProductService {
	
	public ProductDto getProductByProductId(String productId );
	
	public ProductDto getProductById(String id);


}
