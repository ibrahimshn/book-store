package com.getir.bookstore.controller;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.mapper.BookMapper;
import com.getir.bookstore.model.BookDTO;
import com.getir.bookstore.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public BookDTO saveBook(@RequestBody @Valid BookDTO bookDTO) {
        final Book book = bookService.saveBook(bookMapper.fromBookDtoToBookEntity(bookDTO));
        return bookMapper.fromBookEntityToBookDTO(book);
    }

}