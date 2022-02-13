package com.getir.bookstore.repository;

import com.getir.bookstore.entity.MonthlyOrderStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<MonthlyOrderStatistic, Long> {
    Iterable<MonthlyOrderStatistic> findAll();
}
