package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Stock;
import com.getir.bookstore.exception.stock.StockDoesNotExistException;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.function.Supplier;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Transactional
    @Override
    public void decreaseAmount(Stock stock, int amount) {
        //check quantity
        if(amount <= 0) {
            throw new ValidationException("should be selected minimum one book to order");
        }

        //check stock quantity
        if(amount > stock.getQuantity()) {
            throw new ValidationException("can be selected maximum " + stock.getQuantity() +" book to order");
        }
        stock.setQuantity(stock.getQuantity() - amount);
        stockRepository.save(stock);
    }

    @Override
    public Stock findStockByBookId(int bookId) {
        Supplier<StockDoesNotExistException> s =
                () -> new StockDoesNotExistException(bookId + " :bookId not found stock information");
        return stockRepository.findStockByBookId(bookId).orElseThrow(s);
    }
}
