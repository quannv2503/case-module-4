package com.example.test.service.statistics;

import com.example.test.model.StatisticsByProduct;
import com.example.test.model.StatisticsByQuantityOfCategory;
import com.example.test.repository.StatisticsByQuantityOfCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsByQuantityOfCategoryServiceImpl implements StatisticsByQuantityOfCategoryService {
    @Autowired
    StatisticsByQuantityOfCategoryRepo statistics;

    @Override
    public Iterable<StatisticsByQuantityOfCategory> StatisticsByQuantityOfCategory(Long id) {
        return statistics.StatisticsByQuantityOfCategory(id);
    }
}
