package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    List<Order> findByCustomerId(Integer customerId, Pageable pageable);
    List<Order> findAllByOrderDateBetween(Date fromDate, Date toDate);
}
