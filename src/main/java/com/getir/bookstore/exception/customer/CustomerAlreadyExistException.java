package com.getir.bookstore.exception.customer;

public class CustomerAlreadyExistException extends CustomerException {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
