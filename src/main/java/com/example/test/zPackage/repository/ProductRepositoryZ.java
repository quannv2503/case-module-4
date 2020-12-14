package com.example.test.zPackage.repository;

import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryZ extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);
}
