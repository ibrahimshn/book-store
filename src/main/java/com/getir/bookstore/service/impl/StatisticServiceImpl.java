package com.getir.bookstore.service.impl;

import com.getir.bookstore.common.MonthConstant;
import com.getir.bookstore.entity.MonthlyOrderStatistic;
import com.getir.bookstore.model.MonthlyOrderStatisticDTO;
import com.getir.bookstore.repository.StatisticRepository;
import com.getir.bookstore.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public List<MonthlyOrderStatisticDTO> getMonthlyStatistics() {
        List<MonthlyOrderStatisticDTO> monthlyOrderStatistics = new ArrayList<>();
        for (MonthlyOrderStatistic monthlyOrderStatistic : statisticRepository.findAll()) {
            MonthlyOrderStatisticDTO monthlyOrderStatisticDTO = new MonthlyOrderStatisticDTO();
            monthlyOrderStatisticDTO.setMonthName(MonthConstant.monthMap.get(monthlyOrderStatistic.getMonthNumber()));
            monthlyOrderStatisticDTO.setTotalBookCount(monthlyOrderStatistic.getTotalBookCount());
            monthlyOrderStatisticDTO.setTotalOrderCount(monthlyOrderStatistic.getTotalOrderCount());
            monthlyOrderStatisticDTO.setTotalPurchasedAmount(monthlyOrderStatistic.getTotalPurchasedAmount());
            monthlyOrderStatistics.add(monthlyOrderStatisticDTO);
        }
        return monthlyOrderStatistics;
    }
}
