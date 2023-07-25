package com.ecommerce.Ecommerce.Exception;

public class InvalidCardDetailsException extends RuntimeException{
    public InvalidCardDetailsException(String message) {
        super(message);
    }
}
