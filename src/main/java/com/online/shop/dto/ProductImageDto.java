package com.online.shop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
	
    @NotNull
	@Valid
	private String imageFormat;
	
	private byte[] image;
}
