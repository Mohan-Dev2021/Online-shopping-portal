package com.online.shop.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.CartDto;
import com.online.shop.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	
	@PostMapping
	public ResponseEntity<CartDto> saveCartDetails(@RequestBody CartDto saveCartDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.saveCartDto(saveCartDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartDto> getCartDetails(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.getCartDetails(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartDto> updateCartDetails(@PathVariable CartDto saveCartDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.updateCartDto(saveCartDto));
	}
}
