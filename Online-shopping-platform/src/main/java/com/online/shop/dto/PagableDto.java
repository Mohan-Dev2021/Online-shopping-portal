package com.online.shop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PagableDto<T> {

	private T content;

	private long totalElements;

	private int totalPages;

	private int offset;

	private int pageNo;

	private int numberOfElements;
}
