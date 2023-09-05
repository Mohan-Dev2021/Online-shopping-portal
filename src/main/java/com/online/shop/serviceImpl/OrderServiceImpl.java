package com.online.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.OrderDto;
import com.online.shop.dto.PaymentDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.model.Address;
import com.online.shop.model.Customer;
import com.online.shop.model.Order;
import com.online.shop.model.Payment;
import com.online.shop.model.Products;
import com.online.shop.repository.OrderRepo;
import com.online.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepo orderRepo;
	private final ModelMapper modelMapper;

	public OrderDto saveOrder(OrderDto saveOrderDto) {

		Customer customer = modelMapper.map(saveOrderDto.getCustomer(), Customer.class);

		Address address = modelMapper.map(saveOrderDto.getAddress(), Address.class);

		List<Products> product = saveOrderDto.getProducts().stream().filter(Objects::nonNull)
				.map(pro -> modelMapper.map(pro, Products.class)).collect(Collectors.toList());

		List<Payment> payment = saveOrderDto.getPayment().stream().filter(Objects::nonNull).map(pay -> {
			pay.setDateAndTime(LocalDateTime.now());
			return modelMapper.map(pay, Payment.class);
		}).collect(Collectors.toList());
		Order orderEntity = modelMapper.map(saveOrderDto, Order.class);
		orderEntity.setCustomer(customer);

		orderEntity.setAddress(address);

		orderEntity.setProducts(product);

		orderEntity.setPayment(payment);

		Order saveOrder = orderRepo.save(orderEntity);

		OrderDto orderDto = modelMapper.map(saveOrder, OrderDto.class);

		CustomerDto customerDto = modelMapper.map(saveOrder.getCustomer(), CustomerDto.class);

		AddressDto addressDto = modelMapper.map(saveOrder.getAddress(), AddressDto.class);

		List<ProductDto> productDto = saveOrder.getProducts().stream().filter(Objects::nonNull)
				.map(pro -> modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());

		List<PaymentDto> paymentDto = saveOrder.getPayment().stream().filter(Objects::nonNull).map(pay -> {
			pay.setDateAndTime(LocalDateTime.now());
			return modelMapper.map(pay, PaymentDto.class);
		}).collect(Collectors.toList());

		orderDto.setCustomer(customerDto);

		orderDto.setAddress(addressDto);

		orderDto.setProducts(productDto);

		orderDto.setPayment(paymentDto);
		return orderDto;
	}

}
