package com.getir.bookstore.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private int id;
    @NotNull(message = "customerId cannot be null")
    private int customerId;
    private double totalAmount;
    private Date orderDate;
    @NotNull(message = "orderItem cannot be null")
    private List<OrderItemDTO> orderItems;

    @Data
    public static class OrderItemDTO {
        private int id;
        @NotNull(message = "bookId cannot be null")
        private int bookId;
        private int quantity;
        private double subTotal;
    }

    public void addOrderItemDTO(OrderItemDTO orderItemDTO) {
        if(orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItemDTO);
    }

}
