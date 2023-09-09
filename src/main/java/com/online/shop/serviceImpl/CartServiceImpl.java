package com.online.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.online.shop.dto.CartDto;
import com.online.shop.dto.PaymentDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.error_response.EShopException;
import com.online.shop.model.Cart;
import com.online.shop.model.Payment;
import com.online.shop.model.Products;
import com.online.shop.repository.CartRepo;
import com.online.shop.service.CartService;
import com.online.shop.utility.EShopUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepo cartRepo;
	private final ModelMapper modelMapper;
	private final EShopUtility utility;

	@Override
	public CartDto getCartDetails(String id) {

		Optional<Cart> cartEntity = cartRepo.findById(id);

		Cart getCart = modelMapper.map(cartEntity, Cart.class);

		CartDto cartDto = modelMapper.map(getCart, CartDto.class);

		return cartDto;
	}

	@Override
	public CartDto saveCartDto(CartDto saveCartDto) {

		List<Products> product = saveCartDto.getProducts().stream().filter(Objects::nonNull)
				.map(pro -> modelMapper.map(pro, Products.class)).collect(Collectors.toList());

		List<Payment> payment = saveCartDto.getPayment().stream().filter(Objects::nonNull).map(pay -> {
			pay.setDateAndTime(LocalDateTime.now());
			return modelMapper.map(pay, Payment.class);
		}).collect(Collectors.toList());

		Cart cartEntity = modelMapper.map(saveCartDto, Cart.class);

		cartEntity.setCartId(utility.getCartId());

		cartEntity.setProducts(product);

		cartEntity.setPayment(payment);

		Cart savecartEntity = cartRepo.save(cartEntity);

		CartDto cartDto = modelMapper.map(savecartEntity, CartDto.class);

		List<ProductDto> productDto = savecartEntity.getProducts().stream().filter(Objects::nonNull)
				.map(pro -> modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());

		List<PaymentDto> paymentDto = savecartEntity.getPayment().stream().filter(Objects::nonNull).map(pay -> {
			pay.setDateAndTime(LocalDateTime.now());
			return modelMapper.map(pay, PaymentDto.class);
		}).collect(Collectors.toList());

		cartDto.setProducts(productDto);

		cartDto.setPayment(paymentDto);

		return cartDto;
	}

	@Override
	public CartDto updateCartDto(CartDto saveCartDto) {
		Optional<Cart> cartDto = cartRepo.findById(saveCartDto.getId());
		if (cartDto.isPresent()) {
			throw new EShopException(406, "cart id doesn't exists!... - ");
		}
		Cart CartEntity = modelMapper.map(cartDto, Cart.class);
		Cart CartSave = cartRepo.save(CartEntity);
		CartDto cartsDto = modelMapper.map(CartSave, CartDto.class);
		return cartsDto;
	}

}
