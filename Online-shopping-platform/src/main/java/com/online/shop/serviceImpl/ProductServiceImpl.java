package com.online.shop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.online.shop.dto.ProductDto;
import com.online.shop.model.Products;
import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;
	

	private final ModelMapper modelMapper;

	@Override
	public ProductDto getProductByProductId(String productId) {
		Optional<ProductDto>  productDetails=productRepo.findByProductId(productId);
		return  productDetails.get();
	}

	@Override
	public ProductDto getProductById(String id) {
		Optional<Products> product =productRepo.findById(id);
		ProductDto product1=modelMapper.map(product, ProductDto.class);
		return product1;
	}

	





}
