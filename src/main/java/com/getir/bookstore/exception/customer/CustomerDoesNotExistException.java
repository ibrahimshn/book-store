package com.getir.bookstore.exception.customer;

public class CustomerDoesNotExistException extends CustomerException {
    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
