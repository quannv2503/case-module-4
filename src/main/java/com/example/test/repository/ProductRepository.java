package com.example.test.repository;


import com.example.test.model.OrderConfirmation;
import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findProductsByCategoryIdAndSeller_IdAndActiveIsOrderByIdDesc(Long id1, Long id2, String active, Pageable pageable);

    Page<Product> findProductsByNameContainingAndSeller_IdAndActiveIsOrderByIdDesc(String name, Long id, String active, Pageable pageable);

    Page<Product> findProductsBySellerIdAndActiveIsOrderByIdDesc(Long id, String active, Pageable pageable);
    Iterable<Product> findProductsBySellerIdAndActiveIsOrderByIdAsc(Long id,String active);
    Iterable<Product> findProductsBySellerIdOrderByIdAsc(Long id);

}
