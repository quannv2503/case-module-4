package com.example.test.zPackage.service.ProductService;

import com.example.test.model.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceZ {

    Page<Product> findAll(Pageable pageable);

}
