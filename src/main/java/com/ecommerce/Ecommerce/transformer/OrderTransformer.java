package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.Ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.Ecommerce.model.Item;
import com.ecommerce.Ecommerce.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderResponseDto orderToOrderResponseDto(OrderEntity orderEntity) {
        List<ItemResponseDto> itemDto = new ArrayList<>();
        for(Item item : orderEntity.getItems()) {
            itemDto.add(ItemTransformer.itemToItemResponseDto(item));
        }
        return OrderResponseDto.builder()
                .orderId(orderEntity.getOrderId())
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .status(orderEntity.getStatus())
                .item(itemDto)
                .build();
    }

}
