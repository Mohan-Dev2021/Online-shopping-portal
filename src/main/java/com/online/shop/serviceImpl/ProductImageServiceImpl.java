package com.online.shop.serviceImpl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.online.shop.dto.ProductImageDto;
=======
>>>>>>> 93392206682172df59cd318300eed817357448da
import com.online.shop.error_response.EShopException;
import com.online.shop.model.ProductImage;
import com.online.shop.model.Products;
import com.online.shop.repository.ProductImageRepo;
import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductImageService;
import com.online.shop.utility.EShopUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

	private final ProductImageRepo productImageRepo;

	private final ProductRepo productRepo;

	private final EShopUtility utility;
<<<<<<< HEAD
	
	private final ModelMapper modelMap;
	
=======

>>>>>>> 93392206682172df59cd318300eed817357448da
	@Override
	public String insertProductImage(String id, MultipartFile imageFile) throws IOException {
		Products existingproduct = productRepo.findById(id)
				.orElseThrow(() -> new EShopException().setMessage("Product doesn't exists!... - " + id));
		ProductImage image = new ProductImage().setImageId(utility.getImageId())
				.setImageName(imageFile.getOriginalFilename()).setImageFormat(imageFile.getContentType())
				.setImage(imageFile.getBytes());
		productImageRepo.save(image);
		existingproduct.setProductImage(image);
		Products imageInsertedProduct = productRepo.save(existingproduct);
		return imageInsertedProduct.getProductImage().getId();
	}

<<<<<<< HEAD
//	@Override
//	public ProductImageDto getProductImageById(String productImageid) {
//		ProductImage existingProductImage = productImageRepo.findById(productImageid)
//				.orElseThrow(() -> new EShopException().setErrorCode(404)
//						.setMessage("Product image doesn't exists!... - " + productImageid));
//		return existingProductImage;
//	}
	
	@Override
	public ProductImageDto getProductImageById(String productImageid) {
		ProductImage existingProductImage = productImageRepo.findById(productImageid)
				.orElseThrow(() -> new EShopException().setErrorCode(404)
						.setMessage("Product image doesn't exists!... - " + productImageid));
		ProductImageDto productDto=modelMap.map(existingProductImage, ProductImageDto.class);
		
		return productDto;
=======
	@Override
	public ProductImage getProductImageById(String productImageid) {
		ProductImage existingProductImage = productImageRepo.findById(productImageid)
				.orElseThrow(() -> new EShopException().setErrorCode(404)
						.setMessage("Product image doesn't exists!... - " + productImageid));
		return existingProductImage;
>>>>>>> 93392206682172df59cd318300eed817357448da
	}

	@Override
	public Boolean removeProductImageById(String id) {
		try {
			productImageRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new EShopException().setErrorCode(500).setMessage(
					"Something went wrong while removing the product image!... - " + e.getLocalizedMessage());
		}
	}

<<<<<<< HEAD




=======
>>>>>>> 93392206682172df59cd318300eed817357448da
}
