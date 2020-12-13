package com.example.test.service;


import com.example.test.model.OrderConfirmation;
import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> list(Long id,String active,Pageable pageable);
    void save(Product product);
    void delete(Product product);
    Product findProduct(Long id);
    Page<Product> listDivide(Long id1,Long id2,String active,Pageable pageable);
    Page<Product> findAllByNameContaining(String name,Long id,String active,Pageable pageable);
    Iterable<OrderConfirmation> listOrderConfirmation(Long id);
}
