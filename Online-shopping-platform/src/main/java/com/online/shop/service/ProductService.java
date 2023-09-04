package com.online.shop.service;

import com.online.shop.dto.PagableDto;
import com.online.shop.dto.ProductDto;

public interface ProductService {


	public ProductDto getProductById(String id);
	
	public ProductDto getProductByProductId(String productId);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);

	public PagableDto<?> getAllProductsWithPagination(int pageNo, int offset);

}
