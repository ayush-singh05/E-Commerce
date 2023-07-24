package com.ecommerce.Ecommerce.Exception;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
