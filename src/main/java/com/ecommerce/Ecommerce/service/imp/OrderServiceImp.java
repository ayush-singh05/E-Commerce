package com.ecommerce.Ecommerce.service.imp;

import com.ecommerce.Ecommerce.Exception.InsufficientQuantityException;
import com.ecommerce.Ecommerce.model.*;
import com.ecommerce.Ecommerce.service.CardService;
import com.ecommerce.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    CardService cardService;
    @Override
    public OrderEntity placeOrder(Cart cart, Card card) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.cardMask(card.getCardNo()));

        int orderTotal = 0;
        for(Item item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.getAvailableQuantity() < item.getRequiredQuantity()) {
                throw new InsufficientQuantityException("Error : Insufficient Quantity available for "+ product.getProductName());
            }
            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            orderTotal += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(orderEntity);
        }
        orderEntity.setOrderTotal(orderTotal);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(card.getCustomer());
        return orderEntity;

    }
}
