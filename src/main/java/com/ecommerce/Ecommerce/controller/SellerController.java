package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.request.SellerRequestDto;
import com.ecommerce.Ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {

        return new ResponseEntity(sellerService.addSeller(sellerRequestDto), HttpStatus.ACCEPTED);
    }
}
