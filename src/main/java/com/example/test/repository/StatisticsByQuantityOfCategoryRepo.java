package com.example.test.repository;

import com.example.test.model.StatisticsByProduct;
import com.example.test.model.StatisticsByQuantityOfCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatisticsByQuantityOfCategoryRepo extends CrudRepository<StatisticsByQuantityOfCategory,Long> {
    @Query(value = "CALL StatisticsByQuantityOfCategory(?1);", nativeQuery = true)
    Iterable<StatisticsByQuantityOfCategory> StatisticsByQuantityOfCategory(Long id);
}
