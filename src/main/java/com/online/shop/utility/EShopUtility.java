package com.online.shop.utility;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EShopUtility {

	
	private final ModelMapper modelMapper;

	public String getProductId() {
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		return "ECOM" + LocalDate.now().getMonthValue() + uuid.substring(length - 4);
	}

	public String getImageId() {
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		Integer midlength = uuid.length() / 2;
		System.out.println(midlength);
		return "EINTG" + LocalDate.now().getMonthValue() + uuid.substring(midlength, (midlength + 4));
	}

	public String getOrderId() {
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		return "ORDR" + LocalDate.now().getMonthValue() + uuid.substring(length - 4);

	}

	 //Simplify business logic to convert from Entity to Dto & Dto to Entity
	public <T> T toConvert(Object object, Class<?> classType) {
		T object1 = (T) modelMapper.map(object, classType);
		return object1;
	}
	

	public String getCartId() {
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		return "CART" + LocalDate.now().getMonthValue() + uuid.substring(length - 8); 
		
	}

	
	public <S, T> List<T> toConvertList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}


}
