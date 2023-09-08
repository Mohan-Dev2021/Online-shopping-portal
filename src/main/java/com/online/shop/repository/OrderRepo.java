package com.online.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.online.shop.model.Order;
@EnableMongoRepositories
public interface OrderRepo extends MongoRepository<Order, String> {

}
