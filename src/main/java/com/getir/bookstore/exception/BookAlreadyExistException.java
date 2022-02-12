package com.getir.bookstore.exception;

public class BookAlreadyExistException extends BookException{
    public BookAlreadyExistException(String message) {
        super(message);
    }
}
