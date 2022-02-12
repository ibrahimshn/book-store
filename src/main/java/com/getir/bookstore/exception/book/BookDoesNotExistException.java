package com.getir.bookstore.exception.book;

public class BookDoesNotExistException extends BookException {
    public BookDoesNotExistException(String message) {
        super(message);
    }
}
