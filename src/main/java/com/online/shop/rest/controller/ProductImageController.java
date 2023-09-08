package com.online.shop.rest.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.shop.dto.ProductImageDto;
import com.online.shop.enums.ImageFormate;
import com.online.shop.error_response.EShopException;
import com.online.shop.service.ProductImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product/image")
@RequiredArgsConstructor

@Validated

public class ProductImageController {

	private final ProductImageService productImageService;                                                                                                                                                                                       

	@PostMapping
	public ResponseEntity<String> insertProductImage(@RequestParam String id, @RequestPart MultipartFile imageFile)
			throws IOException {

		System.out.println("type1 : " + imageFile.getContentType());
		System.out.println("type2 : " + ImageFormate.PNG.getImgFormate());

		if (!(imageFile.getContentType().equalsIgnoreCase(ImageFormate.PNG.getImgFormate())
				|| imageFile.getContentType().equalsIgnoreCase(ImageFormate.JPG.getImgFormate()))) {

			throw new EShopException().setErrorCode(406).setMessage("image formate is not accepted ");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(productImageService.insertProductImage(id, imageFile));
	}

//	@GetMapping
//	public ResponseEntity<byte[]> getProductImageById(@RequestParam String productImageid) {
//		ProductImage resourceImage = productImageService.getProductImageById(productImageid);
//		String contentType = "application/octet-stream";
//		String headerValue = "attachment; filename=\"" + resourceImage.getImageName() + "\"";
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(contentType))
//				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resourceImage.getImage());
//	}

	@GetMapping
	public ResponseEntity<byte[]> getProductImageById(@RequestParam String productImageid) {
		ProductImageDto resourceImage = productImageService.getProductImageById(productImageid);
		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resourceImage.getImageName() + "\"";
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resourceImage.getImage());
	}

	@DeleteMapping
	public ResponseEntity<Boolean> removeProductImageById(@RequestParam String id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productImageService.removeProductImageById(id));
	}
}
