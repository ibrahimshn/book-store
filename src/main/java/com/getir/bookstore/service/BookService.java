package com.getir.bookstore.service;

import com.getir.bookstore.entity.Book;

public interface BookService {

    Book saveBook(Book book);
    Book updateBook(Book book);
    Book findBookById(int id);

}
