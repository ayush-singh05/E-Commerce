package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.dto.response.CartResponseDto;
import com.ecommerce.Ecommerce.model.Item;

public interface CartService {
    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item);
}
