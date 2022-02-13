package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
    Optional<Stock> findStockByBookId(int bookId);
}
