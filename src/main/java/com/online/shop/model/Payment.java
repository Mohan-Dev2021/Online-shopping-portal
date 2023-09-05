package com.online.shop.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.online.shop.enums.PaymentType;

@Data
@NoArgsConstructor
@Document(collection = "app_payment")
@Accessors(chain = true)
public class Payment {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	private PaymentType PaymentType;
	
	Map<String, Object> paymentSource = new HashMap<>();
	
	@CreatedDate
	 private LocalDateTime DateAndTime;
	
}
