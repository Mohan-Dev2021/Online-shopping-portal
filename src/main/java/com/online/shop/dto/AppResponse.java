package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T> {

	private Boolean status;

	private Integer statusCode;

	private T data;
}
