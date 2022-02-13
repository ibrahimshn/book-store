package com.getir.bookstore.service;

import com.getir.bookstore.entity.Order;
import com.getir.bookstore.model.AppPage;
import com.getir.bookstore.model.OrderDTO;
import com.sun.istack.NotNull;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Order saveOrder(Order order, @NotNull List<OrderDTO.OrderItemDTO> orderItems);

    List<Order> findOrdersByCustomerId(Integer customerId, AppPage appPage);

    Order findOrderById(Integer orderId);

    List<Order> findOrderByDateInterval(Date fromDate, Date toDate);

}
