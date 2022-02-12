package com.getir.bookstore.mapper;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.entity.Stock;
import com.getir.bookstore.model.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book fromBookDtoToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        book.setPrice(bookDTO.getPrice());

        // stock
        final BookDTO.StockDTO stockDTO = bookDTO.getStock();
        Stock stock = new Stock();
        stock.setQuantity(stockDTO.getQuantity());

        book.setStock(stock);

        return book;
    }

    public BookDTO fromBookEntityToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setId(book.getId());

        final Stock inventory = book.getStock();

        // stock
        final BookDTO.StockDTO stockDTO = new BookDTO.StockDTO();
        stockDTO.setQuantity(inventory.getQuantity());

        bookDTO.setStock(stockDTO);

        return bookDTO;
    }

}
