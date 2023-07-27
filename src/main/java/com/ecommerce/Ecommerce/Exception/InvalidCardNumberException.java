package com.ecommerce.Ecommerce.Exception;

public class InvalidCardNumberException extends RuntimeException{
    public InvalidCardNumberException(String message) {
        super(message);
    }
}
