package com.online.shop.model;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Document(collection = "app_cart")
@Accessors(chain = true)
public class Cart {
	@Id
	private String id = UUID.randomUUID().toString();

	@Indexed
	@Field(name = "cart_id")
	private String  cartId;
	
	@Field(name="sub_total")
	private BigDecimal subTotal;
	
	private List<Products> products;

	private List<Payment> payment;
	
}
