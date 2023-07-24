package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
        return new ResponseEntity(cardService.addCard(cardRequestDto), HttpStatus.ACCEPTED);
    }
}
