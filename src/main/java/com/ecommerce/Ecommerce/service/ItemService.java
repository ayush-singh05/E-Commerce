package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.Ecommerce.model.Item;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto);
}
