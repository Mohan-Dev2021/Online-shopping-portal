package com.online.shop.rest.controller;

import com.online.shop.dto.AddressDto;
import com.online.shop.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/address")
@Validated
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestParam String customerId, @RequestBody AddressDto addressDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(customerId,addressDto));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> fetchAddressById(@RequestParam String id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.fetchAddressById(id));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<AddressDto>> fetchAddressByCustomerId(@PathVariable String customerId){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.fetchAddressByCustomerId(customerId));
    }
}
