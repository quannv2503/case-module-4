package com.example.test.repository;


import com.example.test.model.OrderConfirmation;
import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findProductsByCategoryIdAndSeller_IdAndActiveIs(Long id1, Long id2, String active, Pageable pageable);

    Page<Product> findProductsByNameContainingAndSeller_IdAndActiveIs(String name, Long id, String active, Pageable pageable);

    Page<Product> findProductsBySellerIdAndActiveIs(Long id, String active, Pageable pageable);

    @Query(value = "CALL listOrder(?1);", nativeQuery = true)
    public Iterable<OrderConfirmation> listOrderConfirmation(Long id);
}
