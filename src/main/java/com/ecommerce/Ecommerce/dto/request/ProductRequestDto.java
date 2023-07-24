package com.ecommerce.Ecommerce.dto.request;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    int sellerId;

    ProductCategory category;

    String productName;

    int price;

    int availableQuantity;

}
