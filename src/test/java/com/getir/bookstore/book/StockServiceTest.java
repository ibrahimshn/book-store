package com.getir.bookstore.book;

import com.getir.bookstore.entity.Stock;
import com.getir.bookstore.exception.stock.StockDoesNotExistException;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.StockService;
import com.getir.bookstore.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StockServiceImpl.class)
public class StockServiceTest {

    @Autowired
    StockService stockService;

    @MockBean
    StockRepository stockRepository;

    @Test
    void whenIsOkDecreaseAmountTest() {
        Stock stock = new Stock();
        stock.setQuantity(5);
        int initQuantity = stock.getQuantity();

        when(stockRepository.save(stock)).thenReturn(stock);

        stockService.decreaseAmount(stock, 3);
        assertThat(stock.getQuantity()).isEqualTo(initQuantity - 3);
    }

    @Test
    void whenIsZeroBookStockTest() {
        Stock stock = new Stock();
        stock.setQuantity(0);

        when(stockRepository.save(stock)).thenReturn(stock);
        assertThrows(ValidationException.class, () -> stockService.decreaseAmount(stock, 3));

    }

    @Test
    void whenIsBiggerThanBookStockTest() {
        Stock stock = new Stock();
        stock.setQuantity(2);

        when(stockRepository.save(stock)).thenReturn(stock);
        assertThrows(ValidationException.class, () -> stockService.decreaseAmount(stock, 3));

    }

    @Test
    void WhenIsOkFindStockByBookIdTest() {
        when(stockRepository.findStockByBookId(2)).thenReturn(java.util.Optional.of(new Stock()));
        stockService.findStockByBookId(2);
    }

    @Test
    void WhenIsNotFindStockByBookIdTest() {
        when(stockRepository.findStockByBookId(2)).thenThrow(StockDoesNotExistException.class);
        assertThrows(StockDoesNotExistException.class, () ->  stockService.findStockByBookId(2));
    }

}
