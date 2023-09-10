package com.online.shop.rest.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Description;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.PaginationDtoResponse;
import com.online.shop.dto.ProductDto;
import com.online.shop.excel.file.ExcelGenerator;
import com.online.shop.service.ProductService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
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
	
	


  @GetMapping("/export/excel")
  public ResponseEntity<List<ProductDto>> exportIntoExcel (HttpServletResponse response) throws IOException, java.io.IOException {
	  response.setContentType("application/octet-stream");
	  DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date(0));


		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);	
		
		List<ProductDto> getProductsExcel=productService.getAllProducts();
		ExcelGenerator generator = new ExcelGenerator(getProductsExcel);
		generator.generate(getProductsExcel, response);
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());

		
}
	
}
