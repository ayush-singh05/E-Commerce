package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.Exception.SellerNotFoundException;
import com.ecommerce.Ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;
    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(ProductCategory productCategory, int price);
}
