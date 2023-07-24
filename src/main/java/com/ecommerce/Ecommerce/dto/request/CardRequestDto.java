package com.ecommerce.Ecommerce.dto.request;

import com.ecommerce.Ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String mobile;

    String cardNumber;

    int cvv;

    CardType cardType;

    Date validTill;

}
