package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.Enum.ProductCategory;
import com.ecommerce.Ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.Ecommerce.dto.response.ProductResponseDto;
import com.ecommerce.Ecommerce.model.Card;
import com.ecommerce.Ecommerce.model.Cart;
import com.ecommerce.Ecommerce.model.OrderEntity;

import java.util.List;

public interface OrderService {
    public OrderEntity placeOrder(Cart cart, Card card);
    public List<OrderResponseDto> topOrder(int noOfOrder);
    public List<ProductResponseDto> cheapestOrder(ProductCategory category,int noOfProduct);
    public OrderResponseDto cancelOrder(String orderId);
}
