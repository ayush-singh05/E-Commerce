package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.Enum.ProductStatus;
import com.ecommerce.Ecommerce.dto.request.ProductRequestDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.model.Product;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .category(productRequestDto.getCategory())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto productToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .productStatus(product.getProductStatus())
                .category(product.getCategory())
                .build();
    }
}
