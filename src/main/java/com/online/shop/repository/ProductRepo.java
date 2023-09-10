package com.online.shop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.online.shop.model.Products;

@EnableMongoRepositories
public interface ProductRepo extends MongoRepository<Products, String> {

	@Query("{ product_id: ?0 }")
	Optional<Products> findByProductId(String productId);
	
}
