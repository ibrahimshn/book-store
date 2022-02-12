package com.getir.bookstore.exception;

public class BookDoesNotExistException extends BookException {
    public BookDoesNotExistException(String message) {
        super(message);
    }
}
