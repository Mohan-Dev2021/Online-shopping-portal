package com.online.shop.model;

import org.apache.kafka.common.Uuid;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * Authorities - we're maintaining the customer's role with this entity
 * 
 * @category Associate Entity module
 * @author Mohanlal
 */
@Data
public class Authorities {

	@Id
	private String id = Uuid.randomUuid().toString();

	@Field(name = "role")
	private String role;
}
