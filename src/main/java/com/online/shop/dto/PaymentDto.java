package com.online.shop.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDto {

	@Id
	private String id ;
	
	private com.online.shop.enums.PaymentType PaymentType;
	Map<String, Object> paymentSource = new HashMap<>();
	
     @CreatedDate
     @Field(name="date_and_time")
	 private LocalDateTime DateAndTime;;
}
