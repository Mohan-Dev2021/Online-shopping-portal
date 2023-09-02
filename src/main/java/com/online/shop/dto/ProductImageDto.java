package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Sneka S
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
	private String id;

	private String imageId;

	private String imageName;

	private String imageFormat;
}
