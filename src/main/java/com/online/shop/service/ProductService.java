package com.online.shop.service;

import java.util.List;

import com.online.shop.dto.ProductDto;
import com.online.shop.model.Products;

public interface ProductService {

	public ProductDto getProductByProductId(String productId);

	public ProductDto getProductById(String id);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);


}