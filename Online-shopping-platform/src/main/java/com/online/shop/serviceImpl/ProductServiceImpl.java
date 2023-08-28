package com.online.shop.serviceImpl;

import org.springframework.stereotype.Service;

import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;

}
