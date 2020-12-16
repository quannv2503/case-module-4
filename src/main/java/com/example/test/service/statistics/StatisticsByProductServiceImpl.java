package com.example.test.service.statistics;

import com.example.test.model.StatisticsByProduct;
import com.example.test.repository.StatisticsByProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsByProductServiceImpl implements StatisticsByProductService {
    @Autowired
    StatisticsByProductRepository statisticsByProductRepository;

    @Override
    public Iterable<StatisticsByProduct> statisticsByProduct(Long id) {
        return statisticsByProductRepository.statisticsByProduct(id);
    }
}
