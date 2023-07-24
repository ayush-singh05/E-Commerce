package com.ecommerce.Ecommerce.dto.response;

import com.ecommerce.Ecommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {
    String name;
    Gender gender;
    String email;
    String mobile;
}
