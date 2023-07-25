package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.model.Card;
import com.ecommerce.Ecommerce.model.Cart;
import com.ecommerce.Ecommerce.model.OrderEntity;

public interface OrderService {
    public OrderEntity placeOrder(Cart cart, Card card);
}
