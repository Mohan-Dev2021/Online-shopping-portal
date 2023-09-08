package com.online.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.shop.model.ProductImage;
/**
 * Repository layer where we can able to handle all the CRUD operation and
 * interacting with DB by very easier way
 * 
 * @category Persistence module
 * @author Sneka S
 */
public interface ProductImageRepo extends MongoRepository<ProductImage, String> {

	

}
