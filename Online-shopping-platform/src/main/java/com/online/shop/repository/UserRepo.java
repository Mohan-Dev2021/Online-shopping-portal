package com.online.shop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.online.shop.model.Customer;

/**
 * Repository layer where we can able to handle all the CRUD operation and
 * interacting with DB by very easier way
 * 
 * @category Persistence module
 * @author Mohanlal
 */
@EnableMongoRepositories
public interface UserRepo extends MongoRepository<Customer, String> {

	Optional<Customer> findByEmailId(String username);

}
