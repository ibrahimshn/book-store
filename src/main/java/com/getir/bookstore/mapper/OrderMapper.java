package com.getir.bookstore.mapper;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.entity.OrderItem;
import com.getir.bookstore.model.OrderDTO;
import com.getir.bookstore.service.CustomerService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderMapper {

    private final CustomerService customerService;

    public OrderMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Order fromOrderDtoToOrderEntity(OrderDTO orderDTO) {
        Order order = new Order();
        Customer customer = customerService.findCustomerById(orderDTO.getCustomerId());
        order.setCustomer(customer);
        order.setOrderDate(new Date());
        order.setTotalAmount(orderDTO.getTotalAmount());
        return order;
    }

    public OrderDTO fromOrderEntityToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setId(order.getId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setOrderDate(order.getOrderDate());

        final List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            OrderDTO.OrderItemDTO orderItemDTO = new OrderDTO.OrderItemDTO();
            orderItemDTO.setId(orderItem.getId());
            orderItemDTO.setBookId(orderItem.getBook().getId());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setSubTotal(orderItem.getSubTotal());
            orderDTO.addOrderItemDTO(orderItemDTO);
        }
        return orderDTO;
    }
}
