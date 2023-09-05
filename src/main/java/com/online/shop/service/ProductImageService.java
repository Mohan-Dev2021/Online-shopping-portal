package com.online.shop.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.online.shop.dto.ProductImageDto;

public interface ProductImageService {

	String insertProductImage(String id, MultipartFile imageFile) throws IOException;

	ProductImageDto getProductImageById(String productImageid);

	Boolean removeProductImageById(String id);
	
	

}
