package com.online.shop.repository;

import com.online.shop.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface AddressRepo extends MongoRepository<Address,String> {
    List<Address> findAllAddressById(String id);
}
