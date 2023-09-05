package com.online.shop.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PaginationDtoResponse<T> {
	private List<T> content;
	private long totalElements;
	private int totalPages;
	private int offset;
	private int pageNo;
	private int numberOfElements;
	private boolean hasNext;
	private boolean hasPrevious;
	private boolean hasContent;
	private Sort sort;

}