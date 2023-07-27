package com.ecommerce.Ecommerce.dto.response;

import com.ecommerce.Ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto  {

    String customerName;

    String cardNo;

    Date validTill;

    CardType cardType;
}
