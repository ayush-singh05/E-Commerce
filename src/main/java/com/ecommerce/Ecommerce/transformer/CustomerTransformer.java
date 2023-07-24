package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.dto.request.CustomerRequestDto;
import com.ecommerce.Ecommerce.dto.response.CustomerResponseDto;
import com.ecommerce.Ecommerce.model.Customer;



public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = Customer.builder().name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .email(customerRequestDto.getEmail())
                .mobile(customerRequestDto.getMobile())
                .build();
        return customer;
    }
    public static CustomerResponseDto customerToCustomerResponse(Customer customer) {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .gender(customer.getGender())
                .mobile(customer.getMobile())
                .email(customer.getEmail())
                .build();
    }
}
