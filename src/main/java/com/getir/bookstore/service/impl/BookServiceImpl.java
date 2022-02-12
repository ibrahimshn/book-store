package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.entity.Stock;
import com.getir.bookstore.exception.book.BookAlreadyExistException;
import com.getir.bookstore.exception.book.BookDoesNotExistException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        boolean isBookExist = bookRepository.existsByNameAndAuthor(book.getName(), book.getAuthor());
        if (isBookExist) {
            throw new BookAlreadyExistException(book.getAuthor() + "'s  " + book.getName() + " book has already saved.");
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book bookRequest) {

        Book book = findBookById(bookRequest.getId());
        //update Book fields
        book.setPrice(bookRequest.getPrice());
        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());

        //update stock
        Stock stock = book.getStock();
        stock.setQuantity(bookRequest.getStock().getQuantity());

        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(int id) {
        Supplier<BookDoesNotExistException> s =
                () -> new BookDoesNotExistException(id + " bookId: does not exist!");

        return bookRepository.findBookById(id).orElseThrow(s);
    }
}
