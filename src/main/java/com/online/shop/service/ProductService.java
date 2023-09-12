package com.online.shop.service;

import java.io.ByteArrayOutputStream;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.multipart.MultipartFile;

import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;

import io.jsonwebtoken.io.IOException;

public interface ProductService {

	public ProductDto getProductByProductId(String productId);

	public ProductDto getProductById(String id);

	public ProductDto saveProduct(ProductDto saveProduct);

	public ProductDto updateProducts(ProductDto productDetails);

	public Boolean removeProductById(String id) throws NotFoundException;

	public PaginationDtoResponse<?> getAllProductsByPagination(Integer pageNo, Integer offset);

	public ByteArrayOutputStream getAllProducts() throws IOException, Exception;

//	public void save(MultipartFile file)throws IOException, Exception;

	public void saveFile(MultipartFile file) throws java.io.IOException;







}