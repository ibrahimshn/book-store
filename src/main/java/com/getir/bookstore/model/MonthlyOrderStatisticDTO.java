package com.getir.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MonthlyOrderStatisticDTO {
    private int totalOrderCount;
    private Long totalBookCount;
    private double totalPurchasedAmount;
    private String monthName;
}
