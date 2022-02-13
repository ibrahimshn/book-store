package com.getir.bookstore.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.entity.Book;
import com.getir.bookstore.exception.book.BookAlreadyExistException;
import com.getir.bookstore.exception.book.BookDoesNotExistException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @MockBean
    private BookRepository bookRepository;



    @Test
    void whenNotFoundEntityFindBookByIdTest() {
        when(bookRepository.findBookById(1)).thenThrow(BookDoesNotExistException.class);
        assertThrows(BookDoesNotExistException.class, () -> bookService.findBookById(1));
    }

    @Test
    void whenFindBookByIdIsOkTest() {
        when(bookRepository.findBookById(1)).thenReturn(java.util.Optional.of(new Book()));
        bookService.findBookById(1);
    }

    @Test
    void whenNewSaveBookIsOkTest() {
        when(bookRepository.existsByNameAndAuthor(anyString(), anyString())).thenReturn(false);

        // only set author and name
        Book book = new Book();
        book.setAuthor("author");
        book.setName("bookName");

        bookService.saveBook(new Book());
    }

    @Test
    void whenAlreadySavedBookTest() {
        when(bookRepository.existsByNameAndAuthor(anyString(), anyString())).thenReturn(true);

        // only set author and name
        Book book = new Book();
        book.setAuthor("author");
        book.setName("bookName");

        assertThrows(BookAlreadyExistException.class, () -> bookService.saveBook(book));
    }
}
