package com.online.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
UPI("upi"),NETBANKING("netbanking"),COD("cod");
private String  paymentType;
}
