package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.CustomerRequestDto;
import com.ecommerce.Ecommerce.dto.response.CustomerResponseDto;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
