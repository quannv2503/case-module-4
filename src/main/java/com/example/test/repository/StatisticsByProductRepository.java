package com.example.test.repository;

import com.example.test.model.OrderConfirmation;
import com.example.test.model.StatisticsByProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatisticsByProductRepository extends CrudRepository<StatisticsByProduct,Long> {
    @Query(value = "CALL statisticsByProduct(?1);", nativeQuery = true)
    Iterable<StatisticsByProduct> statisticsByProduct(Long id);
}
