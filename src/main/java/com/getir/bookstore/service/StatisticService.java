package com.getir.bookstore.service;

import com.getir.bookstore.model.MonthlyOrderStatisticDTO;

import java.util.List;

public interface StatisticService {
    List<MonthlyOrderStatisticDTO> getMonthlyStatistics();
}
