package com.online.shop.dto;

import java.math.BigDecimal;

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
	
	private byte[] image;
}
