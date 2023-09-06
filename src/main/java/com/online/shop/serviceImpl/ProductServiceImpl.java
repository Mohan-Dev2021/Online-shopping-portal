package com.online.shop.serviceImpl;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 93392206682172df59cd318300eed817357448da
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.mongodb.internal.operation.FindAndDeleteOperation;
=======
>>>>>>> 93392206682172df59cd318300eed817357448da
import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;
import com.online.shop.dto.ProductImageDto;
import com.online.shop.error_response.EShopException;
import com.online.shop.model.ProductImage;
import com.online.shop.model.Products;
import com.online.shop.repository.ProductImageRepo;
import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductService;
import com.online.shop.utility.EShopUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;

	private final ModelMapper modelMapper;
	private final ProductImageRepo productImageRepo;
	private final EShopUtility utility;

	private final EShopUtility utility;

	@Override
	public ProductDto getProductByProductId(String productId) {
		Products productDetails = productRepo.findByProductId(productId).orElseThrow(() -> new EShopException()
				.setErrorCode(404).setMessage("Product doesn't exists with this productId - " + productId));
		ProductDto productDto = modelMapper.map(productDetails, ProductDto.class);
		ProductImageDto imageDto = modelMapper.map(productDetails.getProductImage(), ProductImageDto.class);
		productDto.setProductImageDto(imageDto);
		return productDto;
	}

	@Override
	public ProductDto getProductById(String id) {
		Products existingproduct = productRepo.findById(id)
				.orElseThrow(() -> new EShopException().setErrorCode(404).setMessage("Product doesn't exists -" + id));
		ProductDto productDto = modelMapper.map(existingproduct, ProductDto.class);
		ProductImageDto imageDto = modelMapper.map(existingproduct.getProductImage(), ProductImageDto.class);
		productDto.setProductImageDto(imageDto);
		return productDto;
	}

	@Override
	public ProductDto saveProduct(ProductDto saveProduct) {
		Products productDetails = modelMapper.map(saveProduct, Products.class);
		productDetails.setProductId(utility.getProductId());
		productDetails.setProductImage(new ProductImage().setImageId(utility.getImageId()));
		Products saveProducts = productRepo.save(productDetails);
		ProductDto productDto = modelMapper.map(saveProducts, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto updateProducts(ProductDto productDetails) {
		ProductDto product = this.getProductByProductId(productDetails.getProductId());
		Products productEntity = modelMapper.map(product, Products.class);
		Products product1 = productRepo.save(productEntity);
		ProductDto productDto = modelMapper.map(product1, ProductDto.class);
		return productDto;
	}

	@Override
	public Boolean removeProductById(String id) throws NotFoundException {
		try {
<<<<<<< HEAD
		Optional<Products> removeproducId=productRepo.findById(id);
		if(removeproducId.isPresent()) {
		String imageId= removeproducId.get().getId();
			productImageRepo.deleteById(imageId);
		}
		productRepo.deleteById(id);
=======
			productRepo.deleteById(id);
>>>>>>> 93392206682172df59cd318300eed817357448da
			return true;
		} catch (Exception ex) {
			throw new EShopException().setErrorCode(500)
					.setMessage("Something went wrong while removing the product - " + ex.getLocalizedMessage());
		}
	}

	@Override
	public PaginationDtoResponse<?> getAllProductsByPagination(Integer pageNo, Integer offset) {
		PageRequest request = PageRequest.of(pageNo, offset);
		Page<Products> pagableProducts = productRepo.findAll(request);
		List<ProductDto> toProductDtolist = pagableProducts.getContent().stream()
				.map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		PaginationDtoResponse<ProductDto> paginatedRes = new PaginationDtoResponse<ProductDto>()
				.setContent(toProductDtolist).setNumberOfElements(pagableProducts.getNumberOfElements()).setTotalElements(pagableProducts.getTotalElements())
				.setPageNo(pageNo).setSort(pagableProducts.getSort()).setOffset(offset).setHasPrevious(pagableProducts.getPageable().hasPrevious());
		return paginatedRes;
	}

}