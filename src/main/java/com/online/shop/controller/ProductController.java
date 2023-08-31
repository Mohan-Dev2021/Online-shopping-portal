package com.online.shop.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.ProductDto;
import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	private final ProductRepo productRepo;

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));

	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductByProductId(@PathVariable String productId) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByProductId(productId));
	}
}
