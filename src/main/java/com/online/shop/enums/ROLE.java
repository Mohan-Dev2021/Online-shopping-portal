package com.online.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ROLE {

	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private String roleName;
}
