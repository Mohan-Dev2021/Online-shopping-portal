package com.online.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.online.shop.model.Payment;
@EnableMongoRepositories
public interface PaymentRepo extends MongoRepository<Payment, String> {

}
