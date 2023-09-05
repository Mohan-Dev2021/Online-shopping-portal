package com.online.shop.utility;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EShopUtility {

	public String getProductId() {
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		return "ECOM" + LocalDate.now().getMonthValue() + uuid.substring(length - 4);
	}

	public String getImageId() {
		String uuid = UUID.randomUUID().toString();
		Integer midlength = uuid.length() / 2;
		return "EINTG" + LocalDate.now().getMonthValue() + uuid.substring(midlength, (midlength + 4));
	}
}
