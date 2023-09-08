package com.online.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.dto.OrderDto;
import com.online.shop.dto.PaymentDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.dto.StatusDto;
import com.online.shop.error_response.EShopException;
import com.online.shop.model.Address;
import com.online.shop.model.Customer;
import com.online.shop.model.Order;
import com.online.shop.model.Payment;
import com.online.shop.model.Products;
import com.online.shop.model.Status;
import com.online.shop.repository.OrderRepo;
import com.online.shop.repository.ProductRepo;
import com.online.shop.repository.UserRepo;
import com.online.shop.service.OrderService;
import com.online.shop.utility.EShopUtility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepo orderRepo;
	private final ModelMapper modelMapper;
	private final EShopUtility utility;
	private final ProductRepo productRepo;
	private final UserRepo userRepository;

	@Override
	public OrderDto getOrderDetailsById(String id) {
		Order orderEntity = orderRepo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
//		OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);
//		CustomerDto customerDto = modelMapper.map(orderEntity.getCustomer(), CustomerDto.class);
//		AddressDto addressDto = modelMapper.map(orderEntity.getAddress(), AddressDto.class);
//		List<ProductDto> productDto = orderEntity.getProducts().stream().filter(Objects::nonNull)
//				.map(pro -> modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());
//		List<StatusDto> statusDto = orderEntity.getStatus().stream().map(str -> {
//			str.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(str, StatusDto.class);
//		}).collect(Collectors.toList());
//		List<PaymentDto> paymentDto = orderEntity.getPayment().stream().filter(Objects::nonNull).map(pay -> {
//			pay.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(pay, PaymentDto.class);
//		}).collect(Collectors.toList());
		OrderDto orderDto=utility.toConvert(orderEntity, OrderDto.class);
		CustomerDto customerDto=utility.toConvert(orderEntity.getCustomer(),CustomerDto.class);
		AddressDto addressDto=utility.toConvert(orderEntity.getAddress(), AddressDto.class);
		List<ProductDto> productDto=utility.toConvertList(orderEntity.getProducts(), ProductDto.class);
		List<StatusDto> statusDto=utility.toConvertList(orderEntity.getStatus(),  StatusDto.class);
		List<PaymentDto> paymentDto=utility.toConvertList(orderEntity.getPayment(), PaymentDto.class);
		orderDto.setCustomer(customerDto);
		orderDto.setAddress(addressDto);
		orderDto.setProducts(productDto);
		orderDto.setStatus(statusDto);
		orderDto.setPayment(paymentDto);
		return orderDto;
	}
	
	
//	public OrderDto saveOrder(OrderDto saveOrderDto) {
//		Optional<Customer> customers = userRepository.findById(saveOrderDto.getId());
//		if(customers.isPresent()) {
//			throw new EShopException().setErrorCode(406).setMessage("CustomerId is not found");
//		}
//		Customer customer = modelMapper.map(customers, Customer.class);
//		Address address = modelMapper.map(saveOrderDto.getAddress(), Address.class);
//		List<Products> product = saveOrderDto.getProductsId().stream().filter(Objects::nonNull).map(pro -> {
//			Products pr = productRepo.findByProductId(pro.getProductId())
//					.orElseThrow(() -> new EShopException().setErrorCode(404).setMessage("Product not found"));
//			return modelMapper.map(pr, Products.class);
//		}).collect(Collectors.toList());
//		List<Status> status = saveOrderDto.getStatus().stream().map(str -> {
//			str.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(str, Status.class);
//		}).collect(Collectors.toList());
//		List<Payment> payment = saveOrderDto.getPayment().stream().filter(Objects::nonNull).map(pay -> {
//			pay.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(pay, Payment.class);
//		}).collect(Collectors.toList());
//		Order orderEntity = modelMapper.map(saveOrderDto, Order.class);
//		orderEntity.setOrderId(utility.getOrderId());
//		orderEntity.setCustomer(customer);
//		orderEntity.setAddress(address);
//		orderEntity.setProducts(product);
//		orderEntity.setStatus(status);
//		orderEntity.setPayment(payment);

//		Order saveOrder = orderRepo.save(orderEntity);
//		OrderDto orderDto = modelMapper.map(saveOrder, OrderDto.class);
//		CustomerDto customerDto = modelMapper.map(saveOrder.getCustomer(), CustomerDto.class);
//		AddressDto addressDto = modelMapper.map(saveOrder.getAddress(), AddressDto.class);
//		List<ProductDto> productDto = saveOrder.getProducts().stream().filter(Objects::nonNull)
//				.map(pro -> modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());
//		List<StatusDto> statusDto = saveOrder.getStatus().stream().map(str -> {
//			str.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(str, StatusDto.class);
//		}).collect(Collectors.toList());
//		List<PaymentDto> paymentDto = saveOrder.getPayment().stream().filter(Objects::nonNull).map(pay -> {
//			pay.setDateAndTime(LocalDateTime.now());
//			return modelMapper.map(pay, PaymentDto.class);
//		}).collect(Collectors.toList());
//		orderDto.setCustomerId(customerDto);
//		orderDto.setAddress(addressDto);
//		orderDto.setProductsId(productDto);
//		orderDto.setStatus(statusDto);
//		orderDto.setPayment(paymentDto);
//		return orderDto;
//	}

}
