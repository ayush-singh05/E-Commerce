package com.ecommerce.Ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {
    String name;
    String email;
    String panNo;
}
