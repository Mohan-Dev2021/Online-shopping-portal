package com.online.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.shop.model.Cart;

public interface CartRepo extends MongoRepository<Cart, String> {

}
