package com.getir.bookstore.controller;

import com.getir.bookstore.entity.Order;
import com.getir.bookstore.mapper.OrderMapper;
import com.getir.bookstore.model.OrderDTO;
import com.getir.bookstore.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDto) {
        final Order order = orderService.createOrder(orderMapper.fromOrderDtoToOrderEntity(orderDto), orderDto.getOrderItems());
        return new ResponseEntity(orderMapper.fromOrderEntityToOrderDTO(order), HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity(orderMapper.fromOrderEntityToOrderDTO(order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrderListByDateInterval(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        List<Order> orders = orderService.findOrderByDateInterval(fromDate, toDate);
        final List<OrderDTO> orderDTOList =
                orders.stream().map(orderMapper::fromOrderEntityToOrderDTO).collect(Collectors.toList());
        return new ResponseEntity(orderDTOList, HttpStatus.OK);
    }

}
