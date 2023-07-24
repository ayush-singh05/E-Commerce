package com.ecommerce.Ecommerce.dto.response;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    String itemName;

    int itemPrice;

    int quantityAdded;

    ProductCategory category;
}
