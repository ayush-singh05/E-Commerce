package com.ecommerce.Ecommerce.transformer;

import com.ecommerce.Ecommerce.dto.response.CartResponseDto;
import com.ecommerce.Ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.Ecommerce.model.Cart;
import com.ecommerce.Ecommerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
