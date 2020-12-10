package com.example.test.service;


import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> list(Pageable pageable);
    void save(Product product);
    void delete(Product product);
    Product findProduct(Long id);
    Page<Product> listDivide(Long id,Pageable pageable);
    Page<Product> findAllByNameContaining(String name,Pageable pageable);
}
