package com.online.shop.service;

import com.online.shop.dto.ProductDto;

public interface ProductService {

	public ProductDto getProductByProductId(String productId);

	public ProductDto getProductById(String id);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);

}