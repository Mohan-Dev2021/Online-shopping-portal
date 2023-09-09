package com.online.shop.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PaymentDto {

	private String id;

	private com.online.shop.enums.PaymentType PaymentType;

	private Map<String, Object> paymentSource;

	@CreatedDate
	@Field(name = "date_and_time")
	private LocalDateTime DateAndTime;;
}
