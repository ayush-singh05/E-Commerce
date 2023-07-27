package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.request.CardRequestDto;
import com.ecommerce.Ecommerce.dto.response.CardResponseDto;
import com.ecommerce.Ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
        return new ResponseEntity(cardService.addCard(cardRequestDto), HttpStatus.ACCEPTED);
    }
    @PutMapping("/update")
    public ResponseEntity updateCardNumber(@RequestParam("oldCard") String oldCardNo, @RequestParam("newCard") String newCardNo) {

        try{
            CardResponseDto cardResponseDto = cardService.updateCardNumber(oldCardNo,newCardNo);
            return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCard(@RequestParam("cardNo") String cardNo) {
        try{
            String card = cardService.deleteCard(cardNo);
            return new ResponseEntity(card,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
