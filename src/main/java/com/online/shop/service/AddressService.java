package com.online.shop.service;

import com.online.shop.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> fetchAddressById(String id);

    AddressDto addAddress(String customerId,AddressDto addressDto);

    List<AddressDto> fetchAddressByCustomerId(String customerId);
}
