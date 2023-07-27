package com.ecommerce.Ecommerce.dto.response;

import com.ecommerce.Ecommerce.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderId;

    Date orderDate;

    String cardUsed;

    int orderTotal;

    String customerName;

    OrderStatus status;

    List<ItemResponseDto> item;
}
