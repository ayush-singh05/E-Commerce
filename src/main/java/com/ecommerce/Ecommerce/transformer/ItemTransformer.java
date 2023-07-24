package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.Ecommerce.model.Item;

public class ItemTransformer {
    public static Item itemRequestDtoToItem(int requiredQuantity) {
        return Item.builder()
                        .requiredQuantity(requiredQuantity)
                        .build();
    }
    public static ItemResponseDto itemToItemResponseDto(Item item) {
        return ItemResponseDto.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }
}
