package com.online.shop.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.OrderDto;
import com.online.shop.dto.PaymentDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.service.OrderService;
import com.online.shop.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<OrderDto> savePaymentDetails(@RequestBody PaymentDto savePayment,@RequestParam String id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.savePaymentDetails(savePayment, id));

	}

	@GetMapping("/{id}")
	public ResponseEntity<List<PaymentDto>> getPaymentById(@PathVariable String id){
		return  ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentById( id));
	}
	
	@GetMapping
       public ResponseEntity<OrderDto> getOrderPaymentById(@RequestParam String id){
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.getOrderPaymentById(id));
		
	}
	
}