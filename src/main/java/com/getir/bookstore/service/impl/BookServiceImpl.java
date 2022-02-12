package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
