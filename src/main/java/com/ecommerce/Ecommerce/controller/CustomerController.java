package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.request.CustomerRequestDto;
import com.ecommerce.Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {

        return new ResponseEntity(customerService.addCustomer(customerRequestDto), HttpStatus.ACCEPTED);
    }
}
