package com.example.test.service.statistics;

import com.example.test.model.StatisticsByProduct;

public interface StatisticsByProductService {
    Iterable<StatisticsByProduct> statisticsByProduct(Long id);
}
