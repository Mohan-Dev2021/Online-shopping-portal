package com.online.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
	//remove order prefix
	PLACED ("Placed"),ORDERUPDATE("orderupdate"),DELIVERD("deleverd"),CANCELLED("cancelled");
	private String  status;
}
