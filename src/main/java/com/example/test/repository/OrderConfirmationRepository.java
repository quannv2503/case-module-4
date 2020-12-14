package com.example.test.repository;

import com.example.test.model.OrderConfirmation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderConfirmationRepository extends CrudRepository<OrderConfirmation, Long> {
    @Query(value = "CALL listOrderConfirmation(?1);", nativeQuery = true)
    Iterable<OrderConfirmation> listOrderConfirmation(Long id);
}
