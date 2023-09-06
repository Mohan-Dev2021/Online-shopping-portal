package com.online.shop.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.online.shop.dto.ProductImageDto;
=======
import com.online.shop.model.ProductImage;
>>>>>>> 93392206682172df59cd318300eed817357448da

public interface ProductImageService {

	String insertProductImage(String id, MultipartFile imageFile) throws IOException;

<<<<<<< HEAD
	ProductImageDto getProductImageById(String productImageid);

	Boolean removeProductImageById(String id);
	
	
=======
	ProductImage getProductImageById(String productImageid);

	Boolean removeProductImageById(String id);
>>>>>>> 93392206682172df59cd318300eed817357448da

}
