package com.getir.bookstore.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.entity.Book;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.entity.OrderItem;
import com.getir.bookstore.exception.order.OrderDoesNotExistException;
import com.getir.bookstore.model.AppPage;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.service.StockService;
import com.getir.bookstore.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderServiceImpl.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    BookService bookService;

    @MockBean
    StockService stockService;

    @MockBean
    StockRepository stockRepository;

    @Test
    void whenIsOkFindOrdersByCustomerIdTest() {
        AppPage appPage = new AppPage();
        appPage.setPageNo(1);
        appPage.setPageSize(10);

        Order expectedOrder = getExpectedOrder();

        when(orderRepository.findByCustomerId(2, PageRequest.of(appPage.getPageNo(), appPage.getPageSize())))
                .thenReturn(Arrays.asList(expectedOrder));
        final List<Order> orders = orderService.findOrdersByCustomerId(2, appPage);

        assertThat(orders.size()).isEqualTo(expectedOrder.getOrderItems().size());
    }

    @Test
    void whenIsNotFoundFindOrdersByCustomerIdTest() {
        AppPage appPage = new AppPage();
        appPage.setPageNo(1);
        appPage.setPageSize(10);

        when(orderRepository.findByCustomerId(2, PageRequest.of(appPage.getPageNo(), appPage.getPageSize())))
                .thenThrow(OrderDoesNotExistException.class);

        assertThrows(
                OrderDoesNotExistException.class,
                () ->orderService.findOrdersByCustomerId(2, appPage));

    }

    @Test
    void whenIsOkFindOrderByIdTest() {
        final Order expectedOrder = getExpectedOrder();
        when(orderRepository.findById(1)).thenReturn(Optional.of(expectedOrder));
        final Order order = orderService.findOrderById(1);
        assertThat(order.getOrderDate()).isEqualTo(expectedOrder.getOrderDate());
    }

    @Test
    void whenIsNotFoundFindOrderByIdTest() {
        when(orderRepository.findById(1)).thenThrow(OrderDoesNotExistException.class);
        assertThrows(
                OrderDoesNotExistException.class,
                () ->orderService.findOrderById(1));

    }

    @Test
    void whenIsOkFindOrderByDateIntervalTest() {
        final Order expectedOrder = getExpectedOrder();
        when(orderRepository.findAllByOrderDateBetween(any(), any())).thenReturn(Arrays.asList(expectedOrder));
        final List<Order> orders = orderRepository.findAllByOrderDateBetween(new Date(), new Date());
        assertThat(orders.size()).isEqualTo(expectedOrder.getOrderItems().size());
    }

    @Test
    void whenIsEmptyFindOrderByDateIntervalTest() {
        when(orderRepository.findAllByOrderDateBetween(any(), any())).thenReturn(new ArrayList<>());
        final List<Order> orders = orderRepository.findAllByOrderDateBetween(new Date(), new Date());
        assertThat(orders.size()).isEqualTo(0);
    }


    private Book getBook() {
        Book book = new Book();
        book.setId(5);
        book.setPrice(10.20);
        return book;
    }

    private Order getOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalAmount(0);
        return order;
    }

    private Order getExpectedOrder() {
        Order order = getOrder();
        order.setTotalAmount(10.20);
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        orderItem.setQuantity(2);
        orderItem.setBook(getBook());
        orderItem.setSubTotal(10.20);
        order.setOrderItems(Arrays.asList(orderItem));
        return order;
    }
}
