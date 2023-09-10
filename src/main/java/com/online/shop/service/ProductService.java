package com.online.shop.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;

import jakarta.annotation.Resource;

public interface ProductService {

	public ProductDto getProductByProductId(String productId);

	public ProductDto getProductById(String id);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);

	public Boolean removeProductById(String id) throws NotFoundException;

	public PaginationDtoResponse<?> getAllProductsByPagination(Integer pageNo, Integer offset);

	public List<ProductDto> getAllProducts();



}