package com.online.shop.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.online.shop.utility.ExcelGenerator;
import com.online.shop.utility.ExcelHelper;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;

	private final ProductImageRepo productImageRepo;

	private final EShopUtility utility;

	private final ExcelGenerator excelGenerate;
	
	 private final GridFsTemplate template;

	  private final GridFsOperations operations;
	  
	@Override
	public ProductDto getProductByProductId(String productId) {
		Products productDetails = productRepo.findByProductId(productId).orElseThrow(
				() -> new EShopException(404, "Product doesn't exists with this productId - " + productId));
//		ProductDto productDto = modelMapper.map(productDetails, ProductDto.class);
//		ProductImageDto imageDto = modelMapper.map(productDetails.getProductImage(), ProductImageDto.class);
//		productDto.setProductImageDto(imageDto);
		ProductDto productDto = utility.toConvert(productDetails, ProductDto.class);
		ProductImageDto productImageDto = utility.toConvert(productDetails.getProductImage(), ProductImageDto.class);
		productDto.setProductImageDto(productImageDto);
		return productDto;
	}

	@Override
	public ProductDto getProductById(String id) {
		Products existingproduct = productRepo.findById(id)
				.orElseThrow(() -> new EShopException(404, "Product doesn't exists -" + id));
//		ProductDto productDto = modelMapper.map(existingproduct, ProductDto.class);
//		ProductImageDto imageDto = modelMapper.map(existingproduct.getProductImage(), ProductImageDto.class);
//		productDto.setProductImageDto(imageDto);
		ProductDto productDto = utility.toConvert(existingproduct, ProductDto.class);
		ProductImageDto productImageDto = utility.toConvert(existingproduct.getProductImage(), ProductImageDto.class);
		productDto.setProductImageDto(productImageDto);
		return productDto;
	}

	@Override
	public ProductDto saveProduct(ProductDto saveProduct) {
//	Products productDetails = modelMapper.map(saveProduct, Products.class);
//		productDetails.setProductId(utility.getProductId());
//		productDetails.setProductImage(new ProductImage().setImageId(utility.getImageId()));
//		Products saveProducts = productRepo.save(productDetails);
//		ProductDto productDto = modelMapper.map(saveProducts, ProductDto.class);
		Products productDetails = utility.toConvert(saveProduct, Products.class);
		productDetails.setProductId(utility.getProductId());
		productDetails.setProductImage(new ProductImage().setImageId(utility.getImageId()));
		Products saveProducts = productRepo.save(productDetails);
		ProductDto productDto = utility.toConvert(saveProducts, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto updateProducts(ProductDto productDetails) {
		ProductDto product = this.getProductByProductId(productDetails.getProductId());
		Products productEntity = utility.toConvert(product, Products.class);
		Products product1 = productRepo.save(productEntity);
		ProductDto productDto = utility.toConvert(product1, ProductDto.class);
//		Products productEntity = modelMapper.map(product, Products.class);
//		Products product1 = productRepo.save(productEntity);
//		ProductDto productDto = modelMapper.map(product1, ProductDto.class);
		return productDto;
	}

	@Override
	public Boolean removeProductById(String id) throws NotFoundException {
		try {
			Optional<Products> removeproducId = productRepo.findById(id);
			if (removeproducId.isPresent()) {
				String imageId = removeproducId.get().getId();
				productImageRepo.deleteById(imageId);
			}
			productRepo.deleteById(id);
			return true;
		} catch (Exception ex) {
			throw new EShopException(500,
					"Something went wrong while removing the product - " + ex.getLocalizedMessage());
		}
	}

	@Override
	public PaginationDtoResponse<?> getAllProductsByPagination(Integer pageNo, Integer offset) {
		PageRequest request = PageRequest.of(pageNo, offset);
		Page<Products> pagableProducts = productRepo.findAll(request);
//		List<ProductDto> toProductDtolist = pagableProducts.getContent().stream()
//				.map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
//		PaginationDtoResponse<ProductDto> paginatedRes = new PaginationDtoResponse<ProductDto>()
//				.setContent(toProductDtolist).setNumberOfElements(pagableProducts.getNumberOfElements())
//				.setTotalElements(pagableProducts.getTotalElements()).setPageNo(pageNo)
//				.setSort(pagableProducts.getSort()).setOffset(offset)
//				.setHasPrevious(pagableProducts.getPageable().hasPrevious());
//		return paginatedRes;
		List<ProductDto> toProductDtolist = utility.toConvertList(pagableProducts.getContent(), ProductDto.class);
		PaginationDtoResponse<ProductDto> paginatedRes = new PaginationDtoResponse<ProductDto>()
				.setContent(toProductDtolist).setNumberOfElements(pagableProducts.getNumberOfElements())
				.setTotalElements(pagableProducts.getTotalElements()).setPageNo(pageNo)
				.setSort(pagableProducts.getSort()).setOffset(offset)
				.setHasPrevious(pagableProducts.getPageable().hasPrevious());
		return paginatedRes;

	}

//	@Override
//	public List<ProductDto> getAllProducts() {
//		List<Products> productDetails= productRepo.findAll();
//		List<ProductDto> prodDto=utility.toConvertList(productDetails,ProductDto.class);
//		return prodDto;
//	}

	public ByteArrayOutputStream generate(List<ProductDto> prodtDto) throws IOException, java.io.IOException {
		excelGenerate.writeHeader();
		excelGenerate.write(prodtDto);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		excelGenerate.workbook.write(outputStream);
		excelGenerate.workbook.write(outputStream);
		return outputStream;
	}

	@Override
	public ByteArrayOutputStream getAllProducts() throws IOException, Exception {
		List<Products> productDetails = productRepo.findAll();
		List<ProductDto> prdDto = utility.toConvertList(productDetails, ProductDto.class);
		return this.generate(prdDto);
	}

	@Override
	public void saveFile(MultipartFile file) throws java.io.IOException {
		
		 try {
	            List<ProductDto> productDtos = ExcelHelper.excelToProducts(file.getInputStream());
	            List<Products> products = utility.toConvertList(productDtos,Products.class);
	            List<Products> savedProducts = productRepo.saveAll(products);
	            List<ProductDto> savedProductDtos = utility.toConvertList(savedProducts, ProductDto.class);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to store Excel data: " + e.getMessage());
	        }
	}

	

}