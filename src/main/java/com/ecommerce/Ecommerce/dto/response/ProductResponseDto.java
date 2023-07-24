package com.ecommerce.Ecommerce.dto.response;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {
    String sellerName;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory category;

    ProductStatus productStatus;
}
