package com.getir.bookstore.exception.order;

public class OrderDoesNotExistException extends OrderException {
    public OrderDoesNotExistException(String message) {
        super(message);
    }
}
