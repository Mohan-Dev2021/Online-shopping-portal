package com.online.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.PaymentDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.dto.ProductImageDto;
import com.online.shop.error_response.EShopException;
import com.online.shop.error_response.OrderNotFoundException;
import com.online.shop.model.Customer;
import com.online.shop.model.Order;
import com.online.shop.model.Payment;
import com.online.shop.model.Products;
import com.online.shop.repository.OrderRepo;
import com.online.shop.repository.PaymentRepo;
import com.online.shop.service.PaymentService;
import com.online.shop.utility.EShopUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final OrderRepo orderRep;

	private final ModelMapper mapper;

	private final PaymentRepo paymentRepo;
	
	private final EShopUtility utility;

	@Override
	public OrderDto savePaymentDetails(PaymentDto savePayment, String id) {
		Order orderDetails = orderRep.findById(id).get();
		List<Payment> paymentDetails = new ArrayList<>();
		Payment payment = mapper.map(savePayment, Payment.class);
		paymentDetails.add(payment);
		orderDetails.setPayment(paymentDetails);
		paymentRepo.save(payment);
		Order savedorder = orderRep.save(orderDetails);
		OrderDto orderDto = mapper.map(savedorder, OrderDto.class);
		return orderDto;
	}

	@Override
	public List<PaymentDto> getPaymentById(String id) {
		Optional<Payment> payment = paymentRepo.findById(id);
		List<PaymentDto> paymentDetails = payment.stream().map(pay -> mapper.map(pay, PaymentDto.class))
				.collect(Collectors.toList());
		return paymentDetails;
	}

	@Override
	public OrderDto getOrderPaymentById(String id) {
		Order existingOrder = orderRep.findById(id).orElseThrow(
				() -> new OrderNotFoundException().setErrorCode(404).setMessage("Order id not found-" + id));
		OrderDto orderDto=utility.toConvert(existingOrder,OrderDto.class);
		List<PaymentDto> paymentDto=utility.toConvertList(existingOrder.getPayment(),  PaymentDto.class);
	     orderDto.setPayment(paymentDto);
		return orderDto;
		

//		OrderDto orderDto = mapper.map(existingOrder, OrderDto.class);
//		Optional<Payment> payment = paymentRepo.findById(id);
//		List<PaymentDto> paymentdto = payment.stream().map(pay -> mapper.map(pay, PaymentDto.class))
//				.collect(Collectors.toList());
//		orderDto.setPayment(paymentdto);
//		return orderDto;
				
	}
		

	

}
