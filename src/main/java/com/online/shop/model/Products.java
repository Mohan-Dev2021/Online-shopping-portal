package com.online.shop.model;

import java.math.BigDecimal;
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
@Accessors(chain = true)
@Document(collection = "app_products")
public class Products {

	@Id
	private String id = UUID.randomUUID().toString();

	@Field(name = "product_name")
	private String productName;

	@Indexed
	@Field(name = "product_id")
	private String productId;

	@Field(name = "quantity")
	private Double quantity;

	@Field(name = "price")
	private BigDecimal price;

	@Field(name = "product_image")
	private byte[] image;
}
