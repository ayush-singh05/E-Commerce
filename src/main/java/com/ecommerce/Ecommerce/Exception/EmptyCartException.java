package com.ecommerce.Ecommerce.Exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String m) {
        super(m);
    }
}
