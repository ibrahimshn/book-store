package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.entity.OrderItem;
import com.getir.bookstore.entity.Stock;
import com.getir.bookstore.exception.order.OrderDoesNotExistException;
import com.getir.bookstore.model.AppPage;
import com.getir.bookstore.model.OrderDTO;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.service.StockService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final StockService stockService;

    public OrderServiceImpl(OrderRepository orderRepository, BookService bookService, StockService stockService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.stockService = stockService;
    }

    @Transactional
    @Override
    public Order saveOrder(Order order, List<OrderDTO.OrderItemDTO> orderItems) {
        for (OrderDTO.OrderItemDTO orderItemDTO : orderItems) {
            final Book book = bookService.findBookById(orderItemDTO.getBookId());
            final Stock stock = stockService.findStockByBookId(orderItemDTO.getBookId());

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(orderItemDTO.getQuantity());

            //update stock
            stockService.decreaseAmount(stock, orderItemDTO.getQuantity());

            double subTotal = book.getPrice() * orderItemDTO.getQuantity();
            orderItem.setSubTotal(subTotal);

            //add order item
            order.setTotalAmount(order.getTotalAmount() + subTotal);
            order.addOrderItem(orderItem);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByCustomerId(Integer customerId, AppPage appPage) {
        return orderRepository.findByCustomerId(
                customerId, PageRequest.of(appPage.getPageNo(), appPage.getPageSize()));
    }

    @Override
    public Order findOrderById(Integer orderId) {
        Supplier<OrderDoesNotExistException> s =
                () -> new OrderDoesNotExistException(orderId + " orderId: not found");
        return orderRepository.findById(orderId).orElseThrow(s);
    }

    @Override
    public List<Order> findOrderByDateInterval(Date fromDate, Date toDate) {
        return orderRepository.findAllByOrderDateBetween(fromDate, toDate);
    }
}
