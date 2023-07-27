package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @GetMapping("/top_order")
    public ResponseEntity topOrder(@RequestParam("no") int noOfOrder) {
        List<OrderResponseDto> order = orderService.topOrder(noOfOrder);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }
    @GetMapping("/top_order/{noOfOrder}")
    public ResponseEntity topOrderByPath( @PathVariable int noOfOrder) {
        List<OrderResponseDto> order = orderService.topOrder(noOfOrder);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/lowToHigh")
    public ResponseEntity cheapestOrder(@RequestParam("category") ProductCategory category,@RequestParam("no")int noOfProduct) {
        List<ProductResponseDto> order = orderService.cheapestOrder(category,noOfProduct);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }
    @GetMapping("/lowToHigh/{category}/{noOfProduct}")
    public ResponseEntity cheapestOrderByPath(@PathVariable ProductCategory category,@PathVariable int noOfProduct) {
        List<ProductResponseDto> order = orderService.cheapestOrder(category,noOfProduct);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }

    @PutMapping("/cancel")
    public ResponseEntity cancelOrder(@RequestParam("orderId") String orderId) {
        try{
            OrderResponseDto orderResponseDto = orderService.cancelOrder(orderId);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
