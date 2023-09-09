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
		OrderDto orderDto = utility.toConvert(orderEntity, OrderDto.class);
		CustomerDto customerDto = utility.toConvert(orderEntity.getCustomer(), CustomerDto.class);
		AddressDto addressDto = utility.toConvert(orderEntity.getAddress(), AddressDto.class);
		List<String> productDto = orderEntity.getProducts().stream().map(pro -> pro.getId())
				.collect(Collectors.toList());
		List<Products> products = productRepo.findAllById(productDto);
		List<ProductDto> productDtos = utility.toConvertList(products, ProductDto.class);
		StatusDto statusDto = utility.toConvert(orderEntity.getStatus(), StatusDto.class);
		List<PaymentDto> paymentDto = utility.toConvertList(orderEntity.getPayment(), PaymentDto.class);
		orderDto.setCustomer(customerDto);
		orderDto.setAddress(addressDto);
		orderDto.setProducts(productDtos);
		orderDto.setStatus(statusDto);
		orderDto.setPayment(paymentDto);
		return orderDto;
	}

	public String saveOrder(OrderDto saveOrderDto) {
		Optional<Customer> customers = userRepository.findById(saveOrderDto.getId());
		if (customers.isPresent()) {
			throw new EShopException(406, "CustomerId is not found");
		}
		Customer customer = modelMapper.map(customers, Customer.class);
		Address address = modelMapper.map(saveOrderDto.getAddress(), Address.class);
		List<Products> product = saveOrderDto.getProducts().stream().filter(Objects::nonNull).map(pro -> {
			Products pr = productRepo.findByProductId(pro.getId())
					.orElseThrow(() -> new EShopException(404, "Product not found"));
			return modelMapper.map(pr, Products.class);
		}).collect(Collectors.toList());
		StatusDto statusDto = saveOrderDto.getStatus();
		statusDto.setDateAndTime(LocalDateTime.now());
		Status status = modelMapper.map(statusDto, Status.class);
		List<Payment> payment = saveOrderDto.getPayment().stream().filter(Objects::nonNull).map(pay -> {
			pay.setDateAndTime(LocalDateTime.now());
			return modelMapper.map(pay, Payment.class);
		}).collect(Collectors.toList());
		Order orderEntity = modelMapper.map(saveOrderDto, Order.class);
		orderEntity.setOrderId(utility.getOrderId());
		orderEntity.setCustomer(customer);
		orderEntity.setAddress(address);
		orderEntity.setProducts(product);
		orderEntity.setStatus(status);
		orderEntity.setPayment(payment);
		Order saveOrder = orderRepo.save(orderEntity);
		return saveOrder.getId();
	}

}
