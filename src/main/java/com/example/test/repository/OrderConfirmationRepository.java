package com.example.test.repository;

import com.example.test.model.OrderConfirmation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderConfirmationRepository extends CrudRepository<OrderConfirmation, Long> {
    @Query(value = "CALL listOrderWait(?1);", nativeQuery = true)
    Iterable<OrderConfirmation> listOrderWait(Long id);
    @Query(value = "CALL listOrderConfirmed(?1);", nativeQuery = true)
    Iterable<OrderConfirmation> listOrderConfirmed(Long id);
    @Query(value = "CALL listOrderRefuse(?1);", nativeQuery = true)
    Iterable<OrderConfirmation> listOrderRefuse(Long id);


}
