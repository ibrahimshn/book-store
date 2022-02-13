package com.getir.bookstore.service;

import com.getir.bookstore.entity.Stock;

public interface StockService {
    void decreaseAmount(Stock stock, int amount);
    Stock findStockByBookId(int bookId);
}
