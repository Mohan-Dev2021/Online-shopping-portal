package com.online.shop.serviceImpl;

import java.time.LocalDate;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.online.shop.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl {

	private static String getImageId() {
		
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		int Middilelength = uuid.length()/2;
		System.out.println(Middilelength);
		return "EINTG" + LocalDate.now().getMonthValue() + uuid.substring(Middilelength, Middilelength+4);
	}

	public static void main(String[] args) {
		
		System.out.println(getImageId());
	}
}
