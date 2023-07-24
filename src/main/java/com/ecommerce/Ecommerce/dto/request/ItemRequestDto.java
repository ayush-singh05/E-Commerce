package com.ecommerce.Ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {
    String customerEmail;

    int productId;

    int requiredQuantity;
}
