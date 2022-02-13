package com.getir.bookstore.exception.stock;

public class StockDoesNotExistException extends StockException {
    public StockDoesNotExistException(String message) {
        super(message);
    }
}
