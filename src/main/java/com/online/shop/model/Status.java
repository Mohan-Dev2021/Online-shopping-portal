package com.online.shop.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.online.shop.enums.StatusType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Document(collection = "app_payment")
@Accessors(chain = true)
public class Status {
	@Id

	private String id = UUID.randomUUID().toString();

	Map<StatusType, Object> statusSource;

	@CreatedDate
	private LocalDateTime dateAndTime;
}
