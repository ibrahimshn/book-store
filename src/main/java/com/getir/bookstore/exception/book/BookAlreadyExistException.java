package com.getir.bookstore.exception.book;

public class BookAlreadyExistException extends BookException{
    public BookAlreadyExistException(String message) {
        super(message);
    }
}
