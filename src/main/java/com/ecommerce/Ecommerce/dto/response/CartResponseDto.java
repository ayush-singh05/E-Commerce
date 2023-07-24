package com.ecommerce.Ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;

    int cartTotal;

    List<ItemResponseDto> items;
}
