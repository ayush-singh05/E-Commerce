package com.ecommerce.Ecommerce.dto.request;

import com.ecommerce.Ecommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;
    Gender gender;
    String email;
    String mobile;
}
