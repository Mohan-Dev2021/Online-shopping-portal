package com.online.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFormate {
	JPG("image/jpg"),PNG("image/png"),JPEG("image/jpeg");
	
	private String ImgFormate;
}
