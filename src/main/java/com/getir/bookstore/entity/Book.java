package com.getir.bookstore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String author;
    private double price;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Stock stock;

    public void setStock(Stock stock) {
        if (stock == null) {
            if (this.stock != null) {
                this.stock.setBook(null);
            }
        }
        else {
            stock.setBook(this);
        }
        this.stock = stock;
    }
}
