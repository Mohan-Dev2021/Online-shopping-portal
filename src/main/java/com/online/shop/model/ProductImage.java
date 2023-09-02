package com.online.shop.model;

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
@Document(collection = "app_productImage")
public class ProductImage {
	@Id
	private String id = UUID.randomUUID().toString();

	@Indexed
	@Field(name = "image_id")
	private String imageId;

	@Field(name = "image_name")
	private String imageName;

	@Field(name = "image_format")
	private String imageFormat;

	@Field(name = "product_image")
	private byte[] image;

}
