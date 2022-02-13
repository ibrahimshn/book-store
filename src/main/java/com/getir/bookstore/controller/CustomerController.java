package com.getir.bookstore.controller;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.mapper.OrderMapper;
import com.getir.bookstore.model.AppPage;
import com.getir.bookstore.model.OrderDTO;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public CustomerController(CustomerService customerService, OrderService orderService, OrderMapper orderMapper) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Integer customerId, AppPage appPage) {
        List<Order> orders = orderService.findOrdersByCustomerId(customerId, appPage);
        final List<OrderDTO> orderDTOList =
                orders.stream().map(orderMapper::fromOrderEntityToOrderDTO).collect(Collectors.toList());
        return new ResponseEntity(orderDTOList, HttpStatus.OK);
    }

}
