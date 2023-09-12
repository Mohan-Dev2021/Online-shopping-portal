package com.online.shop.rest.controller;

import org.springframework.context.annotation.Description;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;
import com.online.shop.error_response.ResponseMessage;
import com.online.shop.service.ProductService;
import com.online.shop.utility.EShopConstants;
import com.online.shop.utility.ExcelHelper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

	/**
	 * update comments for functionality
	 */

//	private static final Logger logger = LoggerFactory.class;

	private final ProductService productService;
	

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));

	}

	@GetMapping
	public ResponseEntity<ProductDto> getProductByProductId(@RequestParam String productId) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByProductId(productId));
	}

	@GetMapping("/list")
	public ResponseEntity<PaginationDtoResponse<?>> getAllProductsByPagination(
			@RequestParam(required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(required = false, defaultValue = "10") Integer offset) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsByPagination(pageNo, offset));
	}

	@PostMapping
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto saveProduct) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(saveProduct));
	}

	@Description(value = "Update products details  depends on productId from existing database")
	@PutMapping
	public ResponseEntity<ProductDto> updateProducts(@RequestBody ProductDto productDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.updateProducts(productDto));
	}

	@DeleteMapping
	public ResponseEntity<Boolean> removeProductById(@RequestParam String id) throws NotFoundException {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productService.removeProductById(id));
	}

//  @GetMapping("/export/excel")
//  public ResponseEntity<List<ProductDto>> exportIntoExcel (HttpServletResponse response) throws IOException, java.io.IOException {
//	  response.setContentType(EShopConstants.FILE_CONTENT_TYPE);
//		String headerKey = "Content-Disposition";
//		String headerValue =  EShopConstants.HEADER_VALUE ;  
//		response.setHeader(headerKey, headerValue);	
//		
//		List<ProductDto> getProductsExcel=productService.getAllProducts();
//		ExcelGenerator generator = new ExcelGenerator(getProductsExcel);
//		generator.generate(getProductsExcel, response);
//		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
//

	@GetMapping("/import")
	public ResponseEntity<byte[]> exportIntoExcel() throws Exception {
		String contentType = EShopConstants.FILE_CONTENT_TYPE;
		String headerValue = EShopConstants.HEADER_VALUE;
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
				.body(productService.getAllProducts().toByteArray());
	}

  	  
	@PostMapping("/exports")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestPart MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				productService.saveFile(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}else {
			message = "Please upload an excel file!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
		
	}
}
		

