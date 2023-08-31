package com.online.shop.service;

import java.util.List;

import com.online.shop.dto.ProductDto;

public interface ProductService {
	
	public ProductDto getProductByProductId(String productId );
	
	public ProductDto getProductById(String id);

	public List <ProductDto> getAllproduct();
      

}
