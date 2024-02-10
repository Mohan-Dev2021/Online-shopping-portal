package com.online.shop.serviceImpl;

import com.online.shop.dto.AddressDto;
import com.online.shop.dto.CustomerDto;
import com.online.shop.model.Address;
import com.online.shop.model.Customer;
import com.online.shop.repository.AddressRepo;
import com.online.shop.repository.UserRepo;
import com.online.shop.service.AddressService;
import com.online.shop.service.UserService;
import com.online.shop.utility.EShopUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    private final UserRepo userRepo;

    private final UserService userService;

    private final EShopUtility utility;

    @Override
    public List<AddressDto> fetchAddressById(String id) {
        List<Address> address = addressRepo.findAllAddressById(id);
        return utility.toConvertList(address, AddressDto.class);
    }

    @Override
    public AddressDto addAddress(String customerId,AddressDto addressDto) {
        CustomerDto customerDto = userService.getUserDetailsById(customerId);
        Address address = utility.toConvert(addressDto,Address.class);
        Address savedAddress = addressRepo.save(address);
        AddressDto savedAddressDto = utility.toConvert(savedAddress, AddressDto.class);
        List<AddressDto> existAddressDto = customerDto.getAddressess();
        existAddressDto.add(savedAddressDto);
        customerDto.setAddressess(existAddressDto);
        Customer saveCustomer = utility.toConvert(customerDto,Customer.class);
        userRepo.save(saveCustomer);
        return savedAddressDto;
    }

    @Override
    public List<AddressDto> fetchAddressByCustomerId(String customerId) {
        CustomerDto customerDto = userService.getUserDetailsById(customerId);
        return customerDto.getAddressess();
    }
}
