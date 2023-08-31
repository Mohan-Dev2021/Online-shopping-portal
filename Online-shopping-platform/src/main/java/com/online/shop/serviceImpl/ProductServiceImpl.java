package com.online.shop.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
		Optional<ProductDto> productDetails = productRepo.findByProductId(productId);
		return productDetails.get();
	}

	@Override
	public ProductDto getProductById(String id) {
		Optional<Products> product = productRepo.findById(id);
		ProductDto product1 = modelMapper.map(product, ProductDto.class);
		return product1;
	}

	@Override
	public ProductDto saveProduct(ProductDto saveProduct) {
		Products productDetails = modelMapper.map(saveProduct, Products.class);
		productDetails.setProductId(this.getProductId());
		Products saveProducts = productRepo.save(productDetails);
		ProductDto productDto = modelMapper.map(saveProducts, ProductDto.class);
		return productDto;
	}

	private String getProductId() {
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		return "ECOM" + LocalDate.now().getMonthValue() + uuid.substring(length - 4);
	}

	@Override
	public ProductDto updateProducts(ProductDto productDetails) {
		ProductDto product=productRepo.findByProductId(productDetails.getProductId()).get();
		Products productEntity=modelMapper.map(product, Products.class);
		Products product1=productRepo.save(productEntity);
		ProductDto productDto=modelMapper.map(product1,ProductDto.class);
		return  productDto;
		
	}	

}
