package com.online.shop.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;

public interface ProductService {

	public ProductDto getProductByProductId(String productId);

	public ProductDto getProductById(String id);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);

	public Boolean removeProductById(String id) throws NotFoundException;

	public PaginationDtoResponse<?> getAllProductsByPagination(Integer pageNo, Integer offset);


}