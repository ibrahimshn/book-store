package com.getir.bookstore.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookDTO {
    private int id;
    @NotEmpty(message = "name cannot be null")
    private String name;
    @NotEmpty(message = "author cannot be null")
    private String author;
    private double price;
    private StockDTO stock;

    @Data
    public static class StockDTO {
        private int quantity;
    }
}
