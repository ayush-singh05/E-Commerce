package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {

        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get_product_by_category_and_price")
    public ResponseEntity getProductByCategoryAndPriceGreaterThan(@RequestParam("category")ProductCategory productCategory, @RequestParam("price") int price) {
        List<ProductResponseDto> listOfProduct = productService.getProductByCategoryAndPriceGreaterThan(productCategory,price);
        return new ResponseEntity(listOfProduct,HttpStatus.ACCEPTED);
    }
}
