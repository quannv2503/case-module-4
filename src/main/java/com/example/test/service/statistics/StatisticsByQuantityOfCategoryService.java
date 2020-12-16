package com.example.test.service.statistics;

import com.example.test.model.StatisticsByProduct;
import com.example.test.model.StatisticsByQuantityOfCategory;

public interface StatisticsByQuantityOfCategoryService {
    Iterable<StatisticsByQuantityOfCategory> StatisticsByQuantityOfCategory(Long id);

}
