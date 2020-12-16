package com.example.test.zPackage.service.ProductService;

import com.example.test.model.Category;
import com.example.test.model.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceZ {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product product);

    void remove(Product product);

    Iterable<Product> findAllByCategory(Category category);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);


    }
