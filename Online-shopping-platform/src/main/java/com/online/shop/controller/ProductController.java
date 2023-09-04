package com.online.shop.controller;

import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.shop.dto.PagableDto;
import com.online.shop.dto.ProductDto;
import com.online.shop.repository.ProductRepo;
import com.online.shop.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 *  @category Product module
 * @author Sivaranjani K
 *
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;


	/**
	 * @param id
	 * @return ProductDto
	 * @category Product module
	 * @author Sivaranjani K
	 */
	@Description(value="To get  a Id from database")
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
	}

	
	/**
	 * @param productId
	 * @return ProductDto
	 * @author Sivaranjani K
	 */
	@Description(value="To get product Id from database")
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductByProductId(@PathVariable String productId) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByProductId(productId));
	}

	
	/**
	 * Save new productList
	 * @param saveProduct
	 * @return ProductDto
	 * @author Sivaranjani K
	 */
	// Generate productId for every persistence - (Format - ECOM+MONTH+UUID's last 4
	// digits[size - 13])
	@Description(value="save new productDertails from database")
	@PostMapping
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto saveProduct) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(saveProduct));
	}

	
	/**
	 * Update  products details  from existing database
	 * @param productDto
	 * @return ProductDto
	 * @author Sivaranjani K
	 */
	@Description(value = "Update products details  depends on productId from existing database")
	@PutMapping("/product")
	public ResponseEntity<ProductDto> updateProducts(@RequestBody ProductDto productDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.updateProducts(productDto));

	}



/**
 * Api for to  get a  all products with pagination
 * @param pageNo
 * @param offset
 * @return return type it can be anything(String,boolean,integer...)
 * @author Sivaranjani K
 */
	@GetMapping("/")
	@Description(value = "get all products with pagination   depends on page &pageRequest Methods")
	public ResponseEntity<PagableDto<?>> getAllProductsWithPagination(@RequestParam int pageNo, int offset) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsWithPagination(pageNo, offset));

	}

}
