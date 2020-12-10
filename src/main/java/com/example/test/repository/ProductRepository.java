package com.example.test.repository;


import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findProductsByCategoryId(Long id, Pageable pageable);
    Page<Product> findProductsByNameContaining(String name,Pageable pageable);
}
