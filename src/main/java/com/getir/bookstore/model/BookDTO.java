package com.getir.bookstore.model;

import lombok.Data;

@Data
public class BookDTO {
    private int id;
    private String name;
    private String author;
    private double price;
    private StockDTO stock;

    @Data
    public static class StockDTO {
        private int quantity;
    }
}
