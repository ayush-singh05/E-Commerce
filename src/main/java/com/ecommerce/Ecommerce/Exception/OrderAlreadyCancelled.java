package com.ecommerce.Ecommerce.Exception;

public class OrderAlreadyCancelled extends RuntimeException{
    public OrderAlreadyCancelled(String message) {
        super(message);
    }
}
